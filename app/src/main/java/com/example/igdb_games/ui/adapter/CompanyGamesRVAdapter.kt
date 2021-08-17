package com.example.igdb_games.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.igdb_games.databinding.ItemGameBinding
import com.example.igdb_games.databinding.ItemGameForCompanyScreenBinding
import com.example.igdb_games.mvp.model.entity.Game
import com.example.igdb_games.mvp.presenter.list.IGameListPresenter
import com.example.igdb_games.mvp.view.list.GameItemView

class CompanyGamesRVAdapter(val presenter: IGameListPresenter) :
    RecyclerView.Adapter<CompanyGamesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemGameForCompanyScreenBinding.inflate(
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

    inner class ViewHolder(val vb: ItemGameForCompanyScreenBinding) : RecyclerView.ViewHolder(vb.root),
        GameItemView {

        override fun fillFields(game: Game) = with(vb) {
            tvName.text = game.name
        }

        override var pos = -1

        fun unbind(){}
    }
}