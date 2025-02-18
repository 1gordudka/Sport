package com.fjdjf.sporttt.ui.screens.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjdjf.sporttt.data.local.InfoItem
import com.fjdjf.sporttt.data.local.sportInfoList
import com.fjdjf.sporttt.ui.components.BaseScreen
import com.fjdjf.sporttt.ui.components.grey
import com.fjdjf.sporttt.ui.components.lightGrey
import com.fjdjf.sporttt.ui.components.noRippleClickable
import org.jetbrains.compose.resources.painterResource

@Composable
fun InfoScreen() {

    var isFacts by remember { mutableStateOf(true) }

    BaseScreen("Facts & Tips") {

        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp)).background(grey)
                .padding(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(30.dp))
                    .background(if (isFacts) lightGrey else grey)
                    .noRippleClickable { isFacts = true }
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Facts", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            Box(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(30.dp))
                    .background(if (isFacts) grey else lightGrey)
                    .noRippleClickable { isFacts = false }
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Tips", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
        }

        LazyColumn {
            items(sportInfoList.filter { if (isFacts) it is InfoItem.Fact else it is InfoItem.Tip }){
                when(it){
                    is InfoItem.Fact -> {
                        FactCard(it)
                    }
                    is InfoItem.Tip -> {
                        TipCard(it)
                    }
                }
            }
        }

    }

}

@Composable
fun TipCard(
    tip: InfoItem.Tip
) {

    Column(
        modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth().clip(RoundedCornerShape(16.dp))
            .background(tip.color.copy(alpha = 0.5f)).padding(20.dp)
    ) {
        Text(tip.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(Modifier.height(20.dp))
        Text(tip.description, fontWeight = FontWeight.Medium, fontSize = 16.sp, color = Color.White)
        Spacer(Modifier.height(20.dp))
        Text(tip.level, fontSize = 14.sp, color = Color.White.copy(alpha = 0.5f))
    }
}

@Composable
fun FactCard(
    fact: InfoItem.Fact
) {
    Column(
        modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth().clip(RoundedCornerShape(16.dp))
            .paint(painter = painterResource(fact.pic), contentScale = ContentScale.Crop)
            .background(Color.Black.copy(alpha = 0.5f)).padding(20.dp)
    ) {
        Text(fact.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(Modifier.height(20.dp))
        Text(
            fact.description,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}