package com.example.artspaceapp

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var length = 4
    var isEnd : Boolean = false
    var id by remember { mutableStateOf(1) }
    if(id < 1 ) {
        id = 1
    }

    val painterID = when(id) {
        1 -> R.drawable.molisa
        2 -> R.drawable.molisa
        3 -> R.drawable.molisa
        else -> R.drawable.molisa
    }

    val artistID = when(id) {
        1 -> R.string.artwork_artist
        2 -> R.string.artwork_artist
        3 -> R.string.artwork_artist
        else -> R.string.artwork_artist
    }

    val titleID = when(id) {
        1 -> R.string.artowrk_title
        2 -> R.string.artowrk_title
        3 -> R.string.artowrk_title
        else -> R.string.artowrk_title
    }

    if (id >= length) {
        isEnd = true
    }

    Column(
        modifier = modifier.padding(24.dp),
        //horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    )
    {
        ArtworkWallDisplay(
            modifier = modifier
                .padding(24.dp),
            painterID = painterID,
        )

        ArtworkDescriptor(
            modifier = modifier
                .padding(24.dp),
            artistID = artistID,
            titleID = titleID,
        )

        ButtonDisplay(
            modifier = modifier,
            onClickNexFunction = { id++ },
            onClickPreFunction = { id-- },
            id = id,
            isEnd = isEnd,
        )



    }
}

@Composable
fun ArtworkWallDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes painterID : Int,
) {

    Row(
        modifier = modifier.padding(bottom = 16.dp),

    ) {
        Image(
            painter = painterResource(id = painterID),
            contentDescription = null,
            modifier = Modifier.padding(24.dp),
        )
    }

}

@Composable
fun ArtworkDescriptor(
    modifier : Modifier = Modifier,
    @StringRes artistID : Int,
    @StringRes titleID : Int,
    ) {

    val artworkArtist : String = stringResource(id = artistID)
    val name = artworkArtist.substringBefore("(")
    val year = artworkArtist.substring(startIndex = name.length)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)

    ) {
        Text(
            text = stringResource(id = titleID),
            modifier = Modifier.padding(
                bottom = 8.dp,
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
            ),
            fontSize = 24.sp,
            fontWeight = FontWeight.Light,

        )
        Row(
            modifier = Modifier
                .padding(
                    bottom = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                ),
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = year,
                fontWeight = FontWeight.ExtraLight
            )
        }

    }
}

@Composable
fun ButtonDisplay(
    modifier: Modifier = Modifier,
    onClickPreFunction : ()-> Unit,
    onClickNexFunction : ()-> Unit,
    isEnd: Boolean,
    id: Int,
    ) {
    Row {

        if(id == 1) {
            Spacer(modifier = modifier.weight(0.4f))
        }
        else {
            Button(
                onClick = onClickPreFunction,
                modifier = modifier.weight(0.4f),
            ) {
                Text(text = "Previous")
            }
        }


        Spacer(modifier = modifier.weight(0.2f))

        if(isEnd) {
            Spacer(modifier = modifier.weight(0.4f))
        }
        else{
            Button(
                onClick = onClickNexFunction,
                modifier = modifier.weight(0.4f),
            ) {
                Text(text = "Next")
            }
        }

        Text(text = "")

    }
}


@Preview(
    showBackground = true,
    showSystemUi =  true,
    )
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        Greeting(modifier = Modifier)
    }
}