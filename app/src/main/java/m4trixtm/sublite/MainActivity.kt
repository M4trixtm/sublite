package m4trixtm.sublite

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import m4trixtm.sublite.core.log.appLog
import m4trixtm.sublite.core.platform.activity.BaseActivity
import m4trixtm.sublite.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun layoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appLog { "this is a debug log test" }
    }
}