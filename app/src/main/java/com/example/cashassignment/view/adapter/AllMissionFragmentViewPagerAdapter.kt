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
import com.example.cashassignment.view.fragment.AllMissionFragment
import com.example.cashassignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.view_recyclerview.view.*

class AllMissionFragmentViewPagerAdapter(
    private val allMissionFragment: AllMissionFragment) :
    RecyclerView.Adapter<AllMissionFragmentViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_recyclerview, parent, false),
            allMissionFragment.homeViewModel
        )

    override fun getItemCount(): Int = TaskCategory.values().size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        allMissionFragment.homeViewModel.getTaskPageData(TaskCategory.values()[position]).observe(allMissionFragment, Observer {
            holder.bind(it)
            it.addWeakCallback(null, object: PagedList.Callback() {
                override fun onChanged(position: Int, count: Int) {}
                override fun onInserted(position: Int, count: Int) {
                    holder.itemView.constraintLayout_missionFragment_empty.visibility = View.GONE
                }
                override fun onRemoved(position: Int, count: Int) {}
            })
        })
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
                            if(homeViewModel.checkIsLogin()){
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
                        }
                    })
                }
            }
        }
    }
}