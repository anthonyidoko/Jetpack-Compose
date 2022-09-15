package com.example.jetpackcompose

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MyApp()
        }
    }
}


@Composable
fun MyApp() {
    JetpackComposeTheme {
        Scaffold(
            bottomBar = { ScreenBottomNav() }
        ) {
            LayoutBasics(Modifier.padding(it))
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        backgroundColor = Color.LightGray
    ) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = title).uppercase(),
                modifier = modifier.padding(8.dp)
            )
            content()
        }
    }
}

@Composable
fun LayoutBasics(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SearchBar()
        Spacer(modifier = Modifier.height(10.dp))
        HomeSection(title = R.string.align_body) {
            AlignYourBodyScrollable()
        }
        Spacer(modifier = Modifier.height(10.dp))
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsScrollableGrid(modifier = Modifier)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    var searchText by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp), value = searchText, onValueChange = { searchText = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search)
            )
        },
        placeholder = { Text(text = stringResource(id = R.string.search)) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray,
            textColor = Color.Black
        )
    )
}

@Composable
fun AlignYourBody(
    modifier: Modifier = Modifier,
    image: Painter = painterResource(id = R.drawable.three),
    imageDes: String = "Image",
    description: String = "Inversion"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = modifier
                .size(88.dp)
                .clip(shape = CircleShape),
            painter = image, contentDescription = imageDes,
            contentScale = ContentScale.Crop
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body2,
            modifier = modifier.paddingFromBaseline(top = 12.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavouriteCollection(
    modifier: Modifier = Modifier,
    cardImage: Painter = painterResource(id = R.drawable.one),
    imageName: String? = null,
    cardText: String = "Nature meditations"
) {
    Row(
        modifier = modifier
            .width(192.dp)
            .height(64.dp)
            .background(color = Color.LightGray)
            .padding(5.dp),

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            backgroundColor = Color.White,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = cardImage,
                    contentDescription = imageName,
                )
                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = cardText,
                    modifier = Modifier.weight(3f)
                )
            }
        }
    }
}

val content1Data = listOf(
    AlignBody(R.drawable.one, "Inversion"),
    AlignBody(R.drawable.three, "Divergence"),
    AlignBody(R.drawable.four, "Relegation"),
    AlignBody(R.drawable.five, "Completion"),
    AlignBody(R.drawable.six, "Completion"),
    AlignBody(R.drawable.seven, "Completion"),
)

@Composable
fun AlignYourBodyScrollable(
    modifier: Modifier = Modifier,
    contentItems: List<AlignBody> = content1Data
) {
    LazyRow(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = contentItems) {
            AlignYourBody(
                modifier = modifier,
                image = painterResource(id = it.image),
                description = it.title
            )
        }
    }
}

val favoriteCollections = listOf(
    FavCols(R.drawable.six, "Fav Coll 1"),
    FavCols(R.drawable.seven, "Fav Coll 2"),
    FavCols(R.drawable.eight, "Fav Coll 3"),
    FavCols(R.drawable.nine, "Fav Coll 4"),
    FavCols(R.drawable.ten, "Fav Coll 5"),
    FavCols(R.drawable.one, "Fav Coll 6"),
)

val favoriteCollectionsGrid = listOf(
    FavCols(R.drawable.six, "Fav Coll 1"),
    FavCols(R.drawable.seven, "Fav Coll 2"),
    FavCols(R.drawable.eight, "Fav Coll 3"),
    FavCols(R.drawable.nine, "Fav Coll 4"),
    FavCols(R.drawable.ten, "Fav Coll 5"),
    FavCols(R.drawable.one, "Fav Coll 6"),
    FavCols(R.drawable.six, "Fav Coll 1"),
    FavCols(R.drawable.seven, "Fav Coll 2"),
    FavCols(R.drawable.eight, "Fav Coll 3"),
    FavCols(R.drawable.nine, "Fav Coll 4"),
    FavCols(R.drawable.ten, "Fav Coll 5"),
    FavCols(R.drawable.one, "Fav Coll 6"),
)

@Composable
fun FavoriteCollectionsScrollable(
    modifier: Modifier = Modifier,
    itemData: List<FavCols> = favoriteCollectionsGrid
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(itemData) {
            FavouriteCollection(
                modifier = modifier,
                cardImage = painterResource(id = it.image),
                cardText = it.title
            )
        }
    }
}

@Composable
fun FavoriteCollectionsScrollableGrid(
    modifier: Modifier = Modifier,
    itemDatas: List<FavCols> = favoriteCollectionsGrid
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(120.dp)
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(itemDatas) {
            FavouriteCollection(
                cardImage = painterResource(id = it.image),
                cardText = it.title
            )
        }
    }
}

@Composable
fun ScreenBottomNav(
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background
    ) {
        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.Spa, contentDescription = null)
            },
            label = { Text(text = stringResource(id = R.string.home)) },
            selected = true, onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) },
            label = { Text(text = stringResource(id = R.string.profile)) },
            selected = false, onClick = { /*TODO*/ }
        )
    }
}

data class AlignBody(val image: Int, val title: String)
data class FavCols(val image: Int, val title: String)


@Composable
@Preview(showBackground = true)
fun PrevApp() {
    MyApp()
}






