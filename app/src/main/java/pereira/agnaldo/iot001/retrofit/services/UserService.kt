package pereira.agnaldo.iot001.retrofit.services

import pereira.agnaldo.iot001.database.entity.User
import retrofit2.Call
import retrofit2.http.POST

interface UserService {

    @POST("authorize")
    fun authorize(): Call<User>

}