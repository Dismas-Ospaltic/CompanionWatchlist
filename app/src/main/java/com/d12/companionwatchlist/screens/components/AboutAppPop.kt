package com.d12.companionwatchlist.screens.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.d12.companionwatchlist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppPop(
//    initialText: String,
    onDismiss: () -> Unit,
    appName: String,
    aboutApp: String
) {

    val backgroundColor = colorResource(id = R.color.polynesian_blue)




    val context = LocalContext.current



    // ✅ 1. Get a coroutine scope tied to the composable's lifecycle
    val scope = rememberCoroutineScope()


    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .imePadding()
                    .verticalScroll(rememberScrollState()), // Adjust for keyboard
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                Text(text = "About $appName", fontWeight = FontWeight.Bold, fontSize = 18.sp)


                Text(text = aboutApp, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
//                text = {
//                    Text(text = aboutText)
//                }
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                }







                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    Button(
                        modifier = Modifier
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                        onClick = {





                            onDismiss()

                        }) {
                        Text("Close")
                    }
                }
            }
        }
    }
}