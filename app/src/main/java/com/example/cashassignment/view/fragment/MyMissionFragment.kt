package com.example.cashassignment.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import com.example.cashassignment.view.adapter.MissionFragmentAdapter
import com.example.cashassignment.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_my_mission.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyMissionFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private val taskViewAdapter = MissionFragmentAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initTabLayout()
        setMyMissions(homeViewModel.getTaskData())
    }

    private fun initTabLayout(){

        with(tabLayout_myMission){

            addTab(newTab().setText("찜한 미션"))
            addTab(newTab().setText("최근 한 미션"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab) {
                    setMyMissions(homeViewModel.getTaskData(TaskCategory.values()[tab.position]))
                }
            })
        }
    }

    private fun initAdapter(){
        recyclerView_myMission.adapter = taskViewAdapter
    }

    private fun setMyMissions(myMissionLiveData: LiveData<List<TaskEntity>>){

        taskViewAdapter.setItemClickListener( object:
            ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("test", "${myMissionLiveData.value?.get(position)?.title}")
            }
        })

        myMissionLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { myMissionData ->
            taskViewAdapter.submitList(myMissionData)
        })
    }
}