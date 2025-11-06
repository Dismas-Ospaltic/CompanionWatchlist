
package com.d12.companionwatchlist.screens.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.d12.companionwatchlist.navigation.Screen
import com.d12.companionwatchlist.ui.theme.AccentBlue
import com.d12.companionwatchlist.ui.theme.DeepPurple
import com.d12.companionwatchlist.ui.theme.MintGreen
import com.d12.companionwatchlist.ui.theme.SoftGray



@Composable
fun CompanionInfoSection(
    navController: NavController,
    privacyUrl: String,
    appName: String,
    aboutText: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showAbout by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(SoftGray, shape = RoundedCornerShape(16.dp)),
//            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val cardModifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { }

        // Privacy Policy Card
        ModernInfoCard(
            title = "Privacy Policy",
            subtitle = "Read our privacy policy (opens in browser)",
            icon = Icons.Default.Lock,
            iconTint = AccentBlue,
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, privacyUrl.toUri())
                context.startActivity(intent)
            }
        )

        // About Card
        ModernInfoCard(
            title = "About",
            subtitle = "Learn more about the app",
            icon = Icons.Default.Info,
            iconTint = DeepPurple,
            onClick = {
                navController.navigate(
                    Screen.AboutApp.createRoute(aboutText)
                )
            }
        )

        // App Support Card
        ModernInfoCard(
            title = "App Support",
            subtitle = "Facing any issues? Contact our support team.",
            icon = Icons.Default.MailOutline,
            iconTint = MintGreen,
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("injirasimon7@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "App Support request")
                    putExtra(Intent.EXTRA_TEXT, "Hello, I would like help with ...")
                }
                try {
                    context.startActivity(Intent.createChooser(intent, "Contact Support"))
                } catch (e: Exception) {
                    Toast.makeText(context, "No email app found.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    if (showAbout) {
        AboutAppPop(
            onDismiss = { showAbout = false },
            appName = appName,
            aboutApp = aboutText
        )
    }
}

