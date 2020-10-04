package com.paysafe.hackathon.thesesame.data.model

import android.graphics.Bitmap
import java.io.File

class DocumentsOfAuthorization(
    val listInvoiceFiles : List<File>? = null,
    val listCertificationOAFiles : List<File>? = null,
    val listFactSheetFiles : List<File>? = null,
) {

}
