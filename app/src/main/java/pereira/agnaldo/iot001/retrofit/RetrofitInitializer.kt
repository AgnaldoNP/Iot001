package pereira.agnaldo.iot001.retrofit

import pereira.agnaldo.iot001.retrofit.services.UserService
import pereira.agnaldo.iot001.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit =  Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    fun userService() = retrofit.create(UserService::class.java)

}