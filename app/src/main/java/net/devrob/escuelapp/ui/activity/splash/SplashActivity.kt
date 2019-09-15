package net.devrob.escuelapp.ui.activity.splash

import android.os.Bundle
import net.devrob.escuelapp.ui.activity.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (userAccount != null) {
            performAction()
        } else {
            openLogin()
        }
    }
}