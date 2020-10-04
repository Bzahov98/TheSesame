package com.paysafe.hackathon.thesesame.ui.fragments.myArtItems

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paysafe.hackathon.thesesame.data.repositories.ItemsRepository

class MyArtItemsViewModelFactory(
    private val itemsRepository: ItemsRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyArtItemsViewModel(itemsRepository) as T
    }
}