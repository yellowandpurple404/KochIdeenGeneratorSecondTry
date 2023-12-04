package com.example.kochideengenerator.ui.mainscreen

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.kochideengenerator.ui.IdeasEvent
import com.example.kochideengenerator.ui.mainscreenviewmodel.MainScreenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    var backgroundColor by remember { mutableStateOf(Color.Green) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val mainScreenState by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .combinedClickable(
                enabled = true,
                onDoubleClick = { viewModel.onEvent(IdeasEvent.DoubleClick()) }) {}
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX = dragAmount
                    when {
                        offsetX > 0 -> backgroundColor = Color.Black
                        offsetX < 0 -> backgroundColor = Color.White
                    }
                }
            }
            .pointerInput(Unit) {
                detectVerticalDragGestures { change, dragAmount ->
                    change.consume()
                    offsetY = dragAmount
                    when {
                        offsetY > 0 -> backgroundColor = Color.Blue
                        offsetY < 0 -> backgroundColor = Color.Red
                    }
                }
            },
        contentAlignment = Alignment.Center

    ) {
        Text(text = mainScreenState.displayedIdea.title)
    }
}