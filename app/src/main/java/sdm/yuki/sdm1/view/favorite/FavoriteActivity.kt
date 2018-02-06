package sdm.yuki.sdm1.view.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import sdm.yuki.sdm1.R

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fovorite)
    }


    fun onAuthorClicked(view: View) {
        
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, FavoriteActivity::class.java)
            return intent
        }
    }
}
