package com.okit.ustBazaar.screens.privacypolicy;

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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.okit.ustBazaar.components.SecondaryTopBar
import com.okit.ustBazaar.R
import com.okit.ustBazaar.ui.theme.Dimension

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrivacyAndPoliciesScreen(
    privacyAndPoliciesViewModel: PrivacyAndPoliciesViewModel = hiltViewModel(),
    onBackRequested: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        stickyHeader {
            SecondaryTopBar(
                title = stringResource(id = R.string.privacy_and_policies),
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
                    Text(text = "Information Collection and Use:", fontSize = 20.sp)
                    Text(text = "We collect certain personal information when you use our application. This may include your name, email address, and demographic information. We use this information to provide and improve our services, personalize your experience, and communicate with you. We may also collect non-personal information, such as device information and usage statistics, to analyze trends and optimize our application's performance.")

                    Spacer(modifier = Modifier.height(Dimension.xs))

                    Text(text = "Data Security:", fontSize = 20.sp)
                    Text(text = "We take the security of your personal information seriously and implement appropriate measures to protect it from unauthorized access, disclosure, or alteration. However, please note that no method of transmission over the internet or electronic storage is 100% secure, and we cannot guarantee absolute security. You are responsible for maintaining the confidentiality of any login credentials associated with your account.")

                    Spacer(modifier = Modifier.height(Dimension.xs))

                    Text(text = "Third-Party Services:", fontSize = 20.sp)
                    Text(text = "Our application may contain links to third-party websites or services that are not owned or controlled by us. We are not responsible for the privacy practices or content of these third-party entities. We recommend reviewing their privacy policies before providing any personal information.")

                    Spacer(modifier = Modifier.height(Dimension.xs))

                    Text(text = "Changes to the Privacy Policy:", fontSize = 20.sp)
                    Text(text = "We reserve the right to update or modify this privacy policy at any time. Any changes will be effective upon posting the revised policy on our application. It is your responsibility to review this policy periodically. By continuing to use our application after any modifications, you accept and agree to the updated privacy policy.")
                }
            }
        }
    }
}