package com.example.beginningjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.beginningjetpackcompose.ui.theme.BeginningJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeginningJetPackComposeTheme {

                MyApp()

            }
        }

    }
}


@Composable
fun MyApp() {
    ProfileCard()
}

@Composable
fun ProfileCard() {
    val context = LocalContext.current

    Row(
        Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                Toast
                    .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(12.dp)

    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .wrapContentSize(),
            painter = painterResource(R.drawable.tony),
            contentDescription = stringResource(id = R.string.my_photo)
        )
        Spacer(Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Text(
                text = stringResource(id = R.string.profile_name),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold
                )

            )
            /** LocalContentAlpha provides Opacity for content **/
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = R.string.profile_time),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewProfileCard() {
    ProfileCard()
}
