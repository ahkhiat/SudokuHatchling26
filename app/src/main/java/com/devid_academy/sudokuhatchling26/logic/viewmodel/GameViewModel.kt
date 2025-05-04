package com.devid_academy.sudokuhatchling26.logic.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.repository.GameRepository
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameRepository: GameRepository
): ViewModel() {
    private val _difficultyIndexStateFlow = MutableStateFlow<LevelChoiceEnum>(LevelChoiceEnum.Beginner)
    val difficultyIndexStateFlow: StateFlow<LevelChoiceEnum> = _difficultyIndexStateFlow

    private val _gridStateFlow = MutableStateFlow<List<List<Int>>>(emptyList())
    val gridStateFlow : MutableStateFlow<List<List<Int>>> = _gridStateFlow

    private val _gridSolutionStateFlow = MutableStateFlow<List<List<Int>>>(emptyList())
    val gridSolutionStateFlow : MutableStateFlow<List<List<Int>>> = _gridSolutionStateFlow

    private var _editableCells: List<Boolean> = emptyList()
    val editableCells: List<Boolean> get() = _editableCells

    private val _navigateToCompleteSharedFlow = MutableSharedFlow<NavigateSharedFlow>()
    val navigateToCompleteSharedFlow: SharedFlow<NavigateSharedFlow> = _navigateToCompleteSharedFlow

    fun setDifficultyIndex(difficultyIndex: LevelChoiceEnum) {
        _difficultyIndexStateFlow.value = difficultyIndex
    }

    fun getGrid() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = gameRepository.getRandomGridByDifficulty(difficultyIndexStateFlow.value)
            result?.let {
                _gridStateFlow.value = result.puzzle
                _gridSolutionStateFlow.value = result.solution
                _editableCells = result.puzzle.flatten().map { it == 0 }
            }
        }
    }

    fun updateCell(index: Int, newValue: Int) {
        if (!editableCells[index]) {
            Log.i("GAME VM", "Attempt to edit a non-editable cell at index $index")
            return
        }

        val currentGrid = _gridStateFlow.value.flatten().toMutableList()
        currentGrid[index] = newValue
        _gridStateFlow.value = currentGrid.chunked(9)
    }

    fun verifyGrid() {
        val currentGrid = _gridStateFlow.value
        val solutionGrid = _gridSolutionStateFlow.value

        viewModelScope.launch {
            if (currentGrid == solutionGrid) {
                Log.i("GAME VM", "Grid solved!")
                _navigateToCompleteSharedFlow.emit(NavigateSharedFlow.NavigateToCompleted)
            } else {
                Log.i("GAME VM", "Grid not solved yet.")
                _navigateToCompleteSharedFlow.emit(NavigateSharedFlow.NavigateToChooseLevel)
            }
        }

    }

    fun solvePuzzle() {
        _gridStateFlow.value = _gridSolutionStateFlow.value
    }
}

sealed class NavigateSharedFlow {
    data object NavigateToCompleted: NavigateSharedFlow()
    data object NavigateToChooseLevel: NavigateSharedFlow()
}