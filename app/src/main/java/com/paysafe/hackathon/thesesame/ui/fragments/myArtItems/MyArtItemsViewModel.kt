package com.paysafe.hackathon.thesesame.ui.fragments.myArtItems

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paysafe.hackathon.thesesame.data.model.ArtItemData
import com.paysafe.hackathon.thesesame.data.repositories.ItemsRepository
import com.paysafe.hackathon.thesesame.ui.fragments.myArtItems.recyclerview.ArtListItem

class MyArtItemsViewModel(val itemsRepository: ItemsRepository) : ViewModel() {
private val TAG = "MyArtItemsViewModel"

    private val _listData = MutableLiveData<List<ArtItemData>>().apply {
        postValue(listOf(ArtItemData(),ArtItemData("sss","ww",31.1,"ss",null,null)))
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is UploadOffer Fragment"
    }
    //    private val _listData = MutableLiveData<DatabaseResponce>().apply {
//        postValue(listOf(ArtListItem()))
    val text: LiveData<String> = _text
    val listData: LiveData<List<ArtItemData>> = _listData

    fun createNewItem(artItemData: ArtItemData) {
        itemsRepository.createNewItem(artItemData)
        //TODO("Not yet implemented")
    }
    fun getAllMyItems(name:String, uid : Int){
        itemsRepository.getAllMyItems(name,uid)
    }
}