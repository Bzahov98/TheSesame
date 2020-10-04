package com.paysafe.hackathon.thesesame.ui.fragments.portfolio.recyclerview

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.paysafe.hackathon.thesesame.R
import com.paysafe.hackathon.thesesame.data.model.ArtItemData
import com.paysafe.hackathon.thesesame.internal.utils.gone
import com.paysafe.hackathon.thesesame.internal.utils.show
import com.paysafe.hackathon.thesesame.internal.utils.snackbar
import com.paysafe.hackathon.thesesame.internal.utils.toast
import com.paysafe.hackathon.thesesame.ui.fragments.addNew.AddNewOfferDialogFragment
import com.paysafe.hackathon.thesesame.ui.fragments.addNew.CallbackListener
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_all_offers.view.*
import kotlinx.android.synthetic.main.item_all_offers.view.itemPortfolioImage
import kotlinx.android.synthetic.main.item_all_offers.view.itemPortfolioTitle
import kotlinx.android.synthetic.main.item_portfolio.view.*

class PortfolioItem(
    val data: ArtItemData,
    val isAddButton : Boolean = false,
    val context: FragmentActivity
) : Item(), CallbackListener{
    override fun getLayout() = R.layout.item_portfolio
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            val view = viewHolder.itemView
            updateViewData(view)
        }
    }

    private fun updateViewData(view: View) {
        if(isAddButton){
            view.itemPortfolioMainLayout.gone()
            view.itemPortfolioAddNewLayout.show()
            view.itemPortfolioAddNewButton.setOnClickListener{
                showDialog()
                view.snackbar("create new item")
            }
            return
        }
        //view.portfoli.text = data.description
        view.itemPortfolioTitle.text = data.title
        //view.itemP.text = "${data.price.toBigDecimal()} ${data.currency}"
        Glide.with(view.context)
            .asBitmap()
            .load(data.listPhotos.first())
            .into(view.itemPortfolioImage)
    }
    private fun showDialog() {
        val dialogFragment = AddNewOfferDialogFragment(this)
        dialogFragment.show(context.supportFragmentManager, "signature")
    }

    override fun onDataReceived(data: ArtItemData) {
        context.toast("successful created new item")
    }

}
