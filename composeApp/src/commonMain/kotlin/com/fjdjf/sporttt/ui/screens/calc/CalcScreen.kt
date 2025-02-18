package com.fjdjf.sporttt.ui.screens.calc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
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
import com.fjdjf.sporttt.ui.components.lightGrey
import com.fjdjf.sporttt.ui.components.noRippleClickable
import com.fjdjf.sporttt.ui.components.red
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val categories = listOf("Poor", "Average", "Good", "Excellent")
val cals = listOf("0-500", "500-1000", "1000-1500", "1500+")
val waters = listOf("0-0.5L", "0.5L-1L", "1L-3L", "3L+")
val burns = listOf("500-750", "750-1000", "1000-2000", "2000+")

@Composable
fun CalcScreen(
    addDay: (Day) -> Unit
) {
    var well by remember { mutableStateOf(-1) }
    var kcal by remember { mutableStateOf(-1) }
    var water by remember { mutableStateOf(-1) }
    var burned by remember { mutableStateOf(-1) }

    val today = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date }

    var isWellDialog by remember { mutableStateOf(false) }
    var isKcalDialog by remember { mutableStateOf(false) }
    var isWaterDialog by remember { mutableStateOf(false) }
    var isBurnedDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        BaseScreen("Diary") {
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(grey).padding(20.dp)
                    .noRippleClickable {
                        isWellDialog = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (well != -1) categories[well] else "Well-being",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Icon(Icons.Rounded.KeyboardArrowRight, "",
                    tint = Color.White, modifier = Modifier.size(34.dp))
            }
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(grey).padding(20.dp)
                    .noRippleClickable {
                        isKcalDialog = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (kcal != -1) cals[kcal] else "Kcal eaten",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Icon(Icons.Rounded.KeyboardArrowRight, "",
                    tint = Color.White, modifier = Modifier.size(34.dp))
            }
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(grey).padding(20.dp)
                    .noRippleClickable {
                        isWaterDialog = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (water != -1) waters[water] else "Water drinked",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Icon(Icons.Rounded.KeyboardArrowRight, "",
                    tint = Color.White, modifier = Modifier.size(34.dp))
            }
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(grey).padding(20.dp)
                    .noRippleClickable {
                        isBurnedDialog = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (burned != -1) burns[burned] else "Kcal burned",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Icon(Icons.Rounded.KeyboardArrowRight, "",
                    tint = Color.White, modifier = Modifier.size(34.dp))
            }
            Spacer(Modifier.height(40.dp))
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(
                        if (well != -1 && burned != -1 && water != -1 && kcal != -1) red else grey
                    ).padding(20.dp)
                    .noRippleClickable {
                        addDay(
                            Day(
                                date = today.dayOfMonth.toString(),
                                well = categories[well],
                                water = waters[water],
                                kcal = cals[kcal],
                                burned = burns[burned]
                            )
                        )
                        well = -1
                        burned = -1
                        water = -1
                        kcal = -1
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Save",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        if (isBurnedDialog){
            WellDialog(burns, {burned = it
            isBurnedDialog = false})
        }
        if (isKcalDialog){
            WellDialog(cals, {kcal = it
            isKcalDialog = false})
        }
        if (isWaterDialog){
            WellDialog(waters, {water = it
            isWaterDialog = false})
        }
        if (isWellDialog){
            WellDialog(categories, {well = it
            isWellDialog = false})
        }
    }

}

@Composable
fun WellDialog(
    cat: List<String>,
    choose: (Int) -> Unit
) {

    var chosen by remember { mutableStateOf(-1) }

    Column(
        Modifier.fillMaxSize().background(Color.Black.copy(0.7f))
    ) {
        Spacer(Modifier.weight(1f))
        Column(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                .background(grey).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            cat.forEach {
                Box(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                        .background(if (chosen == cat.indexOf(it)) red else lightGrey)
                        .padding(20.dp)
                        .noRippleClickable {
                            chosen = cat.indexOf(it)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(it, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Medium)
                }

            }
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                    .background(red).padding(20.dp)
                    .noRippleClickable {
                        choose(chosen)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Save",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}