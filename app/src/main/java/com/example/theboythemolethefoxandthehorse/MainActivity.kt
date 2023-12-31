package com.example.theboythemolethefoxandthehorse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theboythemolethefoxandthehorse.data.DataSource
import com.example.theboythemolethefoxandthehorse.model.Quote
import com.example.theboythemolethefoxandthehorse.presentation.QuoteDialog
import com.example.theboythemolethefoxandthehorse.ui.theme.TheBoyTheMoleTheFoxAndTheHorseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheBoyTheMoleTheFoxAndTheHorseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuoteApp()
                }
            }
        }
    }
}

@Composable
fun QuoteApp(
    modifier: Modifier = Modifier,
) {
    val quoteList = DataSource().loadQuotes()


    val isDialogVisible = remember { mutableStateOf(false) }
    val selectedQuote = remember { mutableStateOf(quoteList[0]) }

    QuoteList(
        modifier = Modifier
            .alpha(
                if (isDialogVisible.value) {
                    0.5f
                } else {
                    1f
                }
            ),
        quoteList = quoteList,
        onClick = {quote: Quote ->
            isDialogVisible.value = true
            selectedQuote.value = quote
        }
    )

    if (isDialogVisible.value) {
        QuoteDialog(
            quote = selectedQuote.value,
            onDismiss = {
                isDialogVisible.value = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteList(
    modifier: Modifier = Modifier,
    quoteList: List<Quote>,
    onClick: (Quote) -> Unit,
    ) {

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "The Boy, The Mole, The Fox, and The Horse")
                }
            )
        }
    ){

        Surface(
            modifier = modifier
                .padding(it)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp)
            ) {

                items(quoteList) { quote: Quote ->

                    ClickableQuoteCard(
                        quote = quote,
                        onClick = onClick
                    )

                }

            }

        }

    }


}


@Composable
fun ClickableQuoteCard(
    onClick: (Quote) -> Unit,
    quote: Quote,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable(
                onClick = { onClick(quote) }
            )
    ) {
        Column {
            Image(
                painter = painterResource(quote.imageResourceId),
                contentDescription = stringResource(quote.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

/*
@Composable
fun QuoteCard(quote: Quote, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(quote.imageResourceId),
                contentDescription = stringResource(quote.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(quote.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

 */

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheBoyTheMoleTheFoxAndTheHorseTheme {
        /*
        QuoteCard(
            quote = Quote(stringResourceId = R.string.quote1, imageResourceId = R.drawable.image1)
        )

         */

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val list = listOf(
                DataSource().loadQuotes()[0],
                DataSource().loadQuotes()[1]
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp)
            ) {

                items(list) {quote: Quote ->

                    ClickableQuoteCard(onClick = {}, quote = quote)

                }
            }

        }
    }
}