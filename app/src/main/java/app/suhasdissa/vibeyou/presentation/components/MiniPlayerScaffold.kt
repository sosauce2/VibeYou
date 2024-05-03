package app.suhasdissa.vibeyou.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.suhasdissa.vibeyou.presentation.screens.player.FullScreenPlayer
import app.suhasdissa.vibeyou.presentation.screens.player.MiniPlayer
import app.suhasdissa.vibeyou.presentation.screens.player.model.PlayerViewModel
import app.suhasdissa.vibeyou.utils.mediaItemState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniPlayerScaffold(
    playerViewModel: PlayerViewModel,
    content: @Composable (PaddingValues) -> Unit
) {
    var isPlayerSheetVisible by remember { mutableStateOf(false) }
    val playerSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    if (isPlayerSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isPlayerSheetVisible = false },
            sheetState = playerSheetState,
            shape = RoundedCornerShape(8.dp),
            dragHandle = null
        ) {
            playerViewModel.controller?.let { controller ->
                FullScreenPlayer(
                    controller,
                    onCollapse = {
                        scope.launch { playerSheetState.hide() }.invokeOnCompletion {
                            if (!playerSheetState.isVisible) {
                                isPlayerSheetVisible = false
                            }
                        }
                    }
                )
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            playerViewModel.controller?.let { controller ->
                val mediaItem by controller.mediaItemState()
                mediaItem?.let {
                    MiniPlayer(
                        onClick = {
                            isPlayerSheetVisible = true
                            scope.launch {
                                playerSheetState.show()
                            }
                        },
                        controller,
                        it,
                        playerViewModel
                    )
                }
            }
        }, content = content, contentWindowInsets = WindowInsets.systemBars
    )
}