package com.paysafe.hackathon.thesesame.ui.fragments.portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paysafe.hackathon.thesesame.data.model.ArtItemData
import com.paysafe.hackathon.thesesame.data.repositories.ItemsRepository

class PortfolioViewModel(
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    private val _listData = MutableLiveData<List<ArtItemData>>().apply {
        postValue(listOf(ArtItemData(), ArtItemData("sss","ww",31.1,"ss")))
    }

    val listData: LiveData<List<ArtItemData>> = _listData
}