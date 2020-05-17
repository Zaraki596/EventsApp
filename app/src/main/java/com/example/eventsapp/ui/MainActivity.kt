package com.example.eventsapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventsapp.databinding.ActivityMainBinding
import com.example.eventsapp.ui.adapters.FeaturedListAdapter
import com.example.eventsapp.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<EventsViewModel>()

    private val mFeaturedListAdapter = FeaturedListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initData()
    }

    private fun initData() {
        viewModel.eventsLiveData.observe(this, Observer { state ->
            when (state) {
                is State.Loading -> binding.refreshMain.isRefreshing = true
                is State.Error -> {
                    binding.refreshMain.isRefreshing = false
                    Toast.makeText(applicationContext, state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    binding.refreshMain.isRefreshing = false
                    val featuredList = state.data.featured
                    val tempList = state.data.list.masterList.values.filter {
                        state.data.list.categorywiseList.Music.contains(it.slug)
                    }
                    Log.e("MainActivity", tempList.toString())
                    mFeaturedListAdapter.submitList(featuredList)
                }
            }

        })
    }

    private fun initViews() {
        setSupportActionBar(binding.appBarLayout.toolbar)
        binding.recyclerFeatured.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerFeatured.adapter = mFeaturedListAdapter
        binding.refreshMain.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        viewModel.getEventsResponse()
    }

}
