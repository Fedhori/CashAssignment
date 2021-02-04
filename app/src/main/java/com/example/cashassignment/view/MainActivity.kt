package com.example.cashassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import com.example.cashassignment.R
import com.example.cashassignment.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeStatusBarTransparent()
        //initBinding()
        bottomNavigationView_home.setOnNavigationItemSelectedListener(this)
        bottomNavigationView_home.selectedItemId = R.id.navigation_home
    }

    private fun makeStatusBarTransparent(){
        // make status bar completely transparent
        val window = window
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    /*

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
    }

     */

    private fun initHome(){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_home, HomeFragment()).commit()
    }

    /*

    private fun initMissions(){
        initNewMission()
        initMyMission()
        initBundle()
    }

    private fun initNewMission(){
        val newMissionView = MissionView(this)
        val taskViewAdapter = NewMissionViewAdapter()
        val taskData = viewModel.getTaskNotLoginData()

        taskViewAdapter.setItemClickListener( object: ItemClickListener{
            override fun onClick(view: View, position: Int) {
                Log.d("test", "${taskData.value?.get(position)?.title}")
            }
        })

        binding.linearLayoutHomeBundleContainer.addView(newMissionView)
        newMissionView.textView_mission.text = "새로운 미션"
        newMissionView.recyclerView_mission.adapter = taskViewAdapter

        taskData.observe(this, androidx.lifecycle.Observer { taskData ->
            taskViewAdapter.submitList(taskData)
        })
    }

    private fun initMyMission(){
        val newMissionView = MissionView(this)
        val taskViewAdapter = MyMissionViewAdapter()
        //TODO current is taskData -> you need to change it to other data later
        val taskData = viewModel.getTaskNotLoginData()

        taskViewAdapter.setItemClickListener( object: ItemClickListener{
            override fun onClick(view: View, position: Int) {
                Log.d("test", "${taskData.value?.get(position)?.title}")
            }
        })

        binding.linearLayoutHomeBundleContainer.addView(newMissionView)
        newMissionView.textView_mission.text = "최근 참여한 미션"
        newMissionView.recyclerView_mission.adapter = taskViewAdapter

        taskData.observe(this, androidx.lifecycle.Observer { taskData ->
            taskViewAdapter.submitList(taskData)
        })
    }

    private fun initBundle(){
        val bundleLiveData = viewModel.getBundleNotLoginData()

        bundleLiveData.observe(this, androidx.lifecycle.Observer { bundleList ->
            setBundle(bundleList)
        })
    }

    private fun setBundle(bundleList: List<BundleEntity>){
        for(bundle in bundleList){
            val missionView = MissionView(this)
            val taskViewAdapter = NewMissionViewAdapter()

            binding.linearLayoutHomeBundleContainer.addView(missionView)
            missionView.textView_mission.text = bundle.title
            missionView.recyclerView_mission.adapter = taskViewAdapter
            taskViewAdapter.submitList(bundle.taskTitles)
        }
    }

    */

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_home ->{
                initHome()
            }
            R.id.navigation_all_mission ->{
                initHome()
            }
            R.id.navigation_my_mission ->{
                initHome()
            }
        }
        return true
    }
}