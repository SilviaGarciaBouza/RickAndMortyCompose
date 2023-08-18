package com.example.composeriky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeriky.UI.RikyDetailScreem
import com.example.composeriky.UI.RikyScreem
import com.example.composeriky.UI.RikyViewModel
import com.example.composeriky.data.RikyItemResponse
import com.example.composeriky.ui.theme.ComposeRikyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //Le inyecyo el viewModel
    private val rviewModel: RikyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRikyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //RikyScreem(rviewModel)
                    //pro Navigation:2 crea la navegación
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.Screem1.route){
                        composable(Routes.Screem1.route){ RikyScreem(rviewModel,navigationController) }
                        composable(Routes.Screem2.route,
                            arguments = listOf(navArgument("id"){type= NavType.IntType})
                        ){backStackEntry ->
                            RikyDetailScreem(rviewModel,
                                navigationController,
                                backStackEntry.arguments?.getInt("id" ) ?: 1
                                )}
                    }

                }
            }
        }
    }




    sealed class Routes(val route: String){
        object Screem1: Routes("Screem1")
        object Screem2: Routes("Screem2/{id}"){
            fun createRoute(id: Int) = "Screem2/$id"
        }
    }

}





