package com.example.cashassignment.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import com.example.cashassignment.view.adapter.MissionFragmentAdapter
import com.example.cashassignment.view.adapter.MissionFragmentViewPagerAdapter
import com.example.cashassignment.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.view_recyclerview.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllMissionFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private val missionFragmentViewPagerAdapter = MissionFragmentViewPagerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
    }

    private fun initViewPager(){
        viewPager2_missionFragment.adapter = missionFragmentViewPagerAdapter

        for(category in TaskCategory.values()){

            homeViewModel.getTaskPageData().observe(viewLifecycleOwner, Observer {
                missionFragmentViewPagerAdapter.submitList(category.ordinal, it)
            })
        }

        TabLayoutMediator(tabLayout_missionFragment, viewPager2_missionFragment){ tab, position ->
            tab.text = TaskCategory.values()[position].toString()
        }.attach()
        /*
        Handler(Looper.getMainLooper()).postDelayed({
            missionFragmentViewPagerAdapter.submitList(0, liveData.value!!)
            //Do something after 100ms
        }, 2000)
         */
    }
}