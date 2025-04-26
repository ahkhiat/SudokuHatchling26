package com.devid_academy.sudokuhatchling26.logic.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.repository.GameRepository
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameRepository: GameRepository
): ViewModel() {
    private val _difficultyIndexStateFlow = MutableStateFlow<LevelChoiceEnum>(LevelChoiceEnum.Beginner)
    val difficultyIndexStateFlow: StateFlow<LevelChoiceEnum> = _difficultyIndexStateFlow

    fun setDifficultyIndex(difficultyIndex: LevelChoiceEnum) {
        _difficultyIndexStateFlow.value = difficultyIndex
    }

    fun getGrid() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = gameRepository.getRandomGridByDifficulty(difficultyIndexStateFlow.value)
            Log.i("GAME VM", "Niveau : ${difficultyIndexStateFlow.value}, Grid : $result")
        }
    }



}