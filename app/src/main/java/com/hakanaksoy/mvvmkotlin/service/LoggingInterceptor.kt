package com.hakanaksoy.mvvmkotlin.service

import com.hakanaksoy.mvvmkotlin.ui.base.Constants
import com.orhanobut.logger.Logger
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.ProtocolException

@Singleton
class LoggingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        Logger.i(
                String.format(
                        "Sending Request %s on %s%n%s",
                        request.url(),
                        chain.connection(),
                        request.headers()
                )
        )

        val requestBuilder = request.newBuilder().method(request.method(), request.body())

        if (request.url().toString().contains(Constants.NetworkService.BASE_URL)) {
            requestBuilder.addHeader(Constants.NetworkService.API_KEY_HEADER_NAME, Constants.NetworkService.API_KEY_VALUE)
        } else {
            requestBuilder.addHeader(Constants.NetworkService.API_KEY_HEADER_NAME, Constants.NetworkService.API_KEY_VALUE)
        }

        val newRequest = requestBuilder.build()

        val response: Response
        response = try {
            chain.proceed(newRequest)
        } catch (e: ProtocolException) {
            Response.Builder()
                    .request(request)
                    .code(204)
                    .protocol(Protocol.HTTP_1_1)
                    .build()
        }

        var rawJson: String? = ""
        try {
            rawJson = response.body()!!.string()
            if (rawJson != null) {
                Logger.json(rawJson)
            }
        } catch (e: Exception) {
            Logger.e("Null response body")
        }

        return response.newBuilder()
                .body(ResponseBody.create(response.body()!!.contentType(), rawJson!!)).build()

    }
}