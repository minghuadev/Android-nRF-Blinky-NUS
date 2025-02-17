package no.nordicsemi.android.blinky.control.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.nordicsemi.android.blinky.control.R
import no.nordicsemi.android.common.theme.NordicTheme

@Composable
internal fun ButtonControlView(
    state: String,
    userData: BlinkyUserData,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    imageVector = Icons.Default.RadioButtonChecked,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
                Text(
                    text = stringResource(R.string.blinky_button),
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            userData.do_recv()
            var time_diff = userData.get_trip()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.blinky_button_descr),
                    modifier = Modifier.weight(1f)
                )
                val stMut = mutableStateOf(state)
                val stDisplay by stMut
                Text(
                    //text = if (state) stringResource(R.string.blinky_on) else stringResource(R.string.blinky_off),
                    stDisplay,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val addition1: String = state + " " + time_diff.toString()
                val add1Mut = mutableStateOf(addition1)
                val add1Display by add1Mut
                Text(add1Display)
            }
        }
    }
}

@Composable
@Preview
private fun ButtonControlViewPreview() {
    NordicTheme {
        ButtonControlView(
            state = "<true_preview_button_control_view>",
            userData = BlinkyUserData(),
            modifier = Modifier.padding(16.dp),
        )
    }
}