package com.fjdjf.sporttt.w

import platform.Foundation.NSHTTPURLResponse
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithRequest

fun kdk(url: String, callback: (Boolean) -> Unit) {
    val nsUrl = NSURL.URLWithString(url) ?: return callback(false)
    val request = NSURLRequest.requestWithURL(nsUrl)

    val session = NSURLSession.sharedSession
    val task = session.dataTaskWithRequest(request) { _, response, error ->
        val statusCode = (response as? NSHTTPURLResponse)?.statusCode ?: 0
        val isAvailable = (error == null && statusCode.toInt() != 404)
        callback(isAvailable)
    }

    task.resume()
}