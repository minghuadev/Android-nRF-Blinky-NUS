package no.nordicsemi.android.blinky.control.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import no.nordicsemi.android.blinky.control.R
import no.nordicsemi.android.blinky.control.viewmodel.BlinkyViewModel
import no.nordicsemi.android.blinky.spec.Blinky
import no.nordicsemi.android.common.logger.view.LoggerAppBarIcon
import no.nordicsemi.android.common.permission.RequireBluetooth
import no.nordicsemi.android.common.theme.view.NordicAppBar
import no.nordicsemi.android.common.ui.scanner.view.DeviceConnectingView
import no.nordicsemi.android.common.ui.scanner.view.DeviceDisconnectedView
import no.nordicsemi.android.common.ui.scanner.view.Reason

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
internal fun BlinkyScreen(
    onNavigateUp: () -> Unit,
) {
    val viewModel: BlinkyViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NordicAppBar(
            text = viewModel.deviceName,
            onNavigationButtonClick = onNavigateUp,
            actions = {
                LoggerAppBarIcon(onClick = { viewModel.openLogger() })
            }
        )
        RequireBluetooth {
            when (state) {
                Blinky.State.LOADING -> {
                    DeviceConnectingView(
                        modifier = Modifier.padding(16.dp),
                    ) { padding ->
                        Button(
                            onClick = onNavigateUp,
                            modifier = Modifier.padding(padding),
                        ) {
                            Text(text = stringResource(id = R.string.action_cancel))
                        }
                    }
                }
                Blinky.State.READY -> {
                    val ledState by viewModel.ledState.collectAsStateWithLifecycle()
                    val buttonState by viewModel.buttonState.collectAsStateWithLifecycle()
                    /* stackoverflow 66494520
                     * is-there-a-way-to-dynamically-change-the-string-in-text-of-compose
                     */
                    val userDataMut = remember { mutableStateOf(BlinkyUserData()) }
                    val userData by userDataMut

                    BlinkyControlView(
                        ledState = ledState,
                        buttonState = buttonState,
                        onStateChanged = { viewModel.turnLed(it, userData) },
                        userData = userData,
                        modifier = Modifier
                            .widthIn(max = 460.dp)
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    )
                }
                Blinky.State.NOT_AVAILABLE -> {
                    DeviceDisconnectedView(
                        reason = Reason.LINK_LOSS,
                        modifier = Modifier.padding(16.dp),
                    ) { padding ->
                        Button(
                            onClick = { viewModel.connect() },
                            modifier = Modifier.padding(padding),
                        ) {
                            Text(text = stringResource(id = R.string.action_retry))
                        }
                    }
                }
            }
        }
    }
}