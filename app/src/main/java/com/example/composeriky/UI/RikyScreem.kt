package com.example.composeriky.UI

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import kotlinx.coroutines.*

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.lifecycle.repeatOnLifecycle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.composeriky.MainActivity
import com.example.composeriky.data.RikyItemResponse
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.coroutines.coroutineContext

@ExperimentalMaterial3Api
@Composable
fun RikyScreem(viewModel: RikyViewModel,navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {


        val lifecycle = LocalLifecycleOwner.current.lifecycle
        val uiState by produceState<ImagesUiStates>(initialValue =ImagesUiStates.Loading , key1 = lifecycle, key2 = viewModel)
      {
            lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.usiState.collect{value = it}
            }
        }
        when (uiState) {
            is ImagesUiStates.Error -> Toast.makeText(
                LocalContext.current,
                "Error, try again",
                Toast.LENGTH_SHORT
            ).show()

            is ImagesUiStates.Loading -> MyProgressBar()

            is ImagesUiStates.Success -> {

                MyReciclerViewGrill(viewModel, navController )
            }
        }
    }
}




// Se dibuja el item

@Composable
fun RikyItemCard(onClickItem: (RikyItemResponse) -> Unit, rikiResponse: RikyItemResponse) {
    val context = LocalContext.current

    Card(
        border = BorderStroke(1.dp, Color.DarkGray),
        modifier = Modifier
            // .pointerInput(Unit){detectTapGestures (onLongPress = {Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()})}
            .width(200.dp)
            .clickable { onClickItem(rikiResponse) }
            .padding(top = 8.dp, bottom = 8.dp, end = 16.dp, start = 16.dp))
    {
        Column() {
            AsyncImage(
                model = rikiResponse.rikiImage,
                contentDescription = "riky image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = rikiResponse.rikiName,
                modifier = Modifier
                    .padding(4.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                fontSize = 22.sp,
                maxLines = 1
            )

        }
    }
}

@ExperimentalMaterial3Api
// ReciclerView
@OptIn(ExperimentalComposeApi::class)
@Composable
fun MyReciclerViewGrill(viewModel: RikyViewModel, navController: NavController) {
    val context = LocalContext.current
//fixed el nº de columnas, .Adptive(80.dp) si lo quieres por medida
    Column(modifier = Modifier.fillMaxSize()) {
        MyTopAppBar(navController, false)
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            content = {
                //items(CoroutineScope(Dispatchers.IO).launch { viewModel.doListRikyListUseCase() }){
                items(viewModel.callList()) {
                    RikyItemCard(onClickItem =
                    { navController.navigate(MainActivity.Routes.Screem2.createRoute(id = it.rikiId)) }
                        // Toast.makeText(context, it.rikiName, Toast.LENGTH_SHORT).show()
                        , it)
                }
                //contentOaddings es el margen en los bordes
            },
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
        )
    }

}

@Preview
@Composable
fun MyProgressBar(){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.Green, strokeWidth = 6.dp, modifier = Modifier.testTag("Progress"))
        }
    }




@ExperimentalMaterial3Api
@Composable
fun RikyDetailScreem(viewModel: RikyViewModel, navController: NavController, id:Int){
    Card(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()) {
            MyTopAppBar(navController, true)
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(4f)){
                   AsyncImage(
                        model = viewModel.callList().get(id).rikiImage,
                        contentDescription = "riky image",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )}
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), Alignment.Center) {
                Text(
                    text = viewModel.callList().get(id).rikiName,
                    modifier = Modifier
                        .padding(4.dp),
                    fontSize = 32.sp,
                )
            }
        }

    }

}




@ExperimentalMaterial3Api
@Composable
fun MyTopAppBar(navController: NavController, isVisible: Boolean) {



    TopAppBar(
        title = { Text(text = "Ricky And Morty Characteres") },
        modifier = Modifier.fillMaxWidth(),
       //backgroundColor = Color.DarkGray,
       // contentColor = Color.White,
       // elevation = 4.dp,
        //a la izda:
        navigationIcon = {
            if (isVisible) {
                IconButton(onClick = { navController.navigate("Screem1") }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")

                }
            }

        }
    )


}
