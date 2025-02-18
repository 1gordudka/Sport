package com.fjdjf.sporttt.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjdjf.sporttt.ui.components.grey
import com.fjdjf.sporttt.ui.components.noRippleClickable
import com.fjdjf.sporttt.ui.components.red

@Composable
fun DayTile(
    day: Int,
    name: String,
    isChosen: Boolean,
    choose: () -> Unit
) {

    Column(
        Modifier.clip(RoundedCornerShape(16.dp)).background(
            if (isChosen) red else grey
        ).padding(8.dp).noRippleClickable { choose() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(name, fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(10.dp))
        Text("$day", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Medium)
    }
}