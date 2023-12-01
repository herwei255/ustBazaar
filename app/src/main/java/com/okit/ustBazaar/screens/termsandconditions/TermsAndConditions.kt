package com.okit.ustBazaar.screens.termsandconditions;

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.okit.ustBazaar.components.SecondaryTopBar
import com.okit.ustBazaar.R
import com.okit.ustBazaar.ui.theme.Dimension


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TermsAndConditionsScreen(
    termsAndConditionsViewModel: TermsAndConditionsViewModel = hiltViewModel(),
    onBackRequested: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        stickyHeader {
            SecondaryTopBar(
                title = stringResource(id = R.string.terms_and_conditions),
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
                    Text(text = "Acceptance of Terms and Conditions:")
                    Text(text = "By accessing and using this application, you agree to be bound by the following terms and conditions. If you do not agree with any of these terms, please refrain from using the application. These terms and conditions govern your use of the application and all its associated services. You understand and acknowledge that these terms may be updated or modified from time to time, and it is your responsibility to review them periodically. Continued usage of the application after any amendments constitutes your acceptance of the revised terms and conditions.")

                    Spacer(modifier = Modifier.height(Dimension.sm))

                    Text(text = "User Responsibilities:")
                    Text(text = "When using this application, you agree to comply with all applicable laws and regulations. You are solely responsible for any content you submit, post, or share through the application. You must ensure that your actions and activities do not violate the rights of others or infringe upon any intellectual property rights. You agree not to engage in any unauthorized or illegal activities, including but not limited to hacking, spamming, or transmitting malicious software. Furthermore, you understand and accept that the application's administrators reserve the right to suspend or terminate your access to the services if you violate any of these terms and conditions.")
                }
            }
        }
    }
}