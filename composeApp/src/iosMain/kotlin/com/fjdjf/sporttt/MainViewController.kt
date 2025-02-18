package com.fjdjf.sporttt

import androidx.compose.ui.window.ComposeUIViewController
import com.fjdjf.sporttt.data.getFinanceDatabase

fun MainViewController() = ComposeUIViewController { App(getFinanceDatabase().dayDao()) }