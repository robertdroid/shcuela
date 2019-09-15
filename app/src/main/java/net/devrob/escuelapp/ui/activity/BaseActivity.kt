package net.devrob.escuelapp.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import net.devrob.escuelapp.R

private const val COLLECTION_ACCOUNT = "accounts"
private const val RC_SIGN_IN = 100
abstract class BaseActivity : AppCompatActivity() {
    private val database = FirebaseFirestore.getInstance()
    val schoolCollection = database.collection(COLLECTION_ACCOUNT)
    val userAccount = FirebaseAuth.getInstance().currentUser
    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
        AuthUI.IdpConfig.FacebookBuilder().build(),
        AuthUI.IdpConfig.AnonymousBuilder().build())
    private val pickerLayout = AuthMethodPickerLayout
        .Builder(R.layout.layout_registro)
        .setGoogleButtonId(R.id.btnGoogle)
        .setAnonymousButtonId(R.id.btnContinue)
        .setEmailButtonId(R.id.btnRegisterMail)
        .setFacebookButtonId(R.id.btnFaceboook)
        .setTosAndPrivacyPolicyId(R.id.tvTosPP)
        .build()

    fun openLogin() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAuthMethodPickerLayout(pickerLayout)
                .setTosAndPrivacyPolicyUrls("http://www.devrob.net", "http://www.devrob.net")
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_SIGN_IN -> {
                if (resultCode == Activity.RESULT_OK) {
                    performAction()
                } else {
                    finish()
                }
            }
        }
    }

    fun performAction() {
        schoolCollection
            .whereEqualTo("uid_user", userAccount?.uid)
            .get().addOnCompleteListener {result ->
                if (result.isSuccessful) {
                    result.result?.let {data ->
                        if (data.isEmpty) {
                            goToAccountActivity()
                        } else {
                            //Main app
                        }
                    }
                } else {
                    goToAccountActivity()
                }
            }
    }
    
    private fun goToAccountActivity() {
        val i = Intent(this, AccountData::class.java)
        startActivity(i)
    }
}