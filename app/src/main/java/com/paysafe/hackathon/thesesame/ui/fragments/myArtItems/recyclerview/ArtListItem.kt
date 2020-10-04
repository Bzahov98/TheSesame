package com.paysafe.hackathon.thesesame.ui.fragments.myArtItems.recyclerview

import android.view.View
import com.bumptech.glide.Glide
import com.paysafe.hackathon.thesesame.R
import com.paysafe.hackathon.thesesame.data.model.ArtItemData

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_all_offers.view.*

class ArtListItem(
    val data: ArtItemData
) : Item(){
    override fun getLayout() = R.layout.item_all_offers
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            val view = viewHolder.itemView
            updateViewData(view)
        }
    }

    private fun updateViewData(view: View) {
        view.allOffersListDescription.text = data.description
        view.allOffersListTitle.text = data.title
        view.allOffersListPrice.text = String.format("%.1f %",data.price)

            Glide.with(view.context)
                .asBitmap()
                .load(R.drawable.check)
                .into(view.allOffersListIcon)
    }
}
