package com.practicaltask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.practicaltask.data.entity.home.ShopByBrand
import com.practicaltask.databinding.ItemShopByBrandBinding


class ShopByBrandAdapter(
    val context: Context,
    private var shopArrayList: ArrayList<ShopByBrand>,
    private val listener: BrandListener
) : RecyclerView.Adapter<ShopByBrandAdapter.BrandVh>() {

    interface BrandListener {
        
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopByBrandAdapter.BrandVh {
        return BrandVh(
            ItemShopByBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class BrandVh(binding: ItemShopByBrandBinding) : RecyclerView.ViewHolder(binding.root) {
        var ivBrand = binding.ivBrand
        

        fun setData(){
            val catObj = shopArrayList[adapterPosition]
            ivBrand.load(catObj.image) {
                scale(Scale.FILL)
                CircleCropTransformation()
            }
        }
        init {

        }
    }

    override fun onBindViewHolder(holder: BrandVh, position: Int) {
        holder.setData()
    }

    override fun getItemCount(): Int = shopArrayList.size

    fun updateList(shopArrayList: ArrayList<ShopByBrand>) {
        this.shopArrayList = shopArrayList
        notifyDataSetChanged()
    }
}