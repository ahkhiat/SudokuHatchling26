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
import kotlinx.coroutines.withContext

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

    private var gridId: Int = 0
    private var playsGridId: Int = 0

    init {

    }

    fun setDifficultyIndex(difficultyIndex: LevelChoiceEnum) {
        _difficultyIndexStateFlow.value = difficultyIndex
    }

    fun getGrid() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("GAME VM", "Getting grid for difficulty: ${difficultyIndexStateFlow.value}")
            val existingGrid = gameRepository.getExistingGrid(difficultyIndexStateFlow.value)
            Log.i("GAME VM", "Existing grid: $existingGrid")
            if(existingGrid != null) {
                Log.i("GAME VM", "Existing grid content: ${existingGrid.gridContent}")
                Log.i("GAME VM", "Existing grid solution: ${existingGrid.grid.solution}")

                if (existingGrid.gridContent.isNotEmpty() && existingGrid.gridContent.all { it.isNotEmpty() }) {
                    Log.i("GAME VM", "Existing grid is valid.")
                    playsGridId = existingGrid.id.toInt()
                    _gridStateFlow.value = existingGrid.gridContent
                    _gridSolutionStateFlow.value = existingGrid.grid.solution
                    _editableCells = existingGrid.gridInitial.flatten().map { it == 0 }

                    Log.i("GAME VM", "Grid initial: ${existingGrid.gridInitial}")
                    Log.i("GAME VM", "Existing plays_grid id: $playsGridId")
                    Log.i("GAME VM", "Existing gridStateFlow updated: ${_gridStateFlow.value}")
                    Log.i("GAME VM", "Existing gridStateFlowSolution updated: ${_gridSolutionStateFlow.value}")
                } else {
                    Log.e("GAME VM", "Existing grid is empty or malformed.")
                }
            } else {
                Log.i("GAME VM", "Create new grid")

                val result = gameRepository.getRandomGridByDifficulty(difficultyIndexStateFlow.value)
                Log.i("GAME VM", "Grid fetched ID: ${result?.id}")
                result?.let {
                    gridId = result.id
                    _gridStateFlow.value = result.puzzle
                    _gridSolutionStateFlow.value = result.solution
                    _editableCells = result.puzzle.flatten().map { it == 0 }
                }
                Log.i("GAME VM", "Grid id from private var gridId: $gridId")
                createPlaysGridSupabase()
            }
        }
    }

    fun updateCell(index: Int, newValue: Int) {
        Log.i("GAME VM", "updateCell called with index: $index, newValue: $newValue")
        if (!editableCells[index]) {
            Log.i("GAME VM", "Attempt to edit a non-editable cell at index $index")
            return
        }
        val currentGrid = _gridStateFlow.value.flatten().toMutableList()
        currentGrid[index] = newValue
        _gridStateFlow.value = currentGrid.chunked(9)
        Log.i("GAME VM", "Grid updated : ${_gridStateFlow.value}")
        updatePlaysGridSupabase()
    }

    fun verifyGrid() {
        val currentGrid = _gridStateFlow.value
        val solutionGrid = _gridSolutionStateFlow.value

        viewModelScope.launch {
            if (currentGrid == solutionGrid) {
                Log.i("GAME VM", "Grid solved!")
                with(gameRepository) {
                    updatePlaysGrid(playsGridId, _gridStateFlow.value, isCompleted = true)
                    updateUserScore(difficultyIndexStateFlow.value.scoreValue)
                }

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

    fun createPlaysGridSupabase() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = gameRepository.createPlaysGrid(gridId, _gridStateFlow.value)
                Log.i("GAME VM", "plays_grid entry created with ID: $result")
                playsGridId = result ?: 0
                Log.i("GAME VM", "playsGridId: $playsGridId")
            }
        }
    }

    fun updatePlaysGridSupabase() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.i("GAME VM", "Updating plays grid with ID: $playsGridId, content: ${_gridStateFlow.value}")
                gameRepository.updatePlaysGrid(playsGridId, _gridStateFlow.value)
            }
        }
    }
}

sealed class NavigateSharedFlow {
    data object NavigateToCompleted: NavigateSharedFlow()
    data object NavigateToChooseLevel: NavigateSharedFlow()
}