package net.devrob.escuelapp.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.layout_account.*
import net.devrob.escuelapp.R

class AccountData : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_account)

        fillData()
    }

    private fun fillData() {
        userAccount ?: finish()

        tiedFirstName?.setText(userAccount?.displayName)

    }


}