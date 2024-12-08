package com.pwhs.quickmem.presentation.app.report

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwhs.quickmem.core.datastore.AppManager
import com.pwhs.quickmem.core.datastore.TokenManager
import com.pwhs.quickmem.core.utils.Resources
import com.pwhs.quickmem.domain.model.report.CreateReportRequestModel
import com.pwhs.quickmem.domain.repository.ReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportRepository: ReportRepository,
    private val appManager: AppManager,
    private val tokenManager: TokenManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReportUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<ReportUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val reportType = savedStateHandle.get<ReportTypeEnum>("reportType") ?: ReportTypeEnum.CLASS
        val reportedEntityId = savedStateHandle.get<String>("reportedEntityId") ?: ""
        val ownerOfReportedEntity = savedStateHandle.get<String>("ownerOfReportedEntity") ?: ""
        _uiState.update {
            it.copy(
                reportType = reportType,
                reportedEntityId = reportedEntityId,
                ownerOfReportedEntity = ownerOfReportedEntity
            )
        }
    }

    fun onEvent(event: ReportUiAction) {
        when (event) {
            is ReportUiAction.OnReasonChanged -> {
                _uiState.value = _uiState.value.copy(reason = event.reason)
            }

            is ReportUiAction.OnSubmitReport -> {
                sendReport()
            }
        }
    }

    private fun sendReport() {
        viewModelScope.launch {
            val token = tokenManager.accessToken.firstOrNull() ?: ""
            val reporterId = appManager.userId.firstOrNull() ?: ""
            val reason = _uiState.value.reason
            val reportedEntityId = _uiState.value.reportedEntityId
            val reportType = _uiState.value.reportType
            val ownerOfReportedEntity = _uiState.value.ownerOfReportedEntity

            val reportRequestModel = CreateReportRequestModel(
                reporterId = reporterId,
                reason = reason,
                reportedEntityId = reportedEntityId,
                ownerOfReportedEntityId = ownerOfReportedEntity,
                reportedType = reportType?.name ?: ""
            )

            reportRepository.createReport(
                token = token,
                createReportRequestModel = reportRequestModel
            ).collect { resources ->
                when (resources) {
                    is Resources.Error -> {
                        _uiState.update { it.copy(isLoading = false) }
                        _uiEvent.send(ReportUiEvent.OnError(resources.message ?: ""))
                    }

                    is Resources.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resources.Success -> {
                        _uiState.update { it.copy(isLoading = false) }
                        _uiEvent.send(ReportUiEvent.OnSubmitReport)
                    }
                }
            }
        }
    }
}