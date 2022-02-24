package com.example.tubearhe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.tubearhe.model.Bear
import com.example.tubearhe.ui.theme.TuBearHeTheme
import com.example.tubearhe.viewModel.MainViewModel

class MainActivity : ComponentActivity() {

   val  mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TuBearHeTheme {
                // A surface container using the 'background' color from the theme

                        Surface(color = MaterialTheme.colors.background) {
                            BearList(bearList = mainViewModel.bearListResponse)
                            mainViewModel.getBearList()
                        }
            }
        }
    }

    @Composable
    fun BearList(bearList: List<Bear>) {
        var selectedIndex by remember {
            mutableStateOf(-1)
        }
        LazyColumn {
            itemsIndexed(items = bearList) { index, item ->
                BearItem(bear = item, index, selectedIndex) { i ->
                    selectedIndex = i
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BearItem(){
    val bear =Bear(
        "India Session Lager - Prototype Challenge",
        "https://images.punkapi.com/v2/keg.png",
        "BrewDog’s level of dry-hop to a beer formed with a baseline of 100% pilsner malt – and at under 4.5% ABV – gives you a style that flirts at the edges of several others. Think aromas of fresh cut grass, nettle, white grape, melon, tangerine - with similar flavours leading to a dry, bitter finish."
    )
    BearItem(bear = bear, 0 ,0){
        i ->
    }
}
    @Composable
    fun BearItem(bear: Bear, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {

        val backgroundColor =
            if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background

        Card(modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable { onClick(index) }
            .height(110.dp),

            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Surface(color = backgroundColor) {
                Row(modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()

                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = bear.image_url,

                            builder = {
                                scale(Scale.FILL)
                                placeholder(R.drawable.placeholder)
                                transformations(CircleCropTransformation())

                            }
                        ),
                        contentDescription = bear.description,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.2f)
                    )


                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight()
                            .weight(0.8f)
                    ) {
                        Text(
                            text = bear.name,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = bear.description,
                            style = MaterialTheme.typography.body1,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis)

                    }

                }

            }

        }
    }


