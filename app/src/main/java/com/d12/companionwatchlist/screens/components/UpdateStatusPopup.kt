
package com.d12.companionwatchlist.screens.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d12.companionwatchlist.R
import androidx.compose.ui.window.Dialog
import com.d12.companionwatchlist.model.ProgressEntity
import com.d12.companionwatchlist.viewmodel.ProgressViewModel
import com.d12.companionwatchlist.viewmodel.WatchListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStatusPopup(
    onDismiss: () -> Unit,
    itemId: String,
    totalEpisodes: Int,
    currentPageEpisode: Int,
    watchlistType: String
) {
    val backgroundColor = colorResource(id = R.color.polynesian_blue)
    val context = LocalContext.current

    var episodePage by remember { mutableStateOf(currentPageEpisode.toString()) }
    var note by remember { mutableStateOf("") }

    val watchListViewModel: WatchListViewModel = koinViewModel()
    val progressViewModel: ProgressViewModel = koinViewModel()

    // Collect history from ViewModel
    val progressHistory by progressViewModel.progressHistory.collectAsState()

    // Load progress history when dialog opens
    LaunchedEffect(itemId) {
        progressViewModel.loadProgressHistory(itemId)
    }

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
                modifier = Modifier
                    .padding(16.dp)
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Update progress (page/episode reached)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )



                Text(
        text = when (watchlistType) {
        "Tv Show" -> "Total Episodes: $totalEpisodes"
        "Book" -> "Total Pages: $totalEpisodes"
        else -> "Total Episodes/Pages: $totalEpisodes"
    },
    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.polynesian_blue)
                )

                OutlinedTextField(
                    value = episodePage,
                    onValueChange = { episodePage = it },
                    label = {
                        Text(
                            when (watchlistType) {
                                "Tv Show" -> "Current Episode *: $totalEpisodes"
                                "Book" -> "Current Page *: $totalEpisodes"
                                else -> "Current Episode/Page *: $totalEpisodes"
                            }
                        )
                    },
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

                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Refernce Note (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true
                )

                // 🔽 Scrollable History Section
                Text(
                    text = "Update History",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    if (progressHistory.isEmpty()) {
                        Text(
                            "No updates yet.",
                            color = Color.Gray,
                            modifier = Modifier.padding(8.dp)
                        )
                    } else {
                        progressHistory.forEach { history ->
                            ProgressHistoryCard(history, watchlistType)
                        }
                    }
                }

                // 🔘 Action Buttons (Fixed at bottom)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                    ) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (episodePage.isNotEmpty()) {
                                progressViewModel.updateProgress(
                                    watchlistId = itemId,
                                    newEpisode = episodePage.toInt(),
                                    totalEpisodes = totalEpisodes,
                                    note = note
                                )
                                onDismiss()
                                Toast.makeText(context, "Progress updated!", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please enter the episode or page reached.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.royal_blue_traditional),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

// 💳 Card Composable
@Composable
fun ProgressHistoryCard(progress: ProgressEntity, watchlistType: String) {
    val bgColor = when (progress.direction) {
        "forward" -> Color(0xFFDFF6DD)
        "backward" -> Color(0xFFFFE6E6)
        else -> Color.LightGray.copy(alpha = 0.2f)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        color = bgColor
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = when (watchlistType) {
                    "Tv Show" -> "From Episode ${progress.previousEpisodeReached} to Episode ${progress.newEpisodeReached} (${progress.direction})"
                    "Book" -> "From Page ${progress.previousEpisodeReached} to Page ${progress.newEpisodeReached} (${progress.direction})"
                    else -> "From Episode/Page ${progress.previousEpisodeReached} to ${progress.newEpisodeReached} (${progress.direction})"
                },
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 2.dp)
            )

//            Text(
//                text = "From Episode ${progress.previousEpisodeReached} to Episode ${progress.newEpisodeReached} (${progress.direction})",
//                fontWeight = FontWeight.SemiBold,
//                color = Color.Black
//            )

            Text(
                text = "Progress: ${"%.0f".format(progress.progressPercent)}%",
                fontSize = 13.sp,
                color = Color.DarkGray
            )
            Text(
                text = progress.dateUpdated,
                fontSize = 12.sp,
                color = Color.Gray
            )
            if (!progress.note.isNullOrBlank()) {
                Text(
                    text = "Note: ${progress.note}",
                    fontSize = 12.sp,
                    color = Color(0xFF555555)
                )
            }
        }
    }
}
