package ua.edu.lntu.ipz_cr_4.data

import androidx.annotation.StringRes
import ua.edu.lntu.ipz_cr_4.R
import ua.edu.lntu.ipz_cr_4.model.Category
import ua.edu.lntu.ipz_cr_4.model.Id

data class MyUiState (
    val currentCategory: Category = DataSource.defaultCategory,
    val categoryList: List<Category> = emptyList(),
    @StringRes val headerTitleId: Int = R.string.app_name,
    val isShowingListPage: Boolean = true
)