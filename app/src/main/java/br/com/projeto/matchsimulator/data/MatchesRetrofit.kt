package br.com.projeto.matchsimulator.data

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchesRetrofit {

    var retrofit: Retrofit

    companion object {
        private const val HTTP_TG = "HTTP_LOG"
        private const val BASE_URL = "https://onirvanico.github.io/matches-simulator-api/"
    }

    init {
        val interceptor = HttpLoggingInterceptor {
            Log.d(HTTP_TG, "httpLogginInterceptor : $it",)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        var client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson().newBuilder().create()))
            .client(client)
            .build()
    }

    fun getMatchesApi() = retrofit.create(MatchesApi::class.java)

}