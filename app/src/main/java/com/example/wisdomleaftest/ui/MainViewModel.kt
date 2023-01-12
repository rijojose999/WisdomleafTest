package com.example.wisdomleaftest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wisdomleaftest.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val imageRepository: ImageRepository) : ViewModel(){

    val imagesLiveData get() = imageRepository.imageLiveData

    fun getAllImages(){
        viewModelScope.launch {
            imageRepository.getImages()
        }
    }

}