package com.on.tz.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.on.tz.core.network.Resource
import com.on.tz.core.network.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<Resource<T>>.collectData(
        _state: MutableStateFlow<UIState<T>>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectData.collect() { res ->
                when (res) {
                    is Resource.Error -> {
                        _state.value = UIState.Error(res.message ?: "know")
                    }

                    is Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        if (res.data != null) {
                            _state.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }
    protected fun <T> Flow<Resource<List<T>>>.collectDataList(
        _state: MutableStateFlow<UIState<List<T>>>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectDataList.collect() { res ->
                when (res) {
                    is Resource.Error -> {
                        _state.value = UIState.Error(res.message ?: "know")
                    }

                    is Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        if (res.data != null) {
                            _state.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }

}