package com.example.cashassignment.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.cashassignment.R
import com.example.cashassignment.view.activity.MainActivity
import com.example.cashassignment.view.adapter.MyMissionFragmentViewPagerAdapter
import com.example.cashassignment.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mission.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyMissionFragment : Fragment() {

    val homeViewModel : HomeViewModel by viewModel()
    private lateinit var myMissionFragmentViewPagerAdapter : MyMissionFragmentViewPagerAdapter

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
        tabLayout_missionFragment.tabMode = TabLayout.MODE_FIXED
        imageView_missionFragment_list.setOnClickListener{
            (activity as MainActivity).openDrawer()
        }
    }

    private fun initViewPager(){
        myMissionFragmentViewPagerAdapter = MyMissionFragmentViewPagerAdapter(this)
        viewPager2_missionFragment.adapter = myMissionFragmentViewPagerAdapter
        viewPager2_missionFragment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                myMissionFragmentViewPagerAdapter.notifyDataSetChanged()
            }
        })

        TabLayoutMediator(tabLayout_missionFragment, viewPager2_missionFragment){ tab, position ->
            when(position){
                0 -> tab.text = "찜한 미션"
                1 -> tab.text = "최근 미션"
            }
        }.attach()
    }
}