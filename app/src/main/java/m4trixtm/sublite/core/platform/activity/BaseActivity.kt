package m4trixtm.sublite.core.platform.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.skydoves.whatif.whatIfNotNull
import m4trixtm.sublite.R
import m4trixtm.sublite.core.platform.statusAlert.StatusAlert
import m4trixtm.sublite.core.platform.statusAlert.StatusAlertView

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: B

    abstract fun layoutRes(): Int

    private var offlineStatusAlert: StatusAlertView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes())
    }

    override fun onDestroy() {
        super.onDestroy()
        offlineStatusAlert = null
    }

    fun showOfflineStatus() {
        offlineStatusAlert = StatusAlert.Builder(this).run {
            autoHide(false)
            showProgress(true)
            withText(R.string.net_not_available)
            withAlertColor(android.R.color.transparent)
            withIndeterminateProgressBarColor(R.color.white)
            withTextColor(R.color.white)
            build()
        }
    }

    fun hideOfflineStatus() {
        offlineStatusAlert.whatIfNotNull {
            it.updateText(R.string.net_not_available)
            it.hideIndeterminateProgress()
        }
        StatusAlert.Builder(this)
            .autoHide(true)
            .withDuration(3000)
            .showProgress(false)
            .withText(R.string.back_online)
            .withAlertColor(android.R.color.holo_green_dark)
            .withTextColor(R.color.white)
            .withIndeterminateProgressBarColor(R.color.white)
            .build()
    }
}