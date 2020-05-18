package com.example.eventsapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventsapp.databinding.ActivityMainBinding
import com.example.eventsapp.ui.adapters.BannerListAdpater
import com.example.eventsapp.ui.adapters.FeaturedListAdapter
import com.example.eventsapp.ui.adapters.NormalListAdpater
import com.example.eventsapp.utils.State
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<EventsViewModel>()

    private val mFeaturedListAdapter = FeaturedListAdapter()
    private val mMusicListAdapter = NormalListAdpater()
    private val mComedyListAdapter = NormalListAdpater()
    private val mKidsListAdapter = NormalListAdpater()
    private val mOnlineListAdapter = NormalListAdpater()

    private val mBannerListAdpater = BannerListAdpater()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initViews()
        initData()

        binding.refreshMain.setOnRefreshListener {
            loadData()
        }
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
                    val musicList = state.data.list.masterList.values.filter {
                        state.data.list.categorywiseList.Music.contains(it.slug)
                    }
                    val comedyList = state.data.list.masterList.values.filter {
                        state.data.list.categorywiseList.Comedy.contains(it.slug)
                    }.filter {
                        it.applicable_filters.isNotEmpty()
                    }
                    val onlineCourseList = state.data.list.masterList.values.filter {
                        state.data.list.categorywiseList.Online_Course.contains(it.slug)
                    }.filter {
                        it.applicable_filters.isNotEmpty()
                    }
                    val kidList = state.data.list.masterList.values.filter {
                        state.data.list.categorywiseList.Kids.contains(it.slug)
                    }.filter {
                        it.applicable_filters.isNotEmpty()
                    }
                    val bannerList = state.data.banners.filter {
                        it.priority == 0
                    }
                    Log.e("MainActivity", featuredList.toString())
                    mFeaturedListAdapter.submitList(featuredList)
                    mMusicListAdapter.submitList(musicList)
                    mComedyListAdapter.submitList(comedyList)
                    mKidsListAdapter.submitList(kidList)
                    mOnlineListAdapter.submitList(onlineCourseList)

                    mBannerListAdpater.submitList(bannerList)
                }
            }

        })
    }

    private fun initViews() {
        setSupportActionBar(binding.appBarlayout.toolbar)
        binding.recyclerFeatured.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerMusic.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerComedy.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerKids.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerOnlineCourse.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

//        Setting up the recyclerview Adapter
        binding.recyclerFeatured.adapter = mFeaturedListAdapter
        binding.recyclerMusic.adapter = mMusicListAdapter
        binding.recyclerComedy.adapter = mComedyListAdapter
        binding.recyclerKids.adapter = mKidsListAdapter
        binding.recyclerOnlineCourse.adapter = mOnlineListAdapter
//        ViewPager Setup
        binding.viewpagerBanner.adapter = mBannerListAdpater
        TabLayoutMediator(binding.tablayoutBanner, binding.viewpagerBanner) { tab, position ->

        }.attach()


        loadData()
    }

    private fun loadData() {
        viewModel.getEventsResponse()
    }

}
