package com.hakanaksoy.mvvmkotlin.ui.home

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.base.BaseFragment
import com.hakanaksoy.mvvmkotlin.base.Resource
import com.hakanaksoy.mvvmkotlin.databinding.FragmentHomeBinding
import com.hakanaksoy.mvvmkotlin.service.response.Personel
import com.hakanaksoy.mvvmkotlin.ui.home.jr_personel.JrPersonelAdapter
import com.hakanaksoy.mvvmkotlin.ui.home.sr_personel.SrPersonelAdapter
import com.hakanaksoy.mvvmkotlin.utility.extensions.toast


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(HomeViewModel::class.java) {

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun init() {

        initSrPersonelAdapter()
        initSrPersonel()

        initJrPersonelkAdapter()
        initJrPersonel()

        mBinding.swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        if (viewModel.progressLiveData.hasActiveObservers())
            viewModel.progressLiveData.removeObservers(this)

        viewModel.progressLiveData.observe(
                this@HomeFragment,
                Observer<Boolean> {
                    if (it)
                        showProgress()
                    else
                        hideProgress()
                }
        )
    }

    private fun initJrPersonel() {

        viewModel.getJrPersonel()

        if (viewModel.personelLiveData.hasActiveObservers())
            viewModel.personelLiveData.removeObservers(this)

        viewModel.personelLiveData.observe(
                this@HomeFragment,
                Observer<Resource<Personel>> {
                    (mBinding.recyclerViewBestPodcastsJR.adapter as? JrPersonelAdapter)?.submitList(it.data?.jrpersonel)
                }
        )
    }

    private fun initJrPersonelkAdapter() {
        val adapter = JrPersonelAdapter { item ->
            toast("" + item.name, 100)
        }

        mBinding.recyclerViewBestPodcastsJR.adapter = adapter
        mBinding.recyclerViewBestPodcastsJR.layoutManager =
                GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
    }

    private fun refreshData() {
        initSrPersonel()
        initJrPersonel()
        mBinding.swipeRefreshLayout.isRefreshing = false
    }

    private fun initSrPersonelAdapter() {
        val adapter = SrPersonelAdapter { item ->
            toast("" + item.name, 100)
        }

        mBinding.recyclerViewBestPodcasts.adapter = adapter
        mBinding.recyclerViewBestPodcasts.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
    }


    private fun initSrPersonel() {

        if (viewModel.personelLiveData.hasActiveObservers())
            viewModel.personelLiveData.removeObservers(this)

        viewModel.personelLiveData.observe(
                this@HomeFragment,
                Observer<Resource<Personel>> {
                    (mBinding.recyclerViewBestPodcasts.adapter as? SrPersonelAdapter)?.submitList(it.data?.srpersonel)
                }
        )
    }

}