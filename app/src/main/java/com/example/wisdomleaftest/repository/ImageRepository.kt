package com.example.wisdomleaftest.repository

import androidx.lifecycle.MutableLiveData
import com.example.wisdomleaftest.api.ImageApi
import com.example.wisdomleaftest.models.ImageItemResponse
import com.example.wisdomleaftest.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageApi: ImageApi){

    //Declared a private Mutable LiveData instance to handle updates from the ViewModel only.
    private val _imageLiveData = MutableLiveData<NetworkResult<List<ImageItemResponse>>>()
    //Declared a public LiveData instance to observe from MainActivity
    val imageLiveData get() = _imageLiveData

    //fetching data from api and updating live data
    suspend fun getImages(){
        _imageLiveData.postValue((NetworkResult.Loading()))
        val response = imageApi.getImages()
        if (response.isSuccessful && response.body() != null) {
            _imageLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _imageLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _imageLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}