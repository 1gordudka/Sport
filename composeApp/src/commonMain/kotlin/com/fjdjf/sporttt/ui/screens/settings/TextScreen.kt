package com.fjdjf.sporttt.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjdjf.sporttt.ui.components.noRippleClickable

@Composable
fun TextScreen(
    text: String,
    back: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black)
            .systemBarsPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Icon(Icons.Rounded.KeyboardArrowLeft, "",
                tint = Color.White, modifier = Modifier.size(34.dp)
                    .noRippleClickable { back() })
        }

        Spacer(Modifier.height(20.dp))
        LazyColumn {
            item{
                Text(text, fontSize = 16.sp, color = Color.White)
            }
        }
    }
}