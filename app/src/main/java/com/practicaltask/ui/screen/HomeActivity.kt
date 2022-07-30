package com.practicaltask.ui.screen

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicaltask.data.entity.APIResult
import com.practicaltask.databinding.ActivityHomeBinding
import com.practicaltask.ui.adapter.BestSellerAdapter
import com.practicaltask.ui.adapter.CategoryAdapter
import com.practicaltask.ui.adapter.ShopByBrandAdapter
import com.practicaltask.ui.adapter.VPAdapter
import com.practicaltask.ui.customview.BaseActivity
import java.util.*

class HomeActivity : BaseActivity(), BestSellerAdapter.SellerListener,CategoryAdapter.CategoryListener,ShopByBrandAdapter.BrandListener {
    lateinit var homeBinding: ActivityHomeBinding
    lateinit var homeViewModel: HomeViewModel

    private lateinit var vpAdapter: VPAdapter
    private lateinit var bestSellerAdapter: BestSellerAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var shopByBrandAdapter: ShopByBrandAdapter

    private var currentPage: Int = 0
    var timer: Timer? = null
    private val DELAY_MS: Long = 500
    private val PERIOD_MS: Long = 3000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        init()
        getHomeListData()
    }

    private fun init() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeBinding.apply {
            incBanner.vpBanner.apply {
                vpAdapter = VPAdapter(arrayListOf(), this@HomeActivity)
                adapter = vpAdapter
                incBanner.bannerDots.setViewPager(this)
            }

            incBestSeller.rvBestSeller.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity,RecyclerView.HORIZONTAL,false)
                bestSellerAdapter = BestSellerAdapter(this@HomeActivity, arrayListOf(), this@HomeActivity)
                adapter = bestSellerAdapter
            }

            incCategory.rvCategory.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity,RecyclerView.HORIZONTAL,false)
                categoryAdapter = CategoryAdapter(this@HomeActivity, arrayListOf(), this@HomeActivity)
                adapter = categoryAdapter
            }

            incShopByBrand.rvShopByBrand.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity,RecyclerView.HORIZONTAL,false)
                shopByBrandAdapter = ShopByBrandAdapter(this@HomeActivity, arrayListOf(), this@HomeActivity)
                adapter = shopByBrandAdapter
            }
        }
    }

    private fun slideBanner() {
        val update = Runnable {
            if (currentPage == homeViewModel.home.bannerArrayList.size) {
                currentPage = 0
            }
            homeBinding.incBanner.vpBanner.setCurrentItem(currentPage++, true)
        }


        timer?.cancel()
        timer = Timer() // This will create a new Thread

        timer?.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                baseHandler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)
    }


    private fun getHomeListData() {
        homeViewModel.getHomeData {
            when (it) {
                is APIResult.Success -> {
                    vpAdapter.update(homeViewModel.home.bannerArrayList)
                    slideBanner()
                    bestSellerAdapter.updateList(homeViewModel.home.bestSeller.bestSellerList)
                    categoryAdapter.updateList(homeViewModel.home.topCategoryArrayList)
                    shopByBrandAdapter.updateList(homeViewModel.home.shopByBrandList)
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