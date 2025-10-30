//package com.d12.companionwatchlist.screens.components
//
//
//import android.content.Intent
//import android.net.Uri
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.clickable
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Lock
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun CompanionInfoSection(
//    privacyUrl: String,
//    appName: String,
//    aboutText: String,
//    modifier: Modifier = Modifier
//) {
//    val context = LocalContext.current
//    var showAbout by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        // Privacy Policy Card
//        OutlinedCard(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable {
//                    // Open privacy URL in browser
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyUrl))
//                    context.startActivity(intent)
//                },
//            elevation = CardDefaults.outlinedCardElevation()
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Lock,
//                    contentDescription = "Privacy Policy",
//                    modifier = Modifier.size(28.dp)
//                )
//
//                Spacer(modifier = Modifier.width(12.dp))
//
//                Column(modifier = Modifier.weight(1f)) {
//                    Text(
//                        text = "Privacy Policy",
//                        style = MaterialTheme.typography.titleMedium
//                    )
//                    Text(
//                        text = "Read our privacy policy (opens in browser)",
//                        style = MaterialTheme.typography.bodySmall,
//                        maxLines = 2,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                }
//
//                // optional chevron or action icon
//                Text(
//                    text = "Open",
//                    style = MaterialTheme.typography.labelLarge,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
//            }
//        }
//
//        // About Card
//        OutlinedCard(
//            modifier = Modifier
//                .fillMaxWidth(),
//            elevation = CardDefaults.outlinedCardElevation()
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Info,
//                    contentDescription = "About",
//                    modifier = Modifier.size(28.dp)
//                )
//
//                Spacer(modifier = Modifier.width(12.dp))
//
//                Column(modifier = Modifier.weight(1f)) {
//                    Text(
//                        text = appName,
//                        style = MaterialTheme.typography.titleMedium
//                    )
//                    Text(
//                        text = "A private, offline watchlist for books, movies, and TV shows.",
//                        style = MaterialTheme.typography.bodySmall,
//                        maxLines = 2,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                }
//
//                TextButton(onClick = { showAbout = true }) {
//                    Text(text = "About")
//                }
//            }
//        }
//    }
//
//    // About dialog
//    if (showAbout) {
//        AlertDialog(
//            onDismissRequest = { showAbout = false },
//            confirmButton = {
//                TextButton(onClick = { showAbout = false }) {
//                    Text("Close")
//                }
//            },
//            title = {
//                Text(text = "About $appName")
//            },
//            text = {
//                Column {
//                    Text(text = aboutText)
//                }
//            }
//        )
//    }
//}
package com.d12.companionwatchlist.screens.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun CompanionInfoSection(
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
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Privacy Policy Card
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // Open privacy URL in browser
                    val intent = Intent(Intent.ACTION_VIEW, privacyUrl.toUri())
                    context.startActivity(intent)
                },
            elevation = CardDefaults.outlinedCardElevation()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Privacy Policy",
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Privacy Policy",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Read our privacy policy (opens in browser)",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }


            }
        }

        // About Card
        OutlinedCard(
            modifier = Modifier.fillMaxWidth()
                .clickable{
                    showAbout = true
                },
            elevation = CardDefaults.outlinedCardElevation()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "About",
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "About",
                        style = MaterialTheme.typography.titleMedium
                    )
//                    Text(
//                        text = "A private, offline watchlist for books, movies, and TV shows.",
//                        style = MaterialTheme.typography.bodySmall,
//                        maxLines = 2,
//                        overflow = TextOverflow.Ellipsis
//                    )
                }

//                TextButton(onClick = { showAbout = true }) {
//                    Text(text = "About")
//                }
            }
        }


        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "message/rfc822"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("support@yourapp.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "App Support")
                            putExtra(Intent.EXTRA_TEXT, "Hello, i would like help with ...")
                        }
                        try {
                            context.startActivity(Intent.createChooser(intent, "Contact Support"))
                        } catch (e: Exception) {
                            Toast.makeText(context, "No email app found.", Toast.LENGTH_SHORT).show()
                        }

                },
            elevation = CardDefaults.outlinedCardElevation()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = "app support",
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "App Support",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Are you facing any difficult using our app?",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }


            }
        }
    }

    if (showAbout) {
        AboutAppPop(
            onDismiss = { showAbout = false},
            appName = appName,
            aboutApp = aboutText
        )
    }
}
