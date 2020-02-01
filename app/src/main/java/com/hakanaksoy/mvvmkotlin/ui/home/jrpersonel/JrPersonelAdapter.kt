package com.hakanaksoy.mvvmkotlin.ui.home.jrpersonel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.ui.base.BaseAdapter
import com.hakanaksoy.mvvmkotlin.databinding.ItemJrPersonelBinding
import com.hakanaksoy.mvvmkotlin.service.response.JrPersonel


class JrPersonelAdapter (private val callBack: (JrPersonel) -> Unit) : BaseAdapter<JrPersonel>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<ItemJrPersonelBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_jr_personel,
                parent,
                false
        )
        val viewModel = JrPersonelListItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemJrPersonelBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<JrPersonel>() {
    override fun areContentsTheSame(oldItem: JrPersonel, newItem: JrPersonel): Boolean =
            oldItem == newItem

    override fun areItemsTheSame(oldItem: JrPersonel, newItem: JrPersonel): Boolean =
            oldItem.id == newItem.id
}