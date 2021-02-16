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
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.view_mission.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyMissionFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private val taskViewAdapter = MissionFragmentAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
    }
}