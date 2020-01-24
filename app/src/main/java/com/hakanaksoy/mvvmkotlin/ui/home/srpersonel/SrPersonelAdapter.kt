package com.hakanaksoy.mvvmkotlin.ui.home.srpersonel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.base.BaseAdapter
import com.hakanaksoy.mvvmkotlin.databinding.ItemSrPersonelBinding
import com.hakanaksoy.mvvmkotlin.service.response.SrPersonel


class SrPersonelAdapter(private val callBack: (SrPersonel) -> Unit) : BaseAdapter<SrPersonel>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<ItemSrPersonelBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_sr_personel,
            parent,
            false
        )
        val viewModel = SrPersonelListItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemSrPersonelBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<SrPersonel>() {
    override fun areContentsTheSame(oldItem: SrPersonel, newItem: SrPersonel): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: SrPersonel, newItem: SrPersonel): Boolean =
        oldItem.id == newItem.id
}