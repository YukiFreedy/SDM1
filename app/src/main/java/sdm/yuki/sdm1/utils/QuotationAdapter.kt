package sdm.yuki.sdm1.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import sdm.yuki.sdm1.R
import sdm.yuki.sdm1.persistance.Quotation
import sdm.yuki.sdm1.view.favorite.FavoriteActivity

/**
 * Created by Yuki on 07-Feb-18.
 */

class QuotationAdapter(context: Context, resource: Int, var data: List<Quotation>, private val favoriteActivity: FavoriteActivity) : ArrayAdapter<Quotation>(context, resource, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = View.inflate(context, R.layout.quotation_list_row, null)
        }
        val textViewQuotation = view!!.findViewById<TextView>(R.id.textViewQuoListText)
        textViewQuotation.text = data[position].quoteText
        val textViewAuthor = view.findViewById<TextView>(R.id.textViewQuoListAuthor)
        textViewAuthor.text = data[position].quoteAuthor
        view.setOnClickListener {
            favoriteActivity.onAuthorClicked(position)
        }
        view.setOnLongClickListener {
            favoriteActivity.onLongClick(position)
            true
        }
        return view
    }
}