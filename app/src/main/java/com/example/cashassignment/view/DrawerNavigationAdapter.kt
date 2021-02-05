package com.example.cashassignment.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cashassignment.R
import com.example.cashassignment.item.DrawerNavigationItem
import kotlinx.android.synthetic.main.drawer_home_navigation.view.*

class DrawerNavigationAdapter : RecyclerView.Adapter<DrawerNavigationAdapter.ViewHolder>(){

    private var navigationItemList: List<DrawerNavigationItem>? = null
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.drawer_home_navigation, parent, false)
        )

    override fun getItemCount(): Int = navigationItemList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(navigationItemList?.get(position))

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    fun submitList(navigationItemList: List<DrawerNavigationItem>?){
        this.navigationItemList = navigationItemList
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: DrawerNavigationItem?){
            with(itemView){
                textView_navigation_title.text = item?.title
            }
        }
    }

}