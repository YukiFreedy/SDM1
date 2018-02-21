package sdm.yuki.sdm1.view.bettersettings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import sdm.yuki.sdm1.R

class BetterSettingsActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences_settings)
    }


    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, BetterSettingsActivity::class.java)
        }
    }
}
