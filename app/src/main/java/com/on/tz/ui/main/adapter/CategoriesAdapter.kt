package com.on.tz.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.on.tz.data.model.CategoryEntity
import com.on.tz.databinding.ItemCategoriesBinding

class CategoriesAdapter(val onClick: (CategoryEntity) -> Unit) :
    ListAdapter<CategoryEntity, CategoriesAdapter.CategoriesViewHolder>(StringDiffCallback()) {

    private val categoryList = mutableListOf<CategoryEntity>()


  fun addData(list: List<CategoryEntity>) {
       categoryList.clear()
       categoryList.addAll(list.filter { it.name.isNotEmpty() })
       submitList(categoryList)
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class CategoriesViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: CategoryEntity) {
                binding.btnCategories.text = category.name

            itemView.setOnClickListener {
                onClick(category)
            }
        }
    }

    private class StringDiffCallback : DiffUtil.ItemCallback<CategoryEntity>() {
        override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem == newItem
        }
    }
}
