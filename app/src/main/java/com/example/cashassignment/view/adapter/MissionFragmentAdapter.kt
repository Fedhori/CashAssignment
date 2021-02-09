package com.example.cashassignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.Level
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import kotlinx.android.synthetic.main.view_all_mission_task.view.*

class MissionFragmentAdapter: RecyclerView.Adapter<MissionFragmentAdapter.ViewHolder>() {

    private var taskList: List<TaskEntity>? = null
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_all_mission_task, parent, false)
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TaskEntity?){
            with(itemView){
                Glide.with(context).load(item?.mainSmallThumbnailUrl).into(itemView.imageView_allMission)
                textView_allMission_title.text = item?.title
                textView_allMission_price.text = "건당 ${item?.showingPrice}원"
                if(item?.maxCount != 0){
                    textView_allMission_count.text = "${item?.userCount}/${item?.maxCount}"
                }
                else{
                    textView_allMission_count.text = "${item?.userCount}/∞"
                }
                if(item?.targetAmount == 0 || item?.targetAmount == null){
                    progressBar_allMission.progress = 0
                }
                else{
                    progressBar_allMission.progress = item.progress * 100 / item.targetAmount
                }

                when(item?.level){
                    Level.READY, Level.NOT_LOGGED_IN -> {
                        imageView_allMission_level.setImageResource(R.drawable.img_badge_ready)
                        textView_allMission_level.text = "신입"
                        textView_allMission_level.setTextColor(ContextCompat.getColor(context, R.color.yellow_600))
                    }
                    Level.BEGINNER -> {
                        imageView_allMission_level.setImageResource(R.drawable.img_badge_begginer)
                        textView_allMission_level.text = "수습"
                        textView_allMission_level.setTextColor(ContextCompat.getColor(context, R.color.green_500))
                    }
                    Level.NORMAL -> {
                        imageView_allMission_level.setImageResource(R.drawable.img_badge_normal)
                        textView_allMission_level.text = "정규"
                        textView_allMission_level.setTextColor(ContextCompat.getColor(context, R.color.blue_500))
                    }
                    Level.BLOCKED -> {
                        imageView_allMission_level.setImageResource(R.drawable.img_badge_black)
                        textView_allMission_level.text = "스파이"
                        textView_allMission_level.setTextColor(ContextCompat.getColor(context, R.color.gray_600))
                    }
                }
            }
        }
    }
}