package com.st11.companionwatchlist.screens.components



import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import com.st11.companionwatchlist.R
import com.st11.companionwatchlist.utils.DatePickerField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDetailsPopUp(
//    initialText: String,
    onDismiss: () -> Unit,
//    onSave: (String) -> Unit,
    itemId: String
) {

    val backgroundColor = colorResource(id = R.color.polynesian_blue)



    var title by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var watchlistPageNo by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var expanded01 by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    val watchListType = listOf(
        "Tv Show", "Book"
    )


    val categoryType = listOf(
        "Other",
        // Movies
        "Action",
        "Adventure",
        "Animation",
        "Comedy",
        "Crime",
        "Drama",
        "Fantasy",
        "Historical",
        "Horror",
        "Musical",
        "Mystery",
        "Romance",
        "Sci-Fi",
        "Thriller",
        "War",
        "Western",

        // TV Shows
        "Sitcom",
        "Talk Show",
        "Reality",
        "Documentary",
        "Game Show",
        "Soap Opera",
        "Drama Series",
        "Mini-Series",
        "Crime Series",
        "Fantasy Series",
        "Mystery Series",

        // Books
        "Fiction",
        "Non-Fiction",
        "Biography",
        "Autobiography",
        "Self-Help",
        "Poetry",
        "Science Fiction",
        "Fantasy",
        "Mystery",
        "Thriller",
        "Romance",
        "Historical Fiction",
        "Philosophy",
        "Religion",
        "Young Adult",
        "Children’s Literature"
    )




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
                Text(text = "Edit Watchlist Details", fontWeight = FontWeight.Bold, fontSize = 18.sp)


                    OutlinedTextField(
                        value = title ,
                        onValueChange = { title  = it },
                        label = { Text("Title *") },
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
                    )

                    Text(
                        text = "Enter the Details or Notes of Book/Tv show use markup for styling",
                        modifier = Modifier
                            .padding(end = 16.dp, start = 16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    OutlinedTextField(
//                        value = notes,
                        value =itemId,
                        onValueChange = { notes = it },
                        label = { Text("short Notes") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 200.dp) // Adjust height for ~4 lines
                            .verticalScroll(rememberScrollState()),

                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
                            focusedBorderColor = backgroundColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = backgroundColor,
                            cursorColor = backgroundColor
                        ),
                        singleLine = false,
                        maxLines = 4
                    )



                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = type,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Watchlist type *") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
//                        .menuAnchor(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                                focusedContainerColor = Color.White.copy(alpha = 0.95f),
                                focusedBorderColor = backgroundColor,
                                unfocusedBorderColor = Color.Gray,
                                focusedLabelColor = backgroundColor,
                                cursorColor = backgroundColor
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .background(Color.White) // ✅ White background for the dropdown menu
                        ) {
                            watchListType.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption, color = Color.Black) }, // ✅ Black text
                                    onClick = {
                                        type = selectionOption
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    OutlinedTextField(
                        value = watchlistPageNo,
                        onValueChange = { watchlistPageNo = it },
                        label = { Text("Page or Episode Number*") },
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


                    ExposedDropdownMenuBox(
                        expanded = expanded01,
                        onExpandedChange = { expanded01 = !expanded01 }
                    ) {
                        OutlinedTextField(
                            value = category,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Category") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
//                        .menuAnchor(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded01)
                            },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                                focusedContainerColor = Color.White.copy(alpha = 0.95f),
                                focusedBorderColor = backgroundColor,
                                unfocusedBorderColor = Color.Gray,
                                focusedLabelColor = backgroundColor,
                                cursorColor = backgroundColor
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expanded01,
                            onDismissRequest = { expanded01 = false },
                            modifier = Modifier
                                .background(Color.White) // ✅ White background for the dropdown menu
                        ) {
                            categoryType.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption, color = Color.Black) }, // ✅ Black text
                                    onClick = {
                                        category = selectionOption
                                        expanded01 = false
                                    }
                                )
                            }
                        }
                    }
                    DatePickerField(
                        label = "Expected completion date",
                        value = selectedDate, // <-- This is from your parent state
                        onDateSelected = { date -> selectedDate = date }
                    )





                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Gray // text color
                        )
                        ) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {

                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.royal_blue_traditional), // Green background
                            contentColor = Color.White          // White text
                        )
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}