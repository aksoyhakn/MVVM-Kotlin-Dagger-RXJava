package com.hakanaksoy.mvvmkotlin.ui.splash

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import com.hakanaksoy.mvvmkotlin.R
import com.hakanaksoy.mvvmkotlin.ui.base.BaseActivity
import com.hakanaksoy.mvvmkotlin.ui.base.Constants
import com.hakanaksoy.mvvmkotlin.databinding.ActivitySplashBinding
import com.hakanaksoy.mvvmkotlin.ui.dashboard.DashboardActivity
import com.hakanaksoy.mvvmkotlin.utility.extensions.dpToPx
import com.hakanaksoy.mvvmkotlin.utility.extensions.pixelsToDps
import com.mikhaellopez.rxanimation.*
import com.uber.autodispose.autoDisposable


class SplashActivity :
    BaseActivity<SplashActivityViewModel, ActivitySplashBinding>(SplashActivityViewModel::class.java) {

    private var listenerFlag: Boolean? = true

    override fun getLayoutRes() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ---------------------------------------------------------------------------------------
        // If you don't know what is going over there please don't replace this block or xml file.
        // ---------------------------------------------------------------------------------------

        binding.imageViewAppLogo.viewTreeObserver.addOnGlobalLayoutListener {

            if (listenerFlag != false) {

                listenerFlag = false
                val textRectf = Rect()
                val logoRectf = Rect()

                binding.textViewAppName.getGlobalVisibleRect(textRectf)
                val textX = textRectf.centerX()
                val textY = textRectf.centerY()

                binding.imageViewAppLogo.getGlobalVisibleRect(logoRectf)
                val logoX = logoRectf.centerX()
                val logoY = logoRectf.centerY()

                val moveY = textY - logoY
                val moveX =
                    textX - logoX - textRectf.width() / 2 - (Constants.MainAnimationConstants.LOGO_WIDTH / 2).dpToPx() - Constants.MainAnimationConstants.MARGIN_BETWEEN_LOGO_AND_APPNAME.dpToPx()

                RxAnimation.together(
                    binding.textViewAppName.fadeOut(Constants.MainAnimationConstants.NO_DURATION)
                )
                    .autoDisposable(scopeProvider)
                    .subscribe()

                RxAnimation.sequentially(
                    binding.imageViewAppLogo.fadeIn(Constants.MainAnimationConstants.LONG_DURATION),
                    binding.imageViewAppLogo.resize(300, 300),
                    binding.imageViewAppLogo.rotation(
                        7200f,
                        Constants.MainAnimationConstants.EXTRA_LONG_DURATION
                    ),
                    RxAnimation.together(
                        binding.imageViewAppLogo.resize(
                            Constants.MainAnimationConstants.LOGO_WIDTH_INT,
                            Constants.MainAnimationConstants.LOGO_HEIGHT_INT
                        ),
                        binding.imageViewAppLogo.translation(
                            pixelsToDps(this, moveX.toInt()).toFloat(),
                            pixelsToDps(this, moveY).toFloat(),
                            duration = Constants.MainAnimationConstants.SHORT_DURATION
                        )
                    ),
                    binding.textViewAppName.fadeIn(Constants.MainAnimationConstants.SHORT_DURATION),
                    binding.imageViewAppLogo.rotationY(
                        360f,
                        Constants.MainAnimationConstants.SHORT_DURATION
                    )
                        .doOnComplete {

                            val intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                )
                    .autoDisposable(scopeProvider)
                    .subscribe()
            }
        }
    }
    }

