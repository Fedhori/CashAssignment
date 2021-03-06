package com.example.cashassignment.view.customview

import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.cashassignment.R
import com.example.cashassignment.view.fragment.HomeFragment

class MissionView @JvmOverloads constructor(
    context: HomeFragment,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context.activity, attrs, defStyleAttr) {

    init {
        inflate(context.activity, R.layout.view_mission, this)
    }
}
