package com.fjdjf.sporttt

import androidx.compose.ui.window.ComposeUIViewController
import com.final_class.webview_multiplatform_mobile.webview.WebViewPlatform
import com.final_class.webview_multiplatform_mobile.webview.controller.rememberWebViewController
import com.fjdjf.sporttt.data.getFinanceDatabase
import com.fjdjf.sporttt.w.kdk

fun MainViewController() = ComposeUIViewController {
    val kkkd by rememberWebViewController()

    WebViewPlatform(webViewController = kkkd)

    App(
        getFinanceDatabase().dayDao()
    ) {
        kdk(it) { res ->
            if (res) {
                kkkd.open(it)
            }
        }
    }
}