package sdm.yuki.sdm1.view.quotation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.widget.TextView
import org.jetbrains.anko.defaultSharedPreferences
import sdm.yuki.sdm1.R
import sdm.yuki.sdm1.databases.MySqliteOH
import sdm.yuki.sdm1.databases.QuotationDatabase
import sdm.yuki.sdm1.persistance.Quotation
import sdm.yuki.sdm1.utils.Values

class QuotationActivity : AppCompatActivity() {

    private lateinit var textViewContent: TextView
    private lateinit var textViewAuthor: TextView

    private lateinit var menu: Menu

    private var quotationsReceived = 0

    private var addVisible = false
    private var currentQuotation: Quotation? = null

    private var useRoom = false

    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotation)
        setControllers()

        useRoom = defaultSharedPreferences.getString(Values.DATABASE_TAG, "0") == "1"

        if (savedInstanceState == null) {
            setUsername()
        } else {
            textViewContent.text = savedInstanceState.getString("author")
            textViewAuthor.text = savedInstanceState.getString("content")
            quotationsReceived = savedInstanceState.getInt("count")
            addVisible = savedInstanceState.getBoolean("addVisible")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putString("author", textViewAuthor.text.toString())
        outState.putString("content", textViewContent.text.toString())
        outState.putInt("count", quotationsReceived)
        outState.putBoolean("addVisible", menu.getItem(0).isVisible)
        super.onSaveInstanceState(outState)
    }

    private fun setUsername() {
        try {
            var username = defaultSharedPreferences.all[Values.USERNAME_TAG] as String
            if (username.isEmpty())
                username = getString(R.string.default_username)

            textViewContent.text =
                    textViewContent.text.replace(
                            Regex("%1s"), username
                    )
        } catch (e: Exception) {
            Log.d("exc", e.message)
            textViewContent.text =
                    textViewContent.text.replace(
                            Regex("%1s"),
                            getString(R.string.default_username))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_quotation, menu)
        this.menu = menu!!
        menu.getItem(0).isVisible = addVisible
        menu.getItem(0).setOnMenuItemClickListener {
            menu.getItem(0).isVisible = false
            Thread {
                if (!useRoom)
                    MySqliteOH.getInstance(this).addQuotation(currentQuotation)
                else
                    QuotationDatabase.getInstance(this).quotationDao().addQuotation(currentQuotation)
            }.start()
            true
        }
        menu.getItem(1).setOnMenuItemClickListener {
            val thread = Thread {
                currentQuotation = onAddQuotation()
                if (!useRoom) {
                    val isInDb = !MySqliteOH.getInstance(this).alreadyInDatabase(currentQuotation)
                    handler.post {
                        menu.getItem(0).isVisible = isInDb
                    }
                } else {
                    val alreadyInFav = QuotationDatabase.getInstance(this).quotationDao().getQuotation(currentQuotation!!.quoteText) == null
                    handler.post {
                        menu.getItem(0).isVisible = alreadyInFav
                    }
                }
            }
            thread.start()
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun setControllers() {
        textViewContent = findViewById(R.id.textViewQuoContent)
        textViewAuthor = findViewById(R.id.textViewQuoAuthor)
    }

    private fun onAddQuotation(): Quotation {

        handler.post {
            textViewAuthor.text = getString(R.string.sample_author).replace(Regex("%1d"), quotationsReceived.toString())
            textViewContent.text = getString(R.string.sample_quotation).replace(Regex("%1d"), quotationsReceived.toString())
        }
        val quotation = Quotation(textViewContent.text.toString(), textViewAuthor.text.toString())
        quotationsReceived++
        return quotation
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, QuotationActivity::class.java)
        }
    }
}
