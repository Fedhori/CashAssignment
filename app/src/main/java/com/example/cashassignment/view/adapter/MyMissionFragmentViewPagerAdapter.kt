package com.example.cashassignment.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import com.example.cashassignment.view.fragment.MyMissionFragment
import com.example.cashassignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.view_recyclerview.view.*

class MyMissionFragmentViewPagerAdapter(
    private val myMissionFragment: MyMissionFragment) :
    RecyclerView.Adapter<MyMissionFragmentViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_recyclerview, parent, false),
            myMissionFragment.homeViewModel
        )

    // There are only 2 tabs in 찜한 미션, 쵝근에 한 미션
    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(position){
            0 -> {
                myMissionFragment.homeViewModel.getDibsPageData().observe(myMissionFragment, Observer {
                    holder.bind(it)
                })

            }

            1 -> {
                //TODO implement recent task api
                myMissionFragment.homeViewModel.getRecentPageData().observe(myMissionFragment, Observer {
                    holder.bind(it)
                })
            }
        }

    }

    class ViewHolder(itemView: View, private val homeViewModel: HomeViewModel) : RecyclerView.ViewHolder(itemView) {

        fun bind(taskList: PagedList<TaskEntity>?){

            with(itemView){
                val missionFragmentAdapter = MissionFragmentAdapter()
                recyclerView_missionFragment.adapter = missionFragmentAdapter

                with(missionFragmentAdapter){
                    submitList(taskList)

                    setItemClickListener( object:
                        ItemClickListener {
                        override fun onClick(view: View, position: Int) {
                            Log.d("test", "${taskList?.get(position)?.title}")
                        }
                    })

                    setDibsClickListener( object:
                        ItemClickListener {
                        override fun onClick(view: View, position: Int) {
                            if(taskList?.get(position)?.isDibs == true){
                                taskList?.get(position)?.isDibs = false
                                missionFragmentAdapter.emptyIcon()
                                taskList?.get(position)?.id?.let { homeViewModel.deleteDibs(it) }
                            }
                            else{
                                taskList?.get(position)?.isDibs = true
                                missionFragmentAdapter.fillIcon()
                                taskList?.get(position)?.id?.let { homeViewModel.postDibs(it) }
                            }
                            notifyDataSetChanged()
                        }
                    })
                }
            }
        }
    }
}