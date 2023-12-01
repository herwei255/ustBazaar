package com.okit.ustBazaar.screens.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.okit.ustBazaar.R
import com.okit.ustBazaar.components.SecondaryTopBar
import com.okit.ustBazaar.ui.theme.Dimension

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    onBackRequested: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(Dimension.pagePadding),
        contentPadding = PaddingValues(bottom = Dimension.pagePadding)
    ) {
        stickyHeader {
            SecondaryTopBar(
                title = stringResource(id = R.string.settings),
                onBackClicked = onBackRequested
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimension.pagePadding)
                ) {
                    Text(text = "Settings")
                }
            }
        }
    }
//    Column(
//    ) {
//        SettingItem(
//            title = "Setting 1",
//            description = "Seting 1 option",
//            checked = remember { mutableStateOf(true) },
//            onCheckedChange = { /* Handle notification switch change */ }
//        )
//        SettingItem(
//            title = "Seting 2",
//            description = "Seting 2 option",
//            checked = remember { mutableStateOf(false) },
//            onCheckedChange = { /* Handle dark mode switch change */ }
//        )
//    }
}


@Composable
fun SettingItem(
    title: String,
    description: String,
    checked: MutableState<Boolean>,
    onCheckedChange: (Boolean) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = title)
        Text(text = description)
        Switch(checked = checked.value, onCheckedChange = onCheckedChange)
    }
}