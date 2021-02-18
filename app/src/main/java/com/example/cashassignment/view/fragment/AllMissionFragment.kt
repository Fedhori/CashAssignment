package com.example.cashassignment.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.view.activity.MainActivity
import com.example.cashassignment.view.adapter.AllMissionFragmentViewPagerAdapter
import com.example.cashassignment.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mission.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllMissionFragment : Fragment() {

    val homeViewModel : HomeViewModel by viewModel()
    private lateinit var allMissionFragmentViewPagerAdapter : AllMissionFragmentViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        setUI()
        initViewPager()
    }

    private fun setUI(){
        imageView_missionFragment_list.setOnClickListener{
            (activity as MainActivity).openDrawer()
        }
    }

    private fun initViewPager(){
        allMissionFragmentViewPagerAdapter = AllMissionFragmentViewPagerAdapter(this)
        viewPager2_missionFragment.adapter = allMissionFragmentViewPagerAdapter
        viewPager2_missionFragment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                allMissionFragmentViewPagerAdapter.notifyDataSetChanged()
            }
        })

        TabLayoutMediator(tabLayout_missionFragment, viewPager2_missionFragment){ tab, position ->
            tab.text = TaskCategory.values()[position].toString()
        }.attach()
    }
}