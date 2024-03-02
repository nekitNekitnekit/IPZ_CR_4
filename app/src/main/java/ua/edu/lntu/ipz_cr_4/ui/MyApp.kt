package ua.edu.lntu.ipz_cr_4.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ua.edu.lntu.ipz_cr_4.R

enum class MyScreen {
    CATEGORY,
    ID
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    currentScreen: MyScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    @StringRes headerResId: Int,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = headerResId),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(
    windowSize: WindowWidthSizeClass,
    viewModel: MyViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyScreen.valueOf(
        backStackEntry?.destination?.route ?: MyScreen.CATEGORY.name
    )

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MyAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentScreen = currentScreen,
                headerResId = if (currentScreen == MyScreen.CATEGORY)
                    R.string.app_name
                else
                    uiState.headerTitleId
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyScreen.CATEGORY.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyScreen.CATEGORY.name) {
                CategoryListScreen(
                    categoryList = uiState.categoryList,
                    onCardClick = {
                        viewModel.setCategory(it)
                        navController.navigate(MyScreen.ID.name)
                    }
                )
            }
            composable(route = MyScreen.ID.name) {
                IdScreen(
                    idcategory = uiState.currentCategory
                )
            }
        }

    }
}