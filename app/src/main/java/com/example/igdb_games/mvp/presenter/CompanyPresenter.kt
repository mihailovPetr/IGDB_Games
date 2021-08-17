package com.example.igdb_games.mvp.presenter

import com.example.igdb_games.di.ICompanyScopeContainer
import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.navigation.IScreens
import com.example.igdb_games.mvp.model.repo.ICompaniesRepo
import com.example.igdb_games.mvp.presenter.list.IGameListPresenter
import com.example.igdb_games.mvp.view.CompanyView
import com.example.igdb_games.mvp.view.list.GameItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class CompanyPresenter(var company: Company) : MvpPresenter<CompanyView>() {

    @Inject
    lateinit var companyScopeContainer: ICompanyScopeContainer

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var companiesRepo: ICompaniesRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    class GamesListPresenter : IGameListPresenter {
        val games = mutableListOf<Game>()
        override var itemClickListener: ((GameItemView) -> Unit)? = null

        override fun getCount() = games.size

        override fun bindView(view: GameItemView) {
            val game = games[view.pos]
            view.fillFields(game)
        }
    }

    val developedListPresenter = GamesListPresenter()
    val publishedListPresenter = GamesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        developedListPresenter.itemClickListener = { itemView ->
            val game = developedListPresenter.games[itemView.pos]
            router.navigateTo(screens.game(game))
        }

        publishedListPresenter.itemClickListener = { itemView ->
            val game = publishedListPresenter.games[itemView.pos]
            router.navigateTo(screens.game(game))
        }
    }

    fun loadData() {
        companiesRepo.getCompany(company.id)
            .observeOn(uiScheduler)
            .subscribe({
                company = it
                viewState.fillFields(company)
                developedListPresenter.games.clear()
                company.developed?.let { developedListPresenter.games.addAll(it) }
                viewState.updateDevelopedList()

                publishedListPresenter.games.clear()
                company.published?.let { publishedListPresenter.games.addAll(it) }
                viewState.updatePublishedList()

            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        companyScopeContainer.releaseCompanyScope()
        super.onDestroy()
    }
}