package sdm.yuki.sdm1.view.favorite

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ListView
import org.jetbrains.anko.*
import sdm.yuki.sdm1.R
import sdm.yuki.sdm1.databases.MySqliteOH
import sdm.yuki.sdm1.databases.QuotationDatabase
import sdm.yuki.sdm1.persistance.Quotation
import sdm.yuki.sdm1.utils.QuotationAdapter
import sdm.yuki.sdm1.utils.Values

class FavoriteActivity : AppCompatActivity() {

    private lateinit var quotationList: ArrayList<Quotation>
    private lateinit var adapter: QuotationAdapter
    private lateinit var menu: Menu

    private lateinit var db: MySqliteOH
    private lateinit var room: QuotationDatabase
    private var useRoom = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val listView = findViewById<ListView>(R.id.listViewFavContainer)
        db = MySqliteOH.getInstance(this)
        room = QuotationDatabase.getInstance(this)
        quotationList = db.allQuotations as ArrayList<Quotation>
        adapter = QuotationAdapter(this, R.layout.quotation_list_row, quotationList, this)
        listView.adapter = adapter
        useRoom = defaultSharedPreferences.getString(Values.DATABASE_TAG, "1") == "1"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu!!
        menu.getItem(0).setOnMenuItemClickListener {
            depleteAlert()
            true
        }
        refresh()
        return super.onCreateOptionsMenu(menu)
    }

    private fun depleteAlert() {
        alert {
            title = getString(R.string.deplete_confirmation)
            okButton {
                if (!useRoom)
                    db.removeAllQuotations()
                else
                    room.quotationDao().removeAllQuotations()
                refresh()
            }
            cancelButton { }
        }.show()
    }

    private fun refresh() {
        menu.getItem(0).isVisible = !quotationList.isEmpty()
        if (!useRoom)
            quotationList = db.allQuotations as ArrayList<Quotation>
        else
            quotationList = room.quotationDao().allQuotations as ArrayList<Quotation>
        adapter = QuotationAdapter(this, R.layout.quotation_list_row, quotationList, this)
        val listView = findViewById<ListView>(R.id.listViewFavContainer)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun onAuthorClicked(position: Int) {
        val quotation = quotationList[position]
        if (quotation.quoteAuthor.isEmpty()) {
            toast(R.string.problem_obtaining_data).show()
            return
        }
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(Values.WIKI_PATH + quotation.quoteAuthor)
        startActivity(Intent.createChooser(intent, getString(R.string.select_program)))
    }

    private fun getMockQuotations(): ArrayList<Quotation> {
        var list = ArrayList<Quotation>()
        for (i in 0..20) {
            list.add(Quotation("Quotation " + i, "Author " + i))
        }
        list[3].quoteAuthor = ""
        return list
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, FavoriteActivity::class.java)
        }
    }

    fun onLongClick(position: Int) {
        alert {
            title = getString(R.string.delete_quotation)
            okButton {
                if (!useRoom)
                    db.removeQuotation(quotationList[position])
                else
                    room.quotationDao().removeQuotation(quotationList[position])
                refresh()
            }
            cancelButton { }
        }.show()
    }
}
