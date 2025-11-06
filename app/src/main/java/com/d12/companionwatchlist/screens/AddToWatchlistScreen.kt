package com.d12.companionwatchlist.screens


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import com.d12.companionwatchlist.R
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d12.companionwatchlist.model.WatchListEntity
import com.d12.companionwatchlist.utils.DatePickerField
import com.d12.companionwatchlist.utils.StatusBarColor
import com.d12.companionwatchlist.viewmodel.WatchListViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Plus
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToWatchlistScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.colorful)
    StatusBarColor(backgroundColor)

    val context = LocalContext.current
  val watchListViewModel: WatchListViewModel = koinViewModel()



    var title by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var watchlistPageNo by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var expanded01 by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }

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

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Watchlist", color = Color.White) },
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
        containerColor = colorResource(id = R.color.light_bg_color),

        bottomBar = {
            // Bottom bar container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars) // ✅ Push above system nav bar
            ) {
                Button(
                    onClick = {
                        when {
                            title.isEmpty() -> {
                                Toast.makeText(context, "Please enter a title", Toast.LENGTH_LONG).show()
                                return@Button
                            }

                            watchlistPageNo.isEmpty() -> {
                                Toast.makeText(context, "Please enter Watchlist Page or Episode number", Toast.LENGTH_LONG).show()
                                return@Button
                            }

                            type.isEmpty() -> {
                                Toast.makeText(context, "Please select Watchlist Type", Toast.LENGTH_LONG).show()
                                return@Button
                            }

                            category.isEmpty() -> {
                                Toast.makeText(context, "Please select Watchlist Category", Toast.LENGTH_LONG).show()
                                return@Button
                            }

                            selectedDate.isEmpty() -> {
                                Toast.makeText(context, "Please select Expected Completion Date", Toast.LENGTH_LONG).show()
                                return@Button
                            }

                            else -> {
                                watchListViewModel.insertWatchlist(
                                    WatchListEntity(
                                        watchListTitle = title,
                                        expectedCompleteDate = selectedDate,
                                        link = link,
                                        type = type,
                                        notes = notes,
                                        category = category,
                                        noEpisodesPage = watchlistPageNo.toInt(),
                                        watchlistId = generateSixDigitRandomNumber().toString()
                                    )
                                )
                                navController.popBackStack()
                                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Plus,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Save To Watchlist",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    ) { paddingValues ->
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {

            // Subtitle
            Text(
                text = "Enter the Details of TV shows that " +
                        "you want to watch and Books You want to read (a must read or watch)",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column( verticalArrangement = Arrangement.spacedBy(12.dp) ){
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



                OutlinedTextField(
                    value = link ,
                    onValueChange = { link  = it },
                    label = { Text("link(optional)") },
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
                    text = "Enter the Details or Notes of Book/Tv (optional)",
                    modifier = Modifier
                        .padding(end = 16.dp, start = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("reference Notes") },
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
                        label = { Text("Category*") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable),
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
                    label = "Expected completion date*",
                    value = selectedDate, // <-- This is from your parent state
                    onDateSelected = { date -> selectedDate = date }
                )

            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun AddToWatchlistScreenPreview() {
    AddToWatchlistScreen(navController = rememberNavController())
}



@Composable
fun DropdownSelector(
    items: List<String>,
    selected: String?,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val customTextColor = colorResource(id = R.color.royal_blue_traditional) // from res/colors.xml
    val customBorderColor = colorResource(id = R.color.royal_blue_traditional)
    val customBackgroundColor = colorResource(id = R.color.white)

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = customBackgroundColor,
                contentColor = customTextColor
            ),
            border = BorderStroke(1.dp, customBorderColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selected ?: "Select",
                    style = MaterialTheme.typography.bodyLarge,
                    color = customTextColor
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Arrow",
                    tint = customTextColor
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge,
                            color = customTextColor
                        )
                    },
                    onClick = {
                        onSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}





fun generateSixDigitRandomNumber(): Int {
    return Random.nextInt(1000000, 1000000000)  // Generates a random number between 100000 (inclusive) and 1000000 (exclusive)
}