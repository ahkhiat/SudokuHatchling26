package com.devid_academy.sudokuhatchling26.logic.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.dto.LeaderboardItemDTO
import com.devid_academy.sudokuhatchling26.logic.data.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LeaderboardViewModel(
    private val gameRepository: GameRepository
): ViewModel() {

    private val _leaderboardStateFlow = MutableStateFlow<List<LeaderboardItemDTO>>(emptyList())
    val leaderboardStateFlow: StateFlow<List<LeaderboardItemDTO>> = _leaderboardStateFlow

    init {
        getLeaderboard()
    }

    fun getLeaderboard() {
        viewModelScope.launch {
            val leaderboard = withContext(Dispatchers.IO) {
                gameRepository.getLeaderboard()
            }
            leaderboard?.let {
                _leaderboardStateFlow.value = leaderboard
                Log.i("LeaderboardViewModel", "Leaderboard: $leaderboard")
            }
        }
    }



}