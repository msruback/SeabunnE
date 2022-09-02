package com.beryl.seabunne.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.beryl.seabunne.api.OrchestratorResponse

abstract class SplatnetViewModel(application: Application) : AndroidViewModel(application) {

    var request: LiveData<OrchestratorResponse<Int>> = liveData {
        emit(OrchestratorResponse.Loading())
        emit(getData())
    }

    protected abstract suspend fun getData(): OrchestratorResponse<Int>

    fun refresh() {
        request = liveData {
            emit(OrchestratorResponse.Loading())
            emit(getData())
        }
    }
}
