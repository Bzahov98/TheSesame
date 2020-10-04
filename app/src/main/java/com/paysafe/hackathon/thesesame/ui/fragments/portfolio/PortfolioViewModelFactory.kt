package com.paysafe.hackathon.thesesame.ui.fragments.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paysafe.hackathon.thesesame.data.repositories.ItemsRepository

class PortfolioViewModelFactory(
    private val itemsRepository: ItemsRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PortfolioViewModel(itemsRepository) as T
    }
}