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
        Log.d("Bind", bannerList?.get(position)?.thumbnailUrl.toString())
        Glide.with(context).load(bannerList?.get(position)?.thumbnailUrl).into(holder.imageUrl)
    }

    fun submitList(bannerList: List<BannerEntity>?){
        this.bannerList = bannerList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageUrl: ImageView = view.findViewById(R.id.imageView_banner)
    }
}