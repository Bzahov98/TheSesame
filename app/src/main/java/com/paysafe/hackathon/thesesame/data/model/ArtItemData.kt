package com.paysafe.hackathon.thesesame.data.model

import android.graphics.Bitmap
import com.paysafe.hackathon.thesesame.R
import com.paysafe.hackathon.thesesame.TheSesameApplication
import com.paysafe.hackathon.thesesame.TheSesameApplication.Companion.getAppString
import java.util.*

class ArtItemData(
    val title: String,
    val description: String,
    val price: Double,
    val currency: String = getAppString(R.string.tools_art_currency),
    val listPhotos: List<String> = listOf(getAppString(R.string.dummy_pic_url)),
    val documentsOfAuthority: DocumentsOfAuthorization? = null
) {
    constructor() : this(
        getAppString(R.string.tools_art_title),
        getAppString(R.string.tools_art_supporting_text),
        31.0,
        getAppString(R.string.tools_art_currency)
    )

}
