package com.example.wisdomleaftest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wisdomleaftest.databinding.ActivityMainBinding
import com.example.wisdomleaftest.models.ImageItemResponse
import com.example.wisdomleaftest.ui.ImageAdapter
import com.example.wisdomleaftest.ui.MainViewModel
import com.example.wisdomleaftest.utils.Helper
import com.example.wisdomleaftest.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ImageAdapter(::onItemClicked)

        if (Helper.isInternetConnected(this)){
            mainViewModel.getAllImages()
        }else{
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT)
                .show()
        }

        binding.imageList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        binding.imageList.adapter = adapter
        bindObservers()

        //implementing swipe down to refresh the data
        binding.swipeContainer.setOnRefreshListener {
            if (Helper.isInternetConnected(this)){
                adapter.submitList(null)
                mainViewModel.getAllImages()
            }else{
                binding.swipeContainer.isRefreshing = false
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    private fun bindObservers() {
        mainViewModel.imagesLiveData.observe(this) {
            binding.progressBar.isVisible = false
            binding.swipeContainer.isRefreshing = false
            when (it) {
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    private fun onItemClicked(imageResponse: ImageItemResponse){

        //just showing the data in the response
        val builder = AlertDialog.Builder(this)
        builder.setMessage(imageResponse.toString())
        builder.setPositiveButton("OK") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }
}