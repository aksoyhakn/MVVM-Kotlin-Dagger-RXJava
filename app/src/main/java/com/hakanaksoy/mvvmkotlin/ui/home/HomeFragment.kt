package com.hakanaksoy.mvvmkotlin.ui.home

import android.util.Log
import androidx.lifecycle.Observer
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.ui.base.BaseFragment
import com.hakanaksoy.mvvmkotlin.databinding.FragmentCoinBinding
import com.hakanaksoy.mvvmkotlin.utility.extensions.notNull
import com.hakanaksoy.mvvmkotlin.utility.extensions.observeNonNull


class HomeFragment : BaseFragment<HomeViewModel, FragmentCoinBinding>(HomeViewModel::class.java) {

    override fun initViewModel() {
    }

    override fun getLayoutRes(): Int = R.layout.fragment_coin

    override fun init() {
        initCoin()

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

    val itemClickListener = object : CoinAdapter.ItemClickListener {
        override fun onClick(data: CoinItemViewState) {
            Log.d("main_activiy", data.getName())

        }

    }

    private fun initCoin() {
        Log.d("home_hakan","initCoin")
        viewModel.getCoin()
        viewModel.getCoinLiveData().observeNonNull(this) { viewstate ->
            viewstate.data.notNull {
                Log.d("home_hakan",viewstate.data?.get(1).toString())
                mBinding.viewState = viewstate
                mBinding.helpClick = itemClickListener
            }

        }
    }


    private fun refreshData() {

        mBinding.swipeRefreshLayout.isRefreshing = false
    }


}