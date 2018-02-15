package sdm.yuki.sdm1.view.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import sdm.yuki.sdm1.R
import sdm.yuki.sdm1.databases.QuotationDatabase

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val quotations = QuotationDatabase.getInstance(this).quotationDao().allQuotations
        for (q in quotations)
            Log.d("text", q.quoteText)
    }


    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, AboutActivity::class.java)
        }
    }
}
