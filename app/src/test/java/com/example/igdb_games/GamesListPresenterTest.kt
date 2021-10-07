package com.example.igdb_games

import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.presenter.GamesPresenter
import com.example.igdb_games.mvp.view.list.GameItemView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GamesListPresenterTest {

    private lateinit var gamesListPresenter: GamesPresenter.GamesListPresenter

    @Mock
    private lateinit var view: GameItemView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        gamesListPresenter = GamesPresenter.GamesListPresenter().apply {
            games.add(Game(0, null, null, null, null, null))
        }
    }

    @Test //Проверяем работу метода bindView()
    fun bindView_Test() {
        `when`(view.pos).thenReturn(0)
        gamesListPresenter.bindView(view)
        verify(view, times(1)).fillFields(gamesListPresenter.games[0])
    }
}