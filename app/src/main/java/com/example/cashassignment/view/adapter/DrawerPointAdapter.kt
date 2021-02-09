package com.example.cashassignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cashassignment.R
import com.example.cashassignment.item.DrawerPointItem
import com.example.cashassignment.view.ItemClickListener
import kotlinx.android.synthetic.main.drawer_home_point.view.*

class DrawerPointAdapter : RecyclerView.Adapter<DrawerPointAdapter.ViewHolder>(){

    private var pointItemList: List<DrawerPointItem>? = null
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.drawer_home_point, parent, false)
        )

    override fun getItemCount(): Int = pointItemList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(pointItemList?.get(position))

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    fun submitList(pointItemList: List<DrawerPointItem>?){
        this.pointItemList = pointItemList
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: DrawerPointItem?){
            with(itemView){
                textView_point_title.text = item?.title
                textView_point_point.text = "${item?.point}원"
            }
        }
    }

}