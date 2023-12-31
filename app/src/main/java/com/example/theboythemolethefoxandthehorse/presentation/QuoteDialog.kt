package com.example.theboythemolethefoxandthehorse.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.theboythemolethefoxandthehorse.model.Quote

@Composable
fun QuoteDialog(
    quote: Quote,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.5f)


        Card{
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

}