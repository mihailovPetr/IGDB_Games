package com.example.igdb_games.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igdb_games.databinding.FragmentCompanyBinding
import com.example.igdb_games.mvp.model.entity.Company
import com.example.igdb_games.mvp.presenter.CompanyPresenter
import com.example.igdb_games.mvp.view.CompanyView
import com.example.igdb_games.ui.App
import com.example.igdb_games.ui.BackButtonListener
import com.example.igdb_games.ui.adapter.CompanyGamesRVAdapter
import com.example.igdb_games.ui.adapter.GamesRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CompanyFragment : MvpAppCompatFragment(), CompanyView, BackButtonListener {

    companion object {
        private const val COMPANY_ARG = "company"

        fun newInstance(company: Company) = CompanyFragment().apply {
            arguments = Bundle().apply {
                putParcelable(COMPANY_ARG, company)
            }
        }
    }

    val presenter: CompanyPresenter by moxyPresenter {
        val company = arguments?.getParcelable<Company>(COMPANY_ARG) as Company
        CompanyPresenter(company).apply {
            App.instance.initCompanySubcomponent().inject(this)
        }
    }

    private var vb: FragmentCompanyBinding? = null

    var developedAdapter: CompanyGamesRVAdapter? = null
    var publishedAdapter: CompanyGamesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCompanyBinding.inflate(inflater, container, false).also { vb = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvDeveloped?.layoutManager = LinearLayoutManager(context)
        developedAdapter = CompanyGamesRVAdapter(presenter.developedListPresenter)
        vb?.rvDeveloped?.adapter = developedAdapter

        vb?.rvPublished?.layoutManager = LinearLayoutManager(context)
        publishedAdapter = CompanyGamesRVAdapter(presenter.publishedListPresenter)
        vb?.rvPublished?.adapter = publishedAdapter
    }

    override fun updateDevelopedList() {
        developedAdapter?.notifyDataSetChanged()
    }

    override fun updatePublishedList() {
        publishedAdapter?.notifyDataSetChanged()
    }

    override fun fillFields(company: Company) {
        vb?.apply {
            tvCompanyName.text = company.name
            tvCompanyDescription.text = company.description
        }
    }

    override fun backPressed() = presenter.backPressed()
}