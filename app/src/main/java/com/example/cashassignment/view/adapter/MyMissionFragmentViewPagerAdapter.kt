package com.example.cashassignment.view.adapter

import android.content.Intent
import android.database.DataSetObserver
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
import com.example.cashassignment.view.activity.LoginActivity
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
                    it.addWeakCallback(null, object: PagedList.Callback() {
                        override fun onChanged(position: Int, count: Int) {}
                        override fun onInserted(position: Int, count: Int) {
                            holder.itemView.constraintLayout_missionFragment_empty.visibility = View.GONE
                        }
                        override fun onRemoved(position: Int, count: Int) {}
                    })
                })
            }

            1 -> {
                myMissionFragment.homeViewModel.getRecentPageData().observe(myMissionFragment, Observer {
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
        }
    }

    class ViewHolder(itemView: View, private val homeViewModel: HomeViewModel) : RecyclerView.ViewHolder(itemView) {

        fun bind(taskList: PagedList<TaskEntity>){

            if(!homeViewModel.checkIsLogin()){
                itemView.constraintLayout_missionFragment_login.visibility = View.VISIBLE
                itemView.constraintLayout_missionFragment_empty.visibility = View.GONE
                itemView.button_missionFragment_login.setOnClickListener{
                    itemView.context.startActivity(Intent(itemView.context, LoginActivity::class.java))
                }
            }

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
                            if(taskList[position]?.isDibs == true){
                                taskList[position]?.isDibs = false
                                missionFragmentAdapter.emptyIcon()
                                taskList[position]?.id?.let { homeViewModel.deleteDibs(it) }
                            }
                            else{
                                taskList[position]?.isDibs = true
                                missionFragmentAdapter.fillIcon()
                                taskList[position]?.id?.let { homeViewModel.postDibs(it) }
                            }
                            notifyDataSetChanged()
                        }
                    })
                }
            }
        }
    }
}