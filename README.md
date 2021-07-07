# Android-Kotlin---Retrofit-Demo

Step 1: Add all dependancies : 

Step 2: Create a model/POJO Class for a single element of data list.(eg: AlbumsItem)

package com.vaibhavmojidra.retrofitdemokotlin

import com.google.gson.annotations.SerializedName

data class AlbumsItem(
   @SerializedName("id")
   val id: Int,
   @SerializedName("title")
   val title: String,
   @SerializedName("userId")
   val userId: Int
)

Step 3: Create a class returning list of data.(eg: Albums)
package com.vaibhavmojidra.retrofitdemokotlin

class Albums : ArrayList<AlbumsItem>()

Step 4: Create Interface to declare methods for fetching and other operations like GET POST (Eg AlbumService)
package com.vaibhavmojidra.retrofitdemokotlin

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
   @GET("/albums")
   suspend fun getAlbums():Response<Albums>
}

Step 5: Create a class to configure and get RetrofitInstance (eg: RetrofitInstance)
package com.vaibhavmojidra.retrofitdemokotlin

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
   companion object{
       private const val BASE_URL="https://jsonplaceholder.typicode.com"
       fun getRetrofitInstance():Retrofit{
           return Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
               .build()
       }
   }
}





![S1](https://github.com/VaibhavMojidra/Android-Kotlin---Retrofit-Demo/blob/master/screenshots/1.png)
![S2](https://github.com/VaibhavMojidra/Android-Kotlin---Retrofit-Demo/blob/master/screenshots/2.png)
![S3](https://github.com/VaibhavMojidra/Android-Kotlin---Retrofit-Demo/blob/master/screenshots/3.png)
![S4](https://github.com/VaibhavMojidra/Android-Kotlin---Retrofit-Demo/blob/master/screenshots/4.png)
![S5](https://github.com/VaibhavMojidra/Android-Kotlin---Retrofit-Demo/blob/master/screenshots/5.png)
