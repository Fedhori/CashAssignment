package com.example.cashassignment.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.Level
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_mission_task.view.*

class MissionFragmentAdapter: PagedListAdapter<TaskEntity, MissionFragmentAdapter.ViewHolder>(COMPARATOR) {

    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_mission_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let{holder.bind(it)}

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TaskEntity>() {
            override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
                oldItem.taskKey == newItem.taskKey

            override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TaskEntity?){

            with(itemView){
                Glide.with(context).load(item?.mainSmallThumbnailUrl).into(itemView.imageView_missionFragment)
                textView_missionFragment_title.text = item?.title
                textView_missionFragment_price.text = "건당 ${item?.showingPrice}원"
                if(item?.maxCount != 0){
                    textView_missionFragment_count.text = "${item?.userCount}/${item?.maxCount}"
                }
                else{
                    textView_missionFragment_count.text = "${item?.userCount}/∞"
                }
                if(item?.targetAmount == 0 || item?.targetAmount == null){
                    progressBar_missionFragment.progress = 0
                }
                else{
                    progressBar_missionFragment.progress = item.progress * 100 / item.targetAmount
                }

                when(item?.level){
                    Level.READY, Level.NOT_LOGGED_IN -> {
                        imageView_missionFragment_level.setImageResource(R.drawable.img_badge_ready)
                        textView_missionFragment_level.text = "신입"
                        textView_missionFragment_level.setTextColor(ContextCompat.getColor(context, R.color.yellow_600))
                    }
                    Level.BEGINNER -> {
                        imageView_missionFragment_level.setImageResource(R.drawable.img_badge_begginer)
                        textView_missionFragment_level.text = "수습"
                        textView_missionFragment_level.setTextColor(ContextCompat.getColor(context, R.color.green_500))
                    }
                    Level.NORMAL -> {
                        imageView_missionFragment_level.setImageResource(R.drawable.img_badge_normal)
                        textView_missionFragment_level.text = "정규"
                        textView_missionFragment_level.setTextColor(ContextCompat.getColor(context, R.color.blue_500))
                    }
                    Level.BLOCKED -> {
                        imageView_missionFragment_level.setImageResource(R.drawable.img_badge_black)
                        textView_missionFragment_level.text = "스파이"
                        textView_missionFragment_level.setTextColor(ContextCompat.getColor(context, R.color.gray_600))
                    }
                }
            }
        }
    }
}