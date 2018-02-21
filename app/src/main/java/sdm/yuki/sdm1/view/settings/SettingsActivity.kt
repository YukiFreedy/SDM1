package sdm.yuki.sdm1.view.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import org.jetbrains.anko.defaultSharedPreferences
import sdm.yuki.sdm1.R
import sdm.yuki.sdm1.utils.Values

class SettingsActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setControllers()
    }

    override fun onPause() {
        defaultSharedPreferences.edit().putString(Values.USERNAME_TAG, editTextUsername.text.toString()).apply()
        super.onPause()
    }

    private fun setControllers() {
        editTextUsername = findViewById(R.id.editTextSettingUsername)
        try {
            editTextUsername.setText(defaultSharedPreferences.all[Values.USERNAME_TAG] as String)
        } catch (e: Exception) {
            Log.d("exc", e.message)
            editTextUsername.setText("")
        }

    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}
