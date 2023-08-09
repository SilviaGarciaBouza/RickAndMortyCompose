package com.example.composeriky.UI

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*
import androidx.lifecycle.viewModelScope


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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeriky.R
import com.example.composeriky.data.RetrofitResponse
import com.example.composeriky.data.RikyItemResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext


@Composable
fun RikyScreem(viewModel: RikyViewModel){
    Box(modifier = Modifier.fillMaxSize()) {


        MyReciclerViewGrill(viewModel)
    }
}



//1º hacemos la clase de los items
data class RikyItem(
    var rikyName: String,
    @DrawableRes var rikyImage: Int
)

//2º la fun q retorne la list de items
fun returnListRikyList(): List<RikyItem> {
    return listOf(
        RikyItem("Riky", R.drawable.ic_launcher_background),
        RikyItem("Riky2", R.drawable.ic_launcher_background),
        RikyItem("Riky3", R.drawable.ic_launcher_background),
        RikyItem("Riky4", R.drawable.ic_launcher_background),
        RikyItem("Riky5", R.drawable.ic_launcher_background),
        RikyItem("Riky", R.drawable.ic_launcher_background),
        RikyItem("Riky2", R.drawable.ic_launcher_background),
        RikyItem("Riky3", R.drawable.ic_launcher_background),
        RikyItem("Riky4", R.drawable.ic_launcher_background),
        RikyItem("Riky5", R.drawable.ic_launcher_background),
        RikyItem("Riky", R.drawable.ic_launcher_background),
        RikyItem("Riky2", R.drawable.ic_launcher_background),
        RikyItem("Riky3", R.drawable.ic_launcher_background),
        RikyItem("Riky4", R.drawable.ic_launcher_background),
        RikyItem("Riky5", R.drawable.ic_launcher_background)

    )
}

//3º Se dibuja el item

@Composable
fun RikyItemCard( onClickItem: (RikyItemResponse) -> Unit, rikiResponse: RikyItemResponse) {
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
            Text(text = rikiResponse.rikiName, modifier = Modifier.padding(4.dp).align(alignment = Alignment.CenterHorizontally))

        }
    }
}





//4ª ReciclerView

@OptIn(ExperimentalComposeApi::class)
@Composable
fun MyReciclerViewGrill(viewModel: RikyViewModel){
    val context = LocalContext.current
//fixed el nº de columnas, .Adptive(80.dp) si lo quieres por medida
    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(2), content = {
        //items(CoroutineScope(Dispatchers.IO).launch { viewModel.doListRikyListUseCase() }){
        items(viewModel.callList() ){
            RikyItemCard( onClickItem = { Toast.makeText(context, it.rikiName, Toast.LENGTH_SHORT).show() },it )
        }
        //contentOaddings es el margen en los bordes
    }, contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp ))

}
