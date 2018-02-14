package sdm.yuki.sdm1.view.about

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import sdm.yuki.sdm1.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }


    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, AboutActivity::class.java)
        }
    }
}
