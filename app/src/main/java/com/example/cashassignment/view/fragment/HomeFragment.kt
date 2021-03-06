package com.example.cashassignment.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.example.cashassignment.R
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.BundleEntity
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import com.example.cashassignment.view.activity.LoginActivity
import com.example.cashassignment.view.activity.MainActivity
import com.example.cashassignment.view.customview.MissionView
import com.example.cashassignment.view.adapter.BannerViewPagerAdapter
import com.example.cashassignment.view.adapter.MyMissionViewAdapter
import com.example.cashassignment.view.adapter.NewMissionViewAdapter
import com.example.cashassignment.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_mission.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()
        setBanners(homeViewModel.getBannerData())
        setNewMissions(homeViewModel.getTaskData())
        setMyMissions(homeViewModel.getTaskData())
        setBundles(homeViewModel.getBundleData())
    }

    private fun setUI(){
        button_home_list.setOnClickListener{
            (activity as MainActivity).openDrawer()
        }

        textView_home_point.setOnClickListener{
            if(homeViewModel.checkIsLogin()){
                logOut()
            }
            else{
                goToLoginActivity()
            }
        }
    }

    private fun logOut(){
        homeViewModel.logOut()
        startActivity(Intent(this.activity, MainActivity::class.java))
        this.activity?.finish()
    }

    private fun goToLoginActivity(){
        startActivity(Intent(this.activity, LoginActivity::class.java))
    }

    private fun setBanners(bannerLiveData: LiveData<List<BannerEntity>>){

        val bannerViewPagerAdapter = BannerViewPagerAdapter()
        viewPager2_home_banner.adapter = bannerViewPagerAdapter

        bannerLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { bannerData ->
            bannerViewPagerAdapter.submitList(bannerData)
        })

        TabLayoutMediator(tabLayout_home_banner, viewPager2_home_banner){ tab, position ->

        }.attach()
    }
    
    private fun setNewMissions(newMissionLiveData: LiveData<List<TaskEntity>>){
        val newMissionView =
            MissionView(this)
        val taskViewAdapter =
            NewMissionViewAdapter()

        taskViewAdapter.setItemClickListener( object:
            ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("test", "${newMissionLiveData.value?.get(position)?.title}")
            }
        })

        linearLayout_home_bundleContainer.addView(newMissionView)
        newMissionView.textView_mission.text = "새로운 미션"
        newMissionView.recyclerView_mission.adapter = taskViewAdapter

        newMissionLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { newMissionData ->
            taskViewAdapter.submitList(newMissionData)
        })
    }

    private fun setMyMissions(myMissionLiveData: LiveData<List<TaskEntity>>){
        val myMissionView =
            MissionView(this)
        val taskViewAdapter =
            MyMissionViewAdapter()

        taskViewAdapter.setItemClickListener( object:
            ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("test", "${myMissionLiveData.value?.get(position)?.title}")
            }
        })

        linearLayout_home_bundleContainer.addView(myMissionView)
        myMissionView.textView_mission.text = "새로운 미션"
        myMissionView.recyclerView_mission.adapter = taskViewAdapter

        myMissionLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { myMissionData ->
            taskViewAdapter.submitList(myMissionData)
        })
    }

    private fun setBundles(bundleLiveData: LiveData<List<BundleEntity>>){
        bundleLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { bundleData ->
            setBundle(bundleData)
        })
    }

    private fun setBundle(bundleData: List<BundleEntity>){
        for(bundle in bundleData){
            val missionView =
                MissionView(this)
            val taskViewAdapter =
                NewMissionViewAdapter()

            taskViewAdapter.setItemClickListener( object:
                ItemClickListener {
                override fun onClick(view: View, position: Int) {
                    Log.d("test", bundle.taskTitles[position].title)
                }
            })

            linearLayout_home_bundleContainer.addView(missionView)
            missionView.textView_mission.text = bundle.title
            missionView.recyclerView_mission.adapter = taskViewAdapter
            taskViewAdapter.submitList(bundle.taskTitles)
        }
    }
}