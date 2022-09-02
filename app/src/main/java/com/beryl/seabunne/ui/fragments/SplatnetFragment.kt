package com.beryl.seabunne.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.beryl.seabunne.NavGraphDirections
import com.beryl.seabunne.R
import com.beryl.seabunne.api.OrchestratorResponse
import com.beryl.seabunne.api.SplatnetFailedConnectionException
import com.beryl.seabunne.api.SplatnetMaintenanceException
import com.beryl.seabunne.api.SplatnetUnauthorizedException
import com.beryl.seabunne.ui.viewModels.SplatnetViewModel

abstract class SplatnetFragment<T : SplatnetViewModel> : Fragment() {

    abstract val viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request.observe(viewLifecycleOwner) {
            when (it) {
                is OrchestratorResponse.Error -> {
                    val action = NavGraphDirections.actionGlobalAlertDialogFragment()
                    action.alertMessage = when (it.exception) {
                        is SplatnetUnauthorizedException -> getString(R.string.auth_failure)
                        is SplatnetMaintenanceException -> getString(R.string.maintainence_failure)
                        is SplatnetFailedConnectionException -> getString(R.string.connection_failure)
                        else -> ""
                    }
                    findNavController().navigate(action)
                }
                is OrchestratorResponse.Loading -> {

                }
                is OrchestratorResponse.Success -> {

                }
            }
        }
    }


}