package com.fjdjf.sporttt.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjdjf.sporttt.ui.screens.CALC
import com.fjdjf.sporttt.ui.screens.CALENDAR
import com.fjdjf.sporttt.ui.screens.GAME
import com.fjdjf.sporttt.ui.screens.INFO
import com.fjdjf.sporttt.ui.screens.SETTINGS
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.vectorResource
import sport.composeapp.generated.resources.Res
import sport.composeapp.generated.resources.ball
import sport.composeapp.generated.resources.calc
import sport.composeapp.generated.resources.calendar
import sport.composeapp.generated.resources.info
import sport.composeapp.generated.resources.settings

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomBar(
    screen: String,
    change: (String) -> Unit
) {

    val screens = listOf(
        Pair(Res.drawable.calc, CALC),
        Pair(Res.drawable.info, INFO),
        Pair(Res.drawable.calendar, CALENDAR),
        Pair(Res.drawable.ball, GAME),
        Pair(Res.drawable.settings, SETTINGS)
    )


    Column(
        modifier = Modifier.fillMaxWidth().background(Color.Black).navigationBarsPadding()
    ) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White.copy(0.5f),
            thickness = 0.5.dp
        )
        Row (
            Modifier.fillMaxWidth().padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){

            screens.forEach {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.noRippleClickable {
                        change(it.second)
                    }
                ) {
                    Icon(vectorResource(it.first), "",
                        modifier = Modifier.size(22.dp), tint = if (screen == it.second) Color.White else Color.White.copy(alpha = 0.5f))
                    Spacer(Modifier.height(4.dp))
                    Text(it.second, color = if (screen == it.second) Color.White else Color.White.copy(alpha = 0.5f),
                        fontSize = 8.sp)
                }
            }

        }
    }
}