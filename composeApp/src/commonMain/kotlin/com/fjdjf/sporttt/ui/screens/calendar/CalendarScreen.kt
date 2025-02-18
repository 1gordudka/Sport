package com.fjdjf.sporttt.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjdjf.sporttt.data.db.Day
import com.fjdjf.sporttt.ui.components.BaseScreen
import com.fjdjf.sporttt.ui.components.grey
import com.fjdjf.sporttt.ui.components.noRippleClickable
import com.fjdjf.sporttt.ui.screens.calc.categories
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

@Composable
fun CalendarScreen(
    days: List<Day>
) {

    val today = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date }
    val daysList = remember {
        (0 until 30).map { today.plus(it, DateTimeUnit.DAY) }
    }
    var chosenDay by remember { mutableStateOf(0) }
    var ind by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(true){
        chosenDay = 0
    }

    LaunchedEffect(chosenDay){
        val item = days.firstOrNull { it.date == daysList[chosenDay].dayOfMonth.toString() }
        if (item != null){
            ind = days.indexOf(item)
        }else{
            ind = null
        }
    }

    BaseScreen("Tracking Calendar"){
        LazyRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(daysList){
                DayTile(
                    day = it.dayOfMonth,
                    name = it.dayOfWeek.name.take(3).lowercase().capitalize(),
                    isChosen = daysList.indexOf(it) == chosenDay,
                    choose = {
                        chosenDay = daysList.indexOf(it)
                    }
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        if (ind != null){
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(grey).padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Kcal eaten",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Text(
                    days[ind!!].kcal,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(grey).padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Kcal burned",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Text(
                    days[ind!!].burned,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(grey).padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Water drunk",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Text(
                    days[ind!!].water,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(grey).padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Well-being",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Text(
                    days[ind!!].well,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}