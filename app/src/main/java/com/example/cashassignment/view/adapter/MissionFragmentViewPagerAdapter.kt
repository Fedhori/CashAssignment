package com.example.cashassignment.view.adapter

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.model.TaskEntity
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.fragment_mission_task.view.*
import kotlinx.android.synthetic.main.view_recyclerview.view.*

class MissionFragmentViewPagerAdapter :
    RecyclerView.Adapter<MissionFragmentViewPagerAdapter.ViewHolder>() {

    private var listOfTaskList : Array<PagedList<TaskEntity>?> = arrayOfNulls(TaskCategory.values().size)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_recyclerview, parent, false)
        )

    override fun getItemCount(): Int = listOfTaskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfTaskList[position])
    }

    fun submitList(position: Int, taskList: PagedList<TaskEntity>){
        listOfTaskList[position] = taskList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(taskList: PagedList<TaskEntity>?){
            with(itemView){
                val missionFragmentAdapter = MissionFragmentAdapter()
                recyclerView_missionFragment.adapter = missionFragmentAdapter

                missionFragmentAdapter.submitList(taskList)
            }
        }
    }
}