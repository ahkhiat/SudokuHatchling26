package com.devid_academy.sudokuhatchling26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.devid_academy.sudokuhatchling26.ui.bootstrap.BootstrapComposable
import com.devid_academy.sudokuhatchling26.ui.theme.SudokuHatchling26Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SudokuHatchling26Theme {
                BootstrapComposable()
            }
        }
    }
}

