package com.umkm.pos.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umkm.pos.domain.model.DashboardSummary
import com.umkm.pos.domain.repository.SalesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val salesRepository: SalesRepository
) : ViewModel() {
    private val _summary = MutableStateFlow(DashboardSummary(0, 0, "-"))
    val summary: StateFlow<DashboardSummary> = _summary.asStateFlow()

    fun loadDashboard() {
        viewModelScope.launch {
            _summary.value = salesRepository.dashboardSummary()
        }
    }
}
