package com.example.cashassignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private var listOfTaskList : Array<MutableList<TaskEntity>?> = arrayOfNulls(TaskCategory.values().size)

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

    fun submitList(position: Int, taskList: MutableList<TaskEntity>?){

        if(this.listOfTaskList[position] != null){
            taskList?.let { this.listOfTaskList[position]?.addAll(it) }
        }
        else{
            this.listOfTaskList[position] = taskList
        }
        //this.listOfTaskList.add(taskList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(taskList: List<TaskEntity>?){
            with(itemView){
                val missionFragmentAdapter = MissionFragmentAdapter()
                recyclerView_missionFragment.adapter = missionFragmentAdapter

                missionFragmentAdapter.submitList(taskList)
            }
        }
    }
}