package com.d12.companionwatchlist.screens


import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d12.companionwatchlist.R
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d12.companionwatchlist.utils.StatusBarColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(navController: NavController, appDesc: String) {
    val context = LocalContext.current

    val backgroundColor = colorResource(id = R.color.colorful)
    StatusBarColor(backgroundColor) // ✅ Keeps status bar consistent

    val versionName = try {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        // This should not happen, but it's good practice to have a fallback
        e.printStackTrace()
        "1.0.0"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("About the app", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = backgroundColor
                )
            )
        },
        containerColor = colorResource(id = R.color.light_bg_color)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(8.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 🔹 App Title
                Text(
                    text = "Companion Watchlist",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.main_color)
                )

                Spacer(modifier = Modifier.height(4.dp)) // small space between texts

                // 🔹 Version
                Text(
                    text = "Version $versionName",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }



            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "About the App",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.main_color)
            )
            // 🔹 app Description Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.main_color)),
                border = BorderStroke(2.dp, colorResource(id = R.color.black)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = appDesc,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        lineHeight = 22.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Legal Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, "https://injraapps.github.io/CompanionWatchlist/policy.html".toUri())
                    context.startActivity(intent)
                }) {
                    Text(
                        text = "Privacy Policy",
                        color = colorResource(id = R.color.main_color),
                        fontWeight = FontWeight.Medium
                    )
                }

            }

            Spacer(modifier = Modifier.height(28.dp))

            // 🔹 Intellectual Property & Attribution

            Text(
                text = "Intellectual Property Notice",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = """
All third-party assets used in this app, including icons, 
images, and other media, remain the property of their respective owners and 
are utilized in accordance with their free-to-use licenses.
 This app does not claim ownership of any third-party content. All trademarks, 
 logos, and brand names appearing in the app are the property of their respective 
 owners. Any original content in this app is either created by the developer or 
 used with proper licensing and attribution. The developer fully
 respects intellectual property rights and acknowledges all third-party ownership.
            """.trimIndent(),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )



        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutAppScreenScreenPreview() {
    AboutAppScreen(navController = rememberNavController(),"app description")
}



