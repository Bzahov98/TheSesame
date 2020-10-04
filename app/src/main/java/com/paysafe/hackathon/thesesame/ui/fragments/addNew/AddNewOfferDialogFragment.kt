package com.paysafe.hackathon.thesesame.ui.fragments.addNew

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.paysafe.hackathon.thesesame.R
import com.paysafe.hackathon.thesesame.TheSesameApplication.Companion.getAppString
import com.paysafe.hackathon.thesesame.data.model.ArtItemData
import com.paysafe.hackathon.thesesame.internal.utils.snackbar
import com.paysafe.hackathon.thesesame.internal.utils.toast
import kotlinx.android.synthetic.main.dialog_fullscreen_add_new.*

class AddNewOfferDialogFragment(private val callbackListener: CallbackListener) : DialogFragment() {
   lateinit var images : List<Image>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.dialog_fullscreen_add_new, container, false)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNewDialPictButton.setOnClickListener{
            ImagePicker
                .create(this) // Activity or Fragment
                .multi()
                .limit(12)
                .start()
        }
        addNewOfferDialogConfirmButton.setOnClickListener {

            if(isViewDataValid()){
                //send back data to PARENT fragment using callback
                requireContext().toast("Processing Data")
                callbackListener.onDataReceived(extractFieldDataToItemData())
                // Now dismiss the fragment
                dismiss()
            }else{
                view.snackbar("Field Data Views are not valid")
            }

        }

    }

    private fun isViewDataValid(): Boolean {
//        val title = addNewDialTitleText.text.toString()
//        val author = addNewDialAuthorText.text.toString()
//        val description = addNewDialDescriptionText.text.toString()
//        val priceOfShare : Double = addNewDialPriceOfShareText.text.toString().toDouble()
//        val equity : Double = addNewDialPriceOfShareText.text.toString().toDouble()
//        if(priceOfShare < 0 ) return false
        return true
    }

    private fun extractFieldDataToItemData(): ArtItemData {

        val title = addNewDialTitleText.text.toString()
        val author = addNewDialAuthorText.text.toString()
        val description = addNewDialDescriptionText.text.toString()
        val toString = addNewDialPriceOfShareText.text.toString()
        val priceOfShare : Double = if(toString!="") toString.toDouble() else 0.0
        val toString1 = addNewDialEquityText.text.toString()
        val equity : Double = if(toString1!="") toString1.toDouble() else 0.0

        clearAllFields()
        return ArtItemData(
            title,
            description,
            priceOfShare ,
            getAppString(R.string.tools_art_currency)
        )

    }

    private fun clearAllFields() {
        val title = addNewDialTitleText.setText( "")
        val author = addNewDialAuthorText.setText( "")
        val description = addNewDialDescriptionText.setText( "")
        val priceOfShare  = addNewDialPriceOfShareText.setText("0")
        val equity  = addNewDialPriceOfShareText.setText("0")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            images = ImagePicker.getImages(data)
            // or get a single image only
           val image = ImagePicker.getFirstImageOrNull(data)
        }
        addNewDialPictText.setText("You added ${images.size} images")
        super.onActivityResult(requestCode, resultCode, data);
    }
}

interface CallbackListener {
    fun onDataReceived(data: ArtItemData)
}

fun List<Image>.toListOfUrl(){

}