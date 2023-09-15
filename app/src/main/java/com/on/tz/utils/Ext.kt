package com.on.tz.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.on.tz.data.model.CategoryEntity

const val TAG = "ololo"
const val TAG2 = "shalala"

fun historyArrayToJson(list: ArrayList<String>): String {
    return Gson().toJson(list)
}

fun historyToArray(json: String): ArrayList<String> {
    return try {
        Gson().fromJson<ArrayList<String>>(json)
    } catch (e: Exception) {
        arrayListOf()
    }
}

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

fun ImageView.loadImage(text: String) {
    Glide.with(this).load(text).into(this)
}

fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun mapCategoriesFromApiToEntity(apiCategories: List<String>): List<CategoryEntity> {
    val categoryEntities = mutableListOf<CategoryEntity>()
    for (i in apiCategories) {
            val categoryEntity = CategoryEntity(name = i)
            categoryEntities.add(categoryEntity)
    }
    return categoryEntities
}

fun <T : ViewBinding> Context.createDialog(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> T,
): Pair<T, Dialog> {
    val inflater: LayoutInflater = LayoutInflater.from(this)
    val binding = inflate.invoke(inflater, null, false)
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(binding.root)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.show()
    return Pair(binding, dialog)
}

fun View.toGone() {
    isGone = true
}

fun View.toVisible() {
    isVisible = true
}
