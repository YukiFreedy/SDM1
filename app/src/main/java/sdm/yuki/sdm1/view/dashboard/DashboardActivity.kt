package sdm.yuki.sdm1.view.dashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import sdm.yuki.sdm1.R
import sdm.yuki.sdm1.view.about.AboutActivity
import sdm.yuki.sdm1.view.better_settings.BetterSettingsActivity
import sdm.yuki.sdm1.view.favorite.FavoriteActivity
import sdm.yuki.sdm1.view.quotation.QuotationActivity
import sdm.yuki.sdm1.view.settings.SettingsActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    fun buttonClicked(view: View) {
        when (view.id) {
            R.id.buttonDashGetQuotations -> {
                startActivity(QuotationActivity.newIntent(this))
            }
            R.id.buttonDashFavoriteQuotations -> {
                startActivity(FavoriteActivity.newIntent(this))
            }
            R.id.buttonDashSettings -> {
                startActivity(BetterSettingsActivity.newIntent(this))
            }
            R.id.buttonDashAbout -> {
                startActivity(AboutActivity.newIntent(this))
            }
        }
    }
}
