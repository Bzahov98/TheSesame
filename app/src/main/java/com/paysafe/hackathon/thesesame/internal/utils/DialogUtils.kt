package com.paysafe.hackathon.thesesame.internal.utils

import android.content.Context
import com.paysafe.hackathon.thesesame.data.model.ArtItemData
import com.paysafe.hackathon.thesesame.ui.fragments.addNew.AddNewOfferDialogFragment
import com.paysafe.hackathon.thesesame.ui.fragments.addNew.CallbackListener

private fun showDialog(context: Context, callbackListener : CallbackListener) : ArtItemData {
    val dialogFragment = AddNewOfferDialogFragment(callbackListener)
   // dialogFragment.show(context.req, "signature")
    return ArtItemData()
}