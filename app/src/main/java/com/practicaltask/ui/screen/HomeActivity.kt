package com.practicaltask.ui.screen

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.practicaltask.data.entity.APIResult
import com.practicaltask.databinding.ActivityHomeBinding
import com.practicaltask.ui.customview.BaseActivity

class HomeActivity : BaseActivity() {
    lateinit var homeBinding : ActivityHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        init()
        getHomeListData()
    }

    private fun init(){
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeBinding.apply {

            clBanner.apply {

            }

            clBestSeller.apply {

            }

            clCategory.apply {

            }

            clShopByBrand.apply {

            }
        }
    }

    private fun getHomeListData(){
        homeViewModel.getHomeData {
            when (it) {
                is APIResult.Success -> {
                    dismissDialog()
                }
                is APIResult.Failure -> {
                    dismissDialog()
                }
                APIResult.InProgress -> {
                    showDialog()
                }
            }
        }
    }
}