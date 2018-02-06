package sdm.yuki.sdm1.view.favorite

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import sdm.yuki.sdm1.R
import sdm.yuki.sdm1.utils.Values

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }


    fun onAuthorClicked(view: View) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(Values.WIKI_PATH + "Albert_Einstein")
        startActivity(Intent.createChooser(intent, "Choose program"))
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, FavoriteActivity::class.java)
            return intent
        }
    }
}
