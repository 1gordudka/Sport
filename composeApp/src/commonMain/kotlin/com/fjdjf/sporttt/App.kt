package com.fjdjf.sporttt

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fjdjf.sporttt.data.db.DayDao
import com.fjdjf.sporttt.ui.components.BottomBar
import com.fjdjf.sporttt.ui.screens.CALC
import com.fjdjf.sporttt.ui.screens.CALENDAR
import com.fjdjf.sporttt.ui.screens.GAME
import com.fjdjf.sporttt.ui.screens.INFO
import com.fjdjf.sporttt.ui.screens.POLICY
import com.fjdjf.sporttt.ui.screens.SETTINGS
import com.fjdjf.sporttt.ui.screens.TERMS
import com.fjdjf.sporttt.ui.screens.calc.CalcScreen
import com.fjdjf.sporttt.ui.screens.calendar.CalendarScreen
import com.fjdjf.sporttt.ui.screens.game.GameScreen
import com.fjdjf.sporttt.ui.screens.info.InfoScreen
import com.fjdjf.sporttt.ui.screens.privacy
import com.fjdjf.sporttt.ui.screens.settings.SettingsScreen
import com.fjdjf.sporttt.ui.screens.settings.TextScreen
import com.fjdjf.sporttt.ui.screens.terms
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import sport.composeapp.generated.resources.Res
import sport.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(
    dao: DayDao,
    ch: (String) -> Unit
) {

    val navController = rememberNavController()
    var showBottomBar by remember { mutableStateOf(false) }
    var dest by remember { mutableStateOf("") }

    val days by dao.getAllDays().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    val l = "https://google.com"
    LaunchedEffect(true){
        ch(l)
    }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            dest = destination.route.toString()
            showBottomBar = when (destination.route) {
                INFO -> true
                CALC -> true
                SETTINGS -> true
                CALENDAR -> true
                GAME -> true
                else -> false
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        bottomBar = {
            if (showBottomBar){
                BottomBar(dest, {navController.navigate(it)})
            }
        }
    ){pd->
        NavHost(
            modifier = Modifier.padding(pd).background(Color.Black),
            startDestination = CALC,
            navController = navController
        ){

            composable(CALC){
                CalcScreen {
                    scope.launch {
                        dao.addDay(it)
                    }
                }
            }

            composable(GAME){
                GameScreen{
                    navController.navigate(CALC)
                    navController.navigate(GAME)
                }
            }

            composable(INFO){
                InfoScreen()
            }

            composable(CALENDAR){
                CalendarScreen(days)
            }

            composable(SETTINGS){
                SettingsScreen({
                    navController.navigate(POLICY)
                }, {
                    navController.navigate(TERMS)
                })
            }

            composable(POLICY){
                TextScreen(privacy, {navController.popBackStack()})

            }

            composable(TERMS){
                TextScreen(terms, {navController.popBackStack()})
            }
        }
    }
}