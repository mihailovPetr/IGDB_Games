package com.example.igdb_games.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igdb_games.databinding.FragmentGamesBinding
import com.example.igdb_games.di.game.GameSubcomponent
import com.example.igdb_games.mvp.presenter.GamesPresenter
import com.example.igdb_games.mvp.view.GamesView
import com.example.igdb_games.ui.App
import com.example.igdb_games.ui.BackButtonListener
import com.example.igdb_games.ui.adapter.GamesRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class GamesFragment : MvpAppCompatFragment(), GamesView, BackButtonListener {

    companion object {
        fun newInstance() = GamesFragment()
    }

    var gameSubcomponent: GameSubcomponent? = null

    val presenter: GamesPresenter by moxyPresenter {
        GamesPresenter().apply {
            gameSubcomponent = App.instance.initGameSubcomponent()
            gameSubcomponent?.inject(this)
        }
    }

    var adapter: GamesRVAdapter? = null
    private var vb: FragmentGamesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentGamesBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvGames?.layoutManager = LinearLayoutManager(context)
        adapter = GamesRVAdapter(presenter.gamesListPresenter).apply {
            gameSubcomponent?.inject(this)
        }
        vb?.rvGames?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}