package com.example.cashassignment.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cashassignment.R

class MissionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_mission, this)
    }
}
