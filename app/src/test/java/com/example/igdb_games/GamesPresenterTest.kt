package com.example.igdb_games

import com.example.igdb_games.di.IGameScopeContainer
import com.example.igdb_games.mvp.model.repo.IGamesRepo
import com.example.igdb_games.mvp.presenter.GamesPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GamesPresenterTest {

    private lateinit var presenter: GamesPresenter

    @Mock
    private lateinit var repository: IGamesRepo

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var gameScopeContainer: IGameScopeContainer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = GamesPresenter().apply {
            gamesRepo = repository
            uiScheduler = Schedulers.io()
            router = this@GamesPresenterTest.router
            gameScopeContainer = this@GamesPresenterTest.gameScopeContainer
        }
    }

    @Test //Проверим вызов метода getGames() у нашего Репозитория
    fun getGames_Test() {
        `when`(repository.getGames()).thenReturn(Single.create { emitter ->
            emitter.onError(RuntimeException("Error"))
        })
        presenter.loadData()
        verify(repository, times(1)).getGames()
    }

    @Test //Проверяем работу метода backPressed()
    fun backPressed_Test() {
        presenter.backPressed()
        verify(router, times(1)).exit()
    }

    @Test //Проверяем работу метода onDestroy()
    fun onDestroy_Test() {
        presenter.onDestroy()
        verify(gameScopeContainer, times(1)).releaseGameScope()
    }
}