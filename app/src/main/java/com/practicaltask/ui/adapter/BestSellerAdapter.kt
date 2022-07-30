package com.practicaltask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.practicaltask.data.entity.home.BestSellerList
import com.practicaltask.databinding.ItemBestSellerBinding


class BestSellerAdapter(
    val context: Context,
    private var bestSellerList: ArrayList<BestSellerList>,
    private val listener: SellerListener
) : RecyclerView.Adapter<BestSellerAdapter.BestSellerVh>() {

    interface SellerListener {
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerAdapter.BestSellerVh {
        return BestSellerVh(
            ItemBestSellerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class BestSellerVh(binding: ItemBestSellerBinding) : RecyclerView.ViewHolder(binding.root) {
        var productName = binding.tvProductName
        var ivProduct = binding.ivProduct
        var tvProductDesc = binding.tvProductDesc
        var tvProductPrice = binding.tvProductPrice



        fun setData(){

          val sellerObj = bestSellerList[adapterPosition]
            productName.text = sellerObj.msgBrand
            tvProductDesc.text = sellerObj.sku
            tvProductPrice.text = sellerObj.currencyCode + " " + sellerObj.finalPrice

            ivProduct.load(sellerObj.image) {
                scale(Scale.FILL)
                CircleCropTransformation()
            }
        }
        init {

        }
    }

    override fun onBindViewHolder(holder: BestSellerVh, position: Int) {
        holder.setData()
    }

    override fun getItemCount(): Int = bestSellerList.size

    fun updateList(bestSellerList: ArrayList<BestSellerList>) {
        this.bestSellerList = bestSellerList
        notifyDataSetChanged()
    }
}