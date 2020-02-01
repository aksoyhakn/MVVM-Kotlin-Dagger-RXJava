package com.hakanaksoy.mvvmkotlin.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.databinding.ItemCoinBinding
import com.hakanaksoy.mvvmkotlin.service.response.Coin
import com.hakanaksoy.mvvmkotlin.utility.extensions.inflate
import com.hakanaksoy.mvvmkotlin.utility.extensions.isNotNull
import com.hakanaksoy.mvvmkotlin.utility.extensions.notNull
import javax.inject.Inject

class CoinAdapter(
    var list: List<Coin>,
    var itemClickListener: ItemClickListener?
) : RecyclerView.Adapter<CoinAdapter.PopularTVShowsFeedItemViewHolder>() {

    interface ItemClickListener {
        fun onClick(data: CoinItemViewState)
    }


    companion object {
        @JvmStatic
        @BindingAdapter(value = ["helpMenuList", "helpItemClick"])
        fun bind(
            recyclerView: RecyclerView?,
            helpMenuList: List<Coin>?,
            helpItemClick: ItemClickListener?
        ) {
            recyclerView.notNull { v ->
                Log.d("home_hakan","coinAdapter")
                if (helpMenuList.isNotNull()) {
                    Log.d("home_hakan","coinAdapter-1")
                    if (v.adapter == null) {
                        v.isScrollContainer = false
                        v.isNestedScrollingEnabled = false
                        v.layoutManager =
                            LinearLayoutManager(v.context)
                        v.adapter = CoinAdapter(helpMenuList!!, helpItemClick)
                    } else {
                        Log.d("home_hakan","coinAdapter-2")
                        val adapter = v.adapter as CoinAdapter
                        adapter.list = helpMenuList!!
                        adapter.itemClickListener = helpItemClick
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularTVShowsFeedItemViewHolder {
        val itemBinding =
            ItemCoinBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        return PopularTVShowsFeedItemViewHolder(parent, itemBinding)

    }

    override fun getItemCount(): Int {
        Log.d("home_hakan","coinAdapter-ıtemcount"+list.size)
        return  list.size
    }

    open fun getItem(position: Int): Coin {
        return list[position]
    }

    override fun onBindViewHolder(holder: PopularTVShowsFeedItemViewHolder, position: Int) {
        holder.bind(getItem(position), this)
    }

    inner class PopularTVShowsFeedItemViewHolder(
        val view: View,
        private val binding: ItemCoinBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: Coin, adapterPopular: CoinAdapter) {
            Log.d("home_hakan","binding")
            with(binding) {
                viewState = CoinItemViewState(tvShow)
                adapter = adapterPopular
            }
        }

    }
}