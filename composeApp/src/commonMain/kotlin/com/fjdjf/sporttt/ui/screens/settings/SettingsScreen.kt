package com.fjdjf.sporttt.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
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
import com.fjdjf.sporttt.ui.components.BaseScreen
import com.fjdjf.sporttt.ui.components.grey
import com.fjdjf.sporttt.ui.components.noRippleClickable
import com.fjdjf.sporttt.ui.components.red
import com.fjdjf.sporttt.ui.screens.calc.categories

@Composable
fun SettingsScreen(
    policy: () -> Unit,
    terms: () -> Unit
) {

    var isNotifications by remember { mutableStateOf(true) }

    BaseScreen("Settings"){

        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                .background(grey).padding(20.dp)
                .noRippleClickable {
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Notifications",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.weight(1f))
            Switch(isNotifications, {isNotifications = it},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = red,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = Color.White,
                    uncheckedTrackColor = grey
                ))
        }

        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                .background(grey).padding(20.dp)
                .noRippleClickable {
                    policy()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Privacy Policy",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Rounded.KeyboardArrowRight, "",
                tint = Color.White, modifier = Modifier.size(34.dp))
        }

        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
                .background(grey).padding(20.dp)
                .noRippleClickable {
                    terms()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Terms of Use",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Rounded.KeyboardArrowRight, "",
                tint = Color.White, modifier = Modifier.size(34.dp))
        }

    }

}