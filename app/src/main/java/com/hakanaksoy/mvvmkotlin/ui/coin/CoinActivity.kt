package com.hakanaksoy.mvvmkotlin.ui.coin

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.databinding.ActivityDashboardBinding
import com.hakanaksoy.mvvmkotlin.databinding.FragmentCoinBinding
import com.hakanaksoy.mvvmkotlin.ui.base.BaseActivity
import com.hakanaksoy.mvvmkotlin.ui.dashboard.DashboardViewModel
import com.hakanaksoy.mvvmkotlin.ui.home.CoinAdapter
import com.hakanaksoy.mvvmkotlin.ui.home.CoinItemViewState
import com.hakanaksoy.mvvmkotlin.utility.extensions.notNull
import com.hakanaksoy.mvvmkotlin.utility.extensions.observeNonNull

class CoinActivity :
    BaseActivity<CoinViewModel, FragmentCoinBinding>(CoinViewModel::class.java) {

    override fun getLayoutRes(): Int = R.layout.fragment_coin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCoin()

        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        if (viewModel.progressLiveData.hasActiveObservers())
            viewModel.progressLiveData.removeObservers(this)

        viewModel.progressLiveData.observe(
            this,
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
        Log.d("home_hakan", "initCoin")
        viewModel.getCoin()
        viewModel.getCoinLiveData().observeNonNull(this) { viewstate ->
            viewstate.data.notNull {
                Log.d("home_hakan", viewstate.data?.get(1).toString())
                binding.viewState = viewstate
                binding.helpClick = itemClickListener
            }

        }
    }


    private fun refreshData() {

        binding.swipeRefreshLayout.isRefreshing = false
    }
}
