package com.example.retrofitandcoilpracticeapp.userinterface.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.retrofitandcoilpracticeapp.R
import com.example.retrofitandcoilpracticeapp.model.MarsPhotos

@Composable
fun HomeScreen(marsUiState: MarsUiState,retryAction:()->Unit){
    when(marsUiState){
        is MarsUiState.Loading-> LoadingScreen()
        is MarsUiState.Error-> ErrorScreen(retryAction)
        is MarsUiState.Success-> PhotosGridScreen(marsUiState.photos)
    }
}
@Composable
fun LoadingScreen(){
    Image(painter = painterResource(R.drawable.loading_img), contentDescription ="", modifier = Modifier.size(200.dp) )
}
@Composable
fun ErrorScreen(retryAction: () -> Unit){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(R.drawable.connection_error), contentDescription = "", modifier = Modifier
            .size(200.dp)
            .align(Alignment.CenterHorizontally))
        Text(text = "Connection Error", fontSize = 16.sp)
    }

}
@Composable
fun PhotosGridScreen(photo:List<MarsPhotos>){
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), contentPadding = PaddingValues(4.dp), modifier = Modifier.padding(5.dp)){
        items(photo,key = {photo->photo.id}){
            photo->
            MarsPhotoCard(photo = photo,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f))
        }
    }
}

@Composable
fun MarsPhotoCard(photo: MarsPhotos, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(photo.imgsrc)
                .crossfade(true).build(),
            error = painterResource(R.drawable.connection_error),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


