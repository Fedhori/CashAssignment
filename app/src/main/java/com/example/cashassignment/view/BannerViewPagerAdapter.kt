package com.example.cashassignment.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cashassignment.R
import com.example.cashassignment.databinding.ActivityMainBinding.inflate
import com.example.cashassignment.model.BannerEntity
import kotlinx.android.synthetic.main.view_banner.view.*

class BannerViewPagerAdapter(private val context: Context) :
   RecyclerView.Adapter<BannerViewPagerAdapter.ViewHolder>() {

    private var bannerList: List<BannerEntity>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_banner, parent, false))

    override fun getItemCount(): Int = bannerList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bannerList?.get(position))
    }

    fun submitList(bannerList: List<BannerEntity>?){
        this.bannerList = bannerList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: BannerEntity?){
            with(itemView){
                Glide.with(context).load(item?.thumbnailUrl).into(itemView.imageView_banner)
            }
        }
    }
}