package com.example.igdb_games.mvp.presenter

import com.example.igdb_games.di.ICompanyScopeContainer
import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.navigation.IScreens
import com.example.igdb_games.mvp.model.repo.ICompaniesRepo
import com.example.igdb_games.mvp.view.GameView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class GamePresenter(val game: Game) : MvpPresenter<GameView>() {

    @Inject lateinit var companyScopeContainer: ICompanyScopeContainer
    @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var companiesRepo: ICompaniesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    var companyClickListener: ((Company) -> Unit)? = null
        private set

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()

        companyClickListener = { router.navigateTo(screens.company(it))}
    }

    fun loadData() {
        companiesRepo.getInvolvedCompanies(game)
            .observeOn(uiScheduler)
            .subscribe({ companies ->
                val developers = mutableListOf<Company>()
                val publishers = mutableListOf<Company>()

                for (company in companies){
                    if (company.developer) developers.add(company.company)
                    if (company.publisher) publishers.add(company.company)
                }

                viewState.fillFields(game, developers, publishers)
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
