package com.example.cashassignment.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.cashassignment.R
import com.example.cashassignment.item.DrawerNavigationItem
import com.example.cashassignment.item.DrawerPointItem
import com.example.cashassignment.model.UserDetailEntity
import com.example.cashassignment.view.ItemClickListener
import com.example.cashassignment.view.adapter.DrawerNavigationAdapter
import com.example.cashassignment.view.adapter.DrawerPointAdapter
import com.example.cashassignment.view.fragment.AllMissionFragment
import com.example.cashassignment.view.fragment.HomeFragment
import com.example.cashassignment.view.fragment.MyMissionFragment
import com.example.cashassignment.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    NavigationView.OnNavigationItemSelectedListener{

    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeStatusBarTransparent()
        setDrawer()
        selectHomeNavigation()
    }

    private fun selectHomeNavigation(){
        bottomNavigationView_home.setOnNavigationItemSelectedListener(this)
        bottomNavigationView_home.selectedItemId = R.id.navigation_home
    }

    fun openDrawer(){
        drawerLayout_home.openDrawer(GravityCompat.END)
    }

    //TODO implement setOnClickListener
    private fun setDrawer(){
        if(homeViewModel.checkIsLogin()){
            setDrawerPointAdapter(homeViewModel.getUserDetail())
            layout_navigationView.constraintLayout_drawer_notLogin.visibility = View.GONE
        }
        else{
            layout_navigationView.constraintLayout_drawer_login.visibility = View.GONE
        }

        setDrawerNavigationAdapter()
    }
    
    private fun setDrawerPointAdapter(userDetailLiveData: LiveData<UserDetailEntity>){
        val drawerPointAdapter =
            DrawerPointAdapter()
        val pointItemList = ArrayList<DrawerPointItem>()

        layout_navigationView.recyclerView_drawer_login_point.adapter = drawerPointAdapter

        userDetailLiveData.observe(this, androidx.lifecycle.Observer { userDetailData ->
            layout_navigationView.textView_drawer_login_codeName.text = "${userDetailData.nickname} 요원님"
            with(pointItemList){
                add(DrawerPointItem("검사 통과 시 적립금", userDetailData.pendingPoint))
                add(DrawerPointItem("누적 적립금", userDetailData.totalPoint))
                add(DrawerPointItem("출금 가능한 금액", userDetailData.currentPoint))
            }
            drawerPointAdapter.submitList(pointItemList)
        })

        drawerPointAdapter.setItemClickListener( object:
            ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("test", pointItemList[position].title)
            }
        })
    }

    private fun setDrawerNavigationAdapter(){
        val drawerNavigationAdapter = DrawerNavigationAdapter()
        val navigationItemList = ArrayList<DrawerNavigationItem>()

        layout_navigationView.recyclerView_drawer_navigation.adapter = drawerNavigationAdapter

        with(navigationItemList){
            add(DrawerNavigationItem("공지사항"))
            add(DrawerNavigationItem("캐시미션 가이드"))
            add(DrawerNavigationItem("추천 제도"))
            add(DrawerNavigationItem("문의하기"))
        }

        drawerNavigationAdapter.submitList(navigationItemList)

        drawerNavigationAdapter.setItemClickListener( object:
            ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("test", navigationItemList[position].title)
            }
        })
    }

    private fun makeStatusBarTransparent(){
        // make status bar completely transparent
        val window = window
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun initFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_home,
            fragment
        ).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_home ->{
                initFragment(HomeFragment())
            }
            R.id.navigation_all_mission ->{
                initFragment(AllMissionFragment())
            }
            R.id.navigation_my_mission ->{
                initFragment(MyMissionFragment())
            }
        }
        return true
    }
}