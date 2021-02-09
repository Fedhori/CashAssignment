package com.example.cashassignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.Level
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import kotlinx.android.synthetic.main.view_my_mission_task.view.*

class MyMissionViewAdapter : RecyclerView.Adapter<MyMissionViewAdapter.ViewHolder>() {

    private var taskList: List<TaskEntity>? = null
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_my_mission_task, parent, false)
        )

    override fun getItemCount(): Int = taskList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList?.get(position))

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    fun submitList(taskList: List<TaskEntity>?){
        this.taskList = taskList

        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: TaskEntity?){
            with(itemView){
                Glide.with(context).load(item?.mainSmallThumbnailUrl).into(itemView.imageView_task)
                textView_myMission_title.text = item?.title
                if(item?.maxCount != 0){
                    textView_myMission_count.text = "${item?.userCount}/${item?.maxCount}"
                }
                else{
                    textView_myMission_count.text = "${item?.userCount}/∞"
                }
                if(item?.targetAmount == 0 || item?.targetAmount == null){
                    progressBar_task.progress = 0
                }
                else{
                    progressBar_task.progress = item.progress * 100 / item.targetAmount
                }

                when(item?.level){
                    Level.READY, Level.NOT_LOGGED_IN -> {
                        imageView_myMission_level.setImageResource(R.drawable.img_badge_ready)
                    }
                    Level.BEGINNER -> {
                        imageView_myMission_level.setImageResource(R.drawable.img_badge_begginer)
                    }
                    Level.NORMAL -> {
                        imageView_myMission_level.setImageResource(R.drawable.img_badge_normal)
                    }
                    Level.BLOCKED -> {
                        imageView_myMission_level.setImageResource(R.drawable.img_badge_black)
                    }
                }
            }
        }
    }
}