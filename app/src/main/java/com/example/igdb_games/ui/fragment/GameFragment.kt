package com.example.igdb_games.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.igdb_games.databinding.FragmentGameBinding
import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.model.entity.Image
import com.example.igdb_games.mvp.model.entity.load
import com.example.igdb_games.mvp.presenter.GamePresenter
import com.example.igdb_games.mvp.view.GameView
import com.example.igdb_games.ui.App
import com.example.igdb_games.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import kotlin.math.roundToInt

class GameFragment : MvpAppCompatFragment(), GameView, BackButtonListener {

    companion object {
        const val USER_ARG = "game"

        fun newInstance(game: Game) = GameFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, game)
            }
        }
    }

    val presenter: GamePresenter by moxyPresenter {
        val game = arguments?.getParcelable<Game>(USER_ARG) as Game
        GamePresenter(game).apply {
            App.instance.initCompanySubcomponent().inject(this)
        }
    }

    private var vb: FragmentGameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGameBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun fillFields(game: Game, developers: List<Company>, publishers: List<Company>) {
        vb?.apply {

            tvGameName.text = game.name
            tvSummary.text = game.summary
            tvRating.text = game.rating?.roundToInt().toString()
            tvAggregatedRating.text = game.aggregatedRating?.roundToInt().toString()
            game.cover?.getURL(Image.COVER_BIG)?.let { ivGameCover.load(it) }

            for (developer in developers) {
                val tv = TextView(requireContext())
                tv.text = developer.name
                tv.setTextColor(Color.BLUE)
                tv.setOnClickListener { presenter.companyClickListener?.invoke(developer) }
                developersContainer.addView(tv)
            }

            for (publisher in publishers) {
                val tv = TextView(requireContext())
                tv.text = publisher.name
                tv.setTextColor(Color.BLUE)
                tv.setOnClickListener { presenter.companyClickListener?.invoke(publisher) }
                publishersContainer.addView(tv)
            }

        }
    }

    override fun backPressed() = presenter.backPressed()
}