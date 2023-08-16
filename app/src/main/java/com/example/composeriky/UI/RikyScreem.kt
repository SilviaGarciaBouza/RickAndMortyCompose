package com.example.composeriky.UI

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*
import androidx.lifecycle.viewModelScope

import android.widget.ProgressBar
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import com.example.composeriky.R
import com.example.composeriky.data.RetrofitResponse
import com.example.composeriky.data.RikyItemResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

/*
@Composable
fun RikyScreem(viewModel: RikyViewModel){
    Box(modifier = Modifier.fillMaxSize()) {


        MyReciclerViewGrill(viewModel)
    }
}

*/
@Composable
fun RikyScreem(viewModel: RikyViewModel) {
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

                MyReciclerViewGrill(viewModel)
            }
        }
    }
}


// Se dibuja el item

@Composable
fun RikyItemCard(onClickItem: (RikyItemResponse) -> Unit, rikiResponse: RikyItemResponse) {
    Card(
        border = BorderStroke(1.dp, Color.DarkGray),
        modifier = Modifier
            .width(200.dp)
            .clickable { onClickItem(rikiResponse) }
            .padding(top = 8.dp, bottom = 8.dp, end = 16.dp, start = 16.dp)) {
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
                    .align(alignment = Alignment.CenterHorizontally)
            )

        }
    }
}


// ReciclerView
@OptIn(ExperimentalComposeApi::class)
@Composable
fun MyReciclerViewGrill(viewModel: RikyViewModel) {
    val context = LocalContext.current
//fixed el nº de columnas, .Adptive(80.dp) si lo quieres por medida
    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(2), content = {
        //items(CoroutineScope(Dispatchers.IO).launch { viewModel.doListRikyListUseCase() }){
        items(viewModel.callList()) {
            RikyItemCard(onClickItem = {
                Toast.makeText(context, it.rikiName, Toast.LENGTH_SHORT).show()
            }, it)
        }
        //contentOaddings es el margen en los bordes
    }, contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp))

}

@Preview
@Composable
fun MyProgressBar(){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.Green, strokeWidth = 6.dp, modifier = Modifier.testTag("Progress"))
        }
    }


