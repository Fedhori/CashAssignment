package com.example.cashassignment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashassignment.R
import com.example.cashassignment.model.TaskEntity
import kotlinx.android.synthetic.main.view_new_mission_task.view.*

class TaskViewAdapter(private val context: Context) :
    RecyclerView.Adapter<TaskViewAdapter.ViewHolder>() {

    private var taskList: List<TaskEntity>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_new_mission_task, parent, false))

    override fun getItemCount(): Int = taskList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList?.get(position))
    }

    fun submitList(taskList: List<TaskEntity>?){
        this.taskList = taskList

        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TaskEntity?){
            with(itemView){
                Glide.with(context).load(item?.mainSmallThumbnailUrl).into(itemView.imageView_task)
            }
        }
    }
}