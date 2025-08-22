package com.st11.companionwatchlist.screens.components


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.st11.companionwatchlist.R
import com.st11.companionwatchlist.viewmodel.WatchListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStatusPopup(
    onDismiss: () -> Unit,
    itemId: String,
    currentPageEpisode: Int

) {

    val backgroundColor = colorResource(id = R.color.polynesian_blue)

//    var episodePage by remember { mutableStateOf("") }
    var episodePage by remember { mutableStateOf(currentPageEpisode.toString()) }
    var venue by remember { mutableStateOf("") }
    val watchListViewModel: WatchListViewModel = koinViewModel()




    val context = LocalContext.current


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
                Text(text = "Update progress(page/episode you've reached)", fontWeight = FontWeight.Bold, fontSize = 18.sp)


                OutlinedTextField(
                    value = episodePage,
//                    value = itemId,
                    onValueChange = { episodePage = it },
                    label = { Text("Current Episode/Page *") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Gray // text color
                        )) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {

                        if(episodePage.isNotEmpty()){
                           watchListViewModel.updateSeenPageEpisodeById(
                               itemId = itemId,
                               newSeenPageEpisode = episodePage.toInt()
                           )

                            Toast.makeText(context, "Progress updated!", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Please Enter the Episode or page you've Reached", Toast.LENGTH_SHORT).show()
                        }

                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.royal_blue_traditional), // Green background
                            contentColor = Color.White          // White text
                        )) {
                        Text("Save")
                    }
                }
            }
        }
    }
}