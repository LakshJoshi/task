package com.practicaltask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.practicaltask.data.entity.home.Category
import com.practicaltask.databinding.ItemCategoryBinding


class CategoryAdapter(
    val context: Context,
    private var categoryArrayList: ArrayList<Category>,
    private val listener: CategoryListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryVh>() {

    interface CategoryListener {
        
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CategoryVh {
        return CategoryVh(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class CategoryVh(binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        var ivProduct = binding.ivCategory




        fun setData(){
            val catObj = categoryArrayList[adapterPosition]
            ivProduct.load(catObj.image) {
                scale(Scale.FILL)
                transformations( CircleCropTransformation())
            }
        }
        init {

        }
    }

    override fun onBindViewHolder(holder: CategoryVh, position: Int) {
        holder.setData()
    }

    override fun getItemCount(): Int = categoryArrayList.size

    fun updateList(categoryArrayList: ArrayList<Category>) {
        this.categoryArrayList = categoryArrayList
        notifyDataSetChanged()
    }
}