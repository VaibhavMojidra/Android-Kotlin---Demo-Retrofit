package com.vaibhavmojidra.retrofitdemokotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.vaibhavmojidra.retrofitdemokotlin.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var retrofitService:AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        retrofitService=RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
        //Path parameter
        getAlbumsWithParameters()
        //Sorted Albums
        getSortedAlbums()
        //Get All Albums
        getAllAlbums()
        uploadAlbum()

    }

    private fun getAlbumsWithParameters(){
        val pathResponse:LiveData<Response<AlbumsItem>> = liveData {
            val response=retrofitService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this,{
            val response=it.body()?.title
            Toast.makeText(this,response,Toast.LENGTH_LONG).show()

        })
    }
    private fun getSortedAlbums(){
        val responseLiveData:LiveData<Response<Albums>> = liveData {
            val response=retrofitService.getSortedAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this, {
            val albumsList=it.body()?.listIterator()
            if(albumsList!=null){
                while(albumsList.hasNext()){
                    val albumsItem=albumsList.next()
                    activityMainBinding.textView.append(" "+albumsItem.id+" \n"+
                            " "+albumsItem.title+" \n"+
                            " "+albumsItem.userId+" \n\n\n")
                }

            }
        })
    }
    private fun getAllAlbums(){
        val responseLiveData:LiveData<Response<Albums>> = liveData {
            val response=retrofitService.getAlbums()
            emit(response)
        }
        responseLiveData.observe(this, {
            val albumsList=it.body()?.listIterator()
            if(albumsList!=null){
                while(albumsList.hasNext()){
                    val albumsItem=albumsList.next()
                    activityMainBinding.textView.append(" "+albumsItem.id+" \n"+
                            " "+albumsItem.title+" \n"+
                            " "+albumsItem.userId+" \n\n\n")
                }

            }
        })
    }

    private fun uploadAlbum(){
        val albumsItem=AlbumsItem(0,"Vaibhav",0)
        val postResponse:LiveData<Response<AlbumsItem>> = liveData {
            val response=retrofitService.uploadAlbum(albumsItem)
            emit(response)
        }
        postResponse.observe(this, {
            val receivedAlbumsItem=it.body()
            Toast.makeText(this,receivedAlbumsItem?.title,Toast.LENGTH_LONG).show()
        })
    }
}