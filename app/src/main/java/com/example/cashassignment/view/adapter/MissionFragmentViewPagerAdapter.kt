package com.example.cashassignment.view.adapter

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashassignment.R
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.view.ItemClickListener
import com.example.cashassignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.fragment_mission_task.view.*
import kotlinx.android.synthetic.main.view_recyclerview.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.experimental.property.inject

class MissionFragmentViewPagerAdapter(private val homeViewModel: HomeViewModel) :
    RecyclerView.Adapter<MissionFragmentViewPagerAdapter.ViewHolder>() {

    private var listOfTaskList : Array<PagedList<TaskEntity>?> = arrayOfNulls(TaskCategory.values().size)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_recyclerview, parent, false),
            homeViewModel
        )

    override fun getItemCount(): Int = listOfTaskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfTaskList[position])
    }

    fun submitList(position: Int, taskList: PagedList<TaskEntity>){
        listOfTaskList[position] = taskList
        notifyDataSetChanged()
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

                            notifyItemChanged(position)
                        }
                    })
                }
            }
        }
    }
}