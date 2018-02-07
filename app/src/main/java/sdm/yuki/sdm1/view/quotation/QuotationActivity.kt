package sdm.yuki.sdm1.view.quotation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import sdm.yuki.sdm1.R

class QuotationActivity : AppCompatActivity() {

    private lateinit var textViewContent: TextView
    private lateinit var textViewAuthor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotation)
        setControllers()
        textViewContent.text = textViewContent.text.replace(Regex("%1s"), getString(R.string.default_username))
    }

    private fun setControllers() {
        textViewContent = findViewById(R.id.textViewQuoContent)
        textViewAuthor = findViewById(R.id.textViewQuoAuthor)
    }

    fun onRefresh(view: View) {
        textViewAuthor.text = getString(R.string.sample_author)
        textViewContent.text = getString(R.string.sample_quotation)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, QuotationActivity::class.java)
            return intent
        }
    }
}
