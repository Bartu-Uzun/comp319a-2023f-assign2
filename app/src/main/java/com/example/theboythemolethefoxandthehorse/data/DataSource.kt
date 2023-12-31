package com.example.theboythemolethefoxandthehorse.data

import com.example.theboythemolethefoxandthehorse.R
import com.example.theboythemolethefoxandthehorse.model.Quote

class DataSource {
    fun loadQuotes(): List<Quote> {

        return listOf(
            Quote(R.string.quote1, R.drawable.image1),
            Quote(R.string.quote2, R.drawable.image2),
            Quote(R.string.quote3, R.drawable.image3),
            Quote(R.string.quote4, R.drawable.image4),
            Quote(R.string.quote5, R.drawable.image5),
            Quote(R.string.quote6, R.drawable.image6),
            Quote(R.string.quote7, R.drawable.image7),
            Quote(R.string.quote8, R.drawable.image8),
            Quote(R.string.quote9, R.drawable.image9),
            Quote(R.string.quote10, R.drawable.image10),
        )

    }
}