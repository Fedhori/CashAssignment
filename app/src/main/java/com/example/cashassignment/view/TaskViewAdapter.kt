package com.example.cashassignment.view

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.Level
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
                textView_task_title.text = item?.title
                textView_task_price.text = "건당 ${item?.showingPrice}원"
                if(item?.maxCount != 0){
                    textView_task_count.text = "${item?.userCount}/${item?.maxCount}"
                }
                else{
                    textView_task_count.text = "${item?.userCount}/∞"
                }
                if(item?.targetAmount == 0 || item?.targetAmount == null){
                    progressBar_task.progress = 0
                }
                else{
                    progressBar_task.progress = item.progress / item.targetAmount
                }

                when(item?.level){
                    Level.READY, Level.NOT_LOGGED_IN -> {
                        imageView_task_level.setImageResource(R.drawable.img_badge_ready)
                        textView_task_level.text = "신입"
                    }
                    Level.BEGINNER -> {
                        imageView_task_level.setImageResource(R.drawable.img_badge_begginer)
                        textView_task_level.text = "수습"
                    }
                    Level.NORMAL -> {
                        imageView_task_level.setImageResource(R.drawable.img_badge_normal)
                        textView_task_level.text = "정규"
                    }
                    Level.BLOCKED -> {
                        imageView_task_level.setImageResource(R.drawable.img_badge_black)
                        textView_task_level.text = "스파이"
                    }
                }
            }
        }
    }
}