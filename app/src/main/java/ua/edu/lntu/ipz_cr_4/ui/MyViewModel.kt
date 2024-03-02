package ua.edu.lntu.ipz_cr_4.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ua.edu.lntu.ipz_cr_4.R
import ua.edu.lntu.ipz_cr_4.data.DataSource
import ua.edu.lntu.ipz_cr_4.data.MyUiState
import ua.edu.lntu.ipz_cr_4.model.Category
import ua.edu.lntu.ipz_cr_4.model.Id

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
        updateIdListData(selectedCategory.titleResourceId)
    }

    fun setId(selectedID: Id) {
        _uiState.update {
            it.copy(currentId = selectedId)
        }
    }

    private fun updateCategoryListData(@StringRes categoryTitleId: Int) {

        val recommendationList: List<Category> = when (categoryTitleId) {
            R.string.category1 -> DataSource.getCategory1Data()
            else -> DataSource.getCategoryData()
        }

        _uiState.update {
            it.copy(categoryList = categoryList)
        }
    }

}