package ua.edu.lntu.ipz_cr_4.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MyUiState(categoryList = DataSource.getCategoryData()))
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    fun setCategory(selectedCategory: Category) {
        _uiState.update {
            it.copy(
                currentCategory = selectedCategory,
                headerTitleId = selectedCategory.titleResourceId
            )
        }
        updateIDListData(selectedCategory.titleResourceId)
    }

    fun setID(selectedID: ID) {
        _uiState.update {
            it.copy(currentID = selectedID)
        }
    }

}