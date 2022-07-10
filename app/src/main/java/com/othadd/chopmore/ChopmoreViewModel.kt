package com.othadd.chopmore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChopmoreViewModel() : ViewModel() {

    private var _inLoginMode = MutableLiveData<Boolean>()
    val inLoginMode: LiveData<Boolean> get() = _inLoginMode
    fun enterLoginMode(choice: Boolean) {
        _inLoginMode.value = choice
    }


    val categoriesList = mutableListOf<Category>()
    val cakesList = mutableListOf<Cake>()
    val scrollItemList = mutableListOf<ScrollItem>()

    fun updateScrollItems(position: Int) {
        for ((counter, scrollItem) in scrollItemList.withIndex()) {
            scrollItem.selected = counter == position
        }
    }

    init {
        _inLoginMode.value = true
        generateCategories()
        generateCakes()
        generateScrollItems()
    }

    private fun generateCategories() {
        categoriesList.add(Category("Cake", R.drawable.emojione_v1_birthday_cake))
        categoriesList.add(Category("Burger", R.drawable.emojione_hamburger))
        categoriesList.add(Category("Pizza", R.drawable.emojione_pizza))
        categoriesList.add(Category("Drink", R.drawable.drinks))
    }

    private fun generateCakes() {
        cakesList.add(Cake("Yummy Queens Cake", R.drawable.unsplash_ao09kk2ovb0, "#25000"))
        cakesList.add(Cake("Druid Berry Cake", R.drawable.unsplash_g8gx6e2wyli, "#55000"))
        cakesList.add(Cake("Black Forest Cake", R.drawable.unsplash_clpdea23z44, "#35000"))
        cakesList.add(Cake("Pineapple Upsidedown Cake", R.drawable.unsplash_gx_vsimrgzk, "#50000"))
        cakesList.add(Cake("Carrot Cake", R.drawable.unsplash_ofddiqx8cz8, "#25000"))
        cakesList.add(Cake("Strawberry Short Cake", R.drawable.unsplash_vt5xrj3z1oe, "#45000"))
    }

    private fun generateScrollItems() {
        var counter = 1
        for (cake in cakesList) {
            scrollItemList.add(ScrollItem(counter == 1))
            counter++
        }
    }
}

class ChopmoreViewModelFactory :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChopmoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChopmoreViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

data class Category(val name: String, val picture: Int)
data class Cake(val name: String, val picture: Int, val price: String)
data class ScrollItem(var selected: Boolean)

