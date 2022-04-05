package com.example.beginningjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var shouldShowOnBoarding by rememberSaveable{ mutableStateOf(true)}

    if (shouldShowOnBoarding) OnBoarding { shouldShowOnBoarding = !shouldShowOnBoarding } else Greetings()
}

@Composable
fun Greetings(names: List<String> = listOf(
    "ZOE", "Jeff", "John", "Doe","Ben","Kiff","Sophie","Bash","Keen","Queen","Josh","Mukari","Tari")){
    LazyColumn{
        items(names){name->
            SayHello(name = "${names.indexOf(name)}")
        }
    }

}

@Composable
fun OnBoarding(show: ()->Unit){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics CodeLab!")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { show() }) {
                Text("Continue")
            }
        }
        
    }
}

@Composable
fun SayHello(name: String) {
    var expanded by remember{ mutableStateOf(false)}

    val extraPadding by animateDpAsState(
        if(expanded) 48.dp else 0.dp
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(4.dp, 2.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
//            .padding(extraPadding)
        ){
            Column(
                modifier = Modifier.weight(2F)
            ){
                Text(
                    text = "Hello,"
                )

                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(text = if(expanded) stringResource(R.string.dummy_text) else "")
            }
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector =
                    if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription =
                    if (expanded) stringResource(id = R.string.show_less) else stringResource(
                        id = R.string.show_more
                    ) )
            }

        }


    }
}

@Composable
@Preview(showBackground = true, name = "Function Preview")
fun PreviewSayHello() {
    MyApp()
}


