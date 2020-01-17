package com.hakanaksoy.mvvmkotlin.base

import android.app.Dialog
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.utility.ConnectivityReceiver
import com.hakanaksoy.mvvmkotlin.utility.PodPocketProgressDialog
import com.hakanaksoy.mvvmkotlin.utility.extensions.toast
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    DaggerAppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private var snackBar: Snackbar? = null
    val scopeProvider: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    lateinit var mAuth: FirebaseAuth
    var dialog: Dialog? = null
    private val connectivityReceiver = ConnectivityReceiver()

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this, viewModelProviderFactory).get(mViewModelClass)
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
        initDialog()
        onInject()
        initFirebase()
        initToast()
        getUser()

        registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean?) {
        isConnected?.let { showNetworkMessage(it) }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */

    abstract fun initViewModel(viewModel: VM)

    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            snackBar =
                Snackbar.make(findViewById(R.id.rootView), "Internet bağlantınızı kontrol edin.", Snackbar.LENGTH_LONG)
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.view?.setBackgroundColor(Color.parseColor("#F48B8C"))
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            snackBar?.show()
        } else {
            window.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            snackBar?.dismiss()
        }
    }

    fun initToast() {

        if (viewModel.toastLiveData.hasActiveObservers())
            viewModel.toastLiveData.removeObservers(this)

        viewModel.toastLiveData.observe(
            this,
            Observer<String> {
                toast(it, Toast.LENGTH_LONG)
            }
        )
    }

    fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
    }

    private fun initDialog() {
        dialog = PodPocketProgressDialog.progressDialog(this)
    }

    fun isShow(): Boolean? {
        return dialog?.isShowing
    }

    fun showProgress() {
        runOnUiThread {
            dialog?.show()
        }
    }

    fun hideProgress() {
        runOnUiThread {
            dialog?.dismiss()
        }
    }

    fun getUser() {

    }
}