package sdm.yuki.sdm1.view.favorite

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ListView
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast
import sdm.yuki.sdm1.R
import sdm.yuki.sdm1.persistance.Quotation
import sdm.yuki.sdm1.utils.QuotationAdapter
import sdm.yuki.sdm1.utils.Values

class FavoriteActivity : AppCompatActivity() {

    private lateinit var quotationList: ArrayList<Quotation>
    private lateinit var adapter: QuotationAdapter
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val listView = findViewById<ListView>(R.id.listViewFavContainer)
        quotationList = getMockQuotations()
        adapter = QuotationAdapter(this, R.layout.quotation_list_row, quotationList, this)
        listView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu!!
        menu.getItem(0).setOnMenuItemClickListener {
            depleteAlert()
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun depleteAlert() {
        alert {
            title = getString(R.string.deplete_confirmation)
            okButton {
                quotationList.clear()
                refresh()
            }
            cancelButton { }
        }.show()
    }

    private fun refresh() {
        adapter.notifyDataSetChanged()
        menu.getItem(0).isVisible = !quotationList.isEmpty()
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
        var i = 0
        while (i < 10) {
            list.add(Quotation("Quotation " + i, "Author " + i))
            i++
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
                quotationList.removeAt(position)
                refresh()
            }
            cancelButton { }
        }.show()
    }
}
