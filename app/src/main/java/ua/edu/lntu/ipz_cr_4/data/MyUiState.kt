package ua.edu.lntu.ipz_cr_4.data

import androidx.annotation.StringRes
import ua.edu.lntu.ipz_cr_4.R
import ua.edu.lntu.ipz_cr_4.model.Category
import ua.edu.lntu.ipz_cr_4.model.Id

data class MyUiState (
    val currentCategory: Category = DataSource.defaultCategory,
    val currentCategory_id: Category_id = DataSource.defaultCategory_id,
    val categoryList: List<Category> = emptyList(),
    val category_idList: List<Category_id> = emptyList(),
    @StringRes val headerTitleId: Int = R.string.app_name,
    val isShowingListPage: Boolean = true
)