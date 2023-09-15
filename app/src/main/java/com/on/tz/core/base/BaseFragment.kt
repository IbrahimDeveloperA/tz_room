package com.on.tz.core.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.on.tz.core.network.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRequests()
        setupObservers()
        initClickListeners()
    }

    open fun initialize() {}
    open fun setupRequests() {}
    open fun setupObservers() {}
    open fun initClickListeners() {}

    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        state: (UIState<T>) -> Unit,
        onSuccess: (data: T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectUIState.collect { res ->
                    state.invoke(res)
                    when (res) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Log.e("OLOLO", "collectUIState: ${res.message}", )
                            Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                        }

                        is UIState.Loading -> {}

                        is UIState.Success -> {
                            onSuccess(res.data)
                        }
                    }
                }
            }
        }
    }
}