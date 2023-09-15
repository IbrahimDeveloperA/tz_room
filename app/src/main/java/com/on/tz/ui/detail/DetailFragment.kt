package com.on.tz.ui.detail

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.on.tz.R
import com.on.tz.core.base.BaseDialog
import com.on.tz.core.network.UIState
import com.on.tz.data.model.Product
import com.on.tz.databinding.AlertDialogBinding
import com.on.tz.databinding.FragmentDetailBinding
import com.on.tz.ui.detail.viewModel.DetailViewModel
import com.on.tz.utils.Constants.ARGS_SEND_DATA
import com.on.tz.utils.createDialog
import com.on.tz.utils.loadImage
import com.on.tz.utils.toGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseDialog(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()
    private var product: Product? = null

    override fun initialize() {
        product = arguments?.getSerializable(ARGS_SEND_DATA) as Product?
    }

    override fun setupRequests() {
        viewModel.getProduct(product?.id)
    }

    override fun setupObservers() {
        viewModel.getProduct.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
                binding.progressBar.visibility = View.VISIBLE
                binding.display.visibility = View.INVISIBLE
            },
            onSuccess = {
                binding.progressBar.visibility = View.GONE
                binding.display.visibility = View.VISIBLE
                binding.tvCategory.title.text = it.category
                binding.tvRating.text = it.rating.rate.toString()
                binding.tvTitle.text = it.title
                binding.imgImage.loadImage(it.image)
                binding.tvPrice.text = it.price.toString()
                binding.tvDesc.text = it.description
                Log.e("ololo", "setupObservers: $it")
            }


        )
    }

    override fun initClickListeners() {
        binding.btnShare.setOnClickListener {
            alertDialog()
        }
    }

    private fun alertDialog(){
        val dialog = requireContext().createDialog(AlertDialogBinding::inflate)
        dialog.first.txtTitle.text = "Желаете ли вы поделится продуктом?"
        dialog.first.txtDescription.toGone()
        dialog.first.btnYes.setOnClickListener {
            shareData()
            dialog.second.dismiss()
        }
        dialog.first.btnNo.setOnClickListener {
            dialog.second.dismiss()
        }
    }

    private fun shareData(){
        val url = product.toString()
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(intent, "Share via"))
    }
}
