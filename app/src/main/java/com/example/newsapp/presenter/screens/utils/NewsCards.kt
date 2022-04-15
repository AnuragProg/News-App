package com.example.newsapp.presenter.screens.utils



import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presenter.navigationcomponents.Destinations
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.example.newsapp.R

@ExperimentalMaterialApi
@Composable
fun NewsCard(
    navController: NavController,
    it: Article
){
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        onClick = {
            var url = it.url

            if(!url.startsWith("https://")&& !url.startsWith("http://")){
                url = "http://$url"
            }
            val intent = Intent(Intent.ACTION_VIEW).apply{
                data = Uri.parse(url)
            }
            Log.d("urls", "url is $url")
            try{
                startActivity(context, intent, null)
            }catch(e: ActivityNotFoundException){
                Toast.makeText(context, "Browser Not Found", Toast.LENGTH_SHORT).show()
            }
        },
        shape = RoundedCornerShape(10.dp)
    ) {

        AsyncImage(
            model = it.urlToImage,
            placeholder = painterResource(id = R.drawable.loading_news_placeholder),
            contentDescription = "News Image",

            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = it.title,
                fontSize = 17.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
            )
        }
    }
}