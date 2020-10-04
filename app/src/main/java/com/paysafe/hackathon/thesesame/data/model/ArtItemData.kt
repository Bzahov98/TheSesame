package com.paysafe.hackathon.thesesame.data.model

import android.graphics.Bitmap
import java.util.*

class ArtItemData(
    val title: String,
    val description: String,
    val price: Double,
    val currency: String,
    val listPhotos: List<Bitmap>? = null,
    val documentsOfAuthority: DocumentsOfAuthorization? = null
) {
    constructor() : this("testItem", "testItemDescription", 31.0,"EUR",null, DocumentsOfAuthorization())

}
