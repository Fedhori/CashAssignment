package com.example.cashassignment.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import com.example.cashassignment.view.adapter.AllMissionAdapter
import com.example.cashassignment.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_all_mission.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllMissionFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private val taskViewAdapter = AllMissionAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initTabLayout()
        setAllMissions(homeViewModel.getTaskData())
    }

    private fun initTabLayout(){
        tabLayout_allMission.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                setAllMissions(homeViewModel.getTaskData(TaskCategory.values()[tab.position]))
            }
        })
    }

    private fun initAdapter(){
        recyclerView_allMission.adapter = taskViewAdapter
    }

    private fun setAllMissions(allMissionLiveData: LiveData<List<TaskEntity>>){

        taskViewAdapter.setItemClickListener( object:
            ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("test", "${allMissionLiveData.value?.get(position)?.title}")
            }
        })

        allMissionLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { allMissionData ->
            taskViewAdapter.submitList(allMissionData)
        })
    }
}