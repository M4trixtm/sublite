package m4trixtm.sublite

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import m4trixtm.sublite.core.log.appLog
import m4trixtm.sublite.core.platform.activity.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appLog { "this is a debug log test" }
    }
}