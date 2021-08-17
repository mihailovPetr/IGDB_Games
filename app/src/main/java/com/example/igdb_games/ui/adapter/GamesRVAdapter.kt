package com.example.igdb_games.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.igdb_games.databinding.ItemGameBinding
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.presenter.list.IGameListPresenter
import com.example.igdb_games.mvp.view.list.GameItemView

class GamesRVAdapter(val presenter: IGameListPresenter) :
    RecyclerView.Adapter<GamesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun onViewRecycled(holder: ViewHolder) = holder.unbind()

    inner class ViewHolder(val vb: ItemGameBinding) : RecyclerView.ViewHolder(vb.root),
        GameItemView {

        override fun fillFields(game: Game) = with(vb) {
            tvName.text = game.name
            //Glide.with(ivCover).load()
        }

        override var pos = -1

        fun unbind() = vb.ivCover.setImageDrawable(null)
    }
}