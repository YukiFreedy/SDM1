package sdm.yuki.sdm1.view.quotation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.Menu
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_quotation, menu)
        menu!!.getItem(0).setOnMenuItemClickListener {

            true
        }
        menu.getItem(1).setOnMenuItemClickListener {
            onAddQuotation()
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun setControllers() {
        textViewContent = findViewById(R.id.textViewQuoContent)
        textViewAuthor = findViewById(R.id.textViewQuoAuthor)
    }

    fun onAddQuotation() {
        textViewAuthor.text = getString(R.string.sample_author)
        textViewContent.text = getString(R.string.sample_quotation)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, QuotationActivity::class.java)
        }
    }
}
