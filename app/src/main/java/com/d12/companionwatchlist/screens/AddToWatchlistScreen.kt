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
import com.d12.companionwatchlist.R


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

//                    onClick = {
//
//
//                        if (title.isNotEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "Please enter a title",
//                                Toast.LENGTH_LONG
//                            ).show()
//                            return@Button
//                        }
//
//                        if (watchlistPageNo.isNotEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "Please enter watchlist Page or Episodes number",
//                                Toast.LENGTH_LONG
//                            ).show()
//                            return@Button
//                        }
//
//                        if (type.isNotEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "Please select watchlist Type",
//                                Toast.LENGTH_LONG
//                            ).show()
//                            return@Button
//                        }
//
//                        if (category.isNotEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "Please select watchlist Category",
//                                Toast.LENGTH_LONG
//                            ).show()
//                            return@Button
//                        }
//
//
//
//                        if (selectedDate.isNotEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "Please select Expected Completion Date",
//                                Toast.LENGTH_LONG
//                            ).show()
//                            return@Button
//                        }
//
////                    if(  &&  &&  && ){
//                       watchListViewModel.insertWatchlist(
//                           WatchListEntity(
//                               watchListTitle = title,
//                               expectedCompleteDate = selectedDate,
//                               link = link,
//                               type = type,
//                               notes = notes,
//                               category = category,
//                               noEpisodesPage = watchlistPageNo.toInt(),
//                               watchlistId = generateSixDigitRandomNumber().toString()
//                               )
//                       )
//                    navController.popBackStack()
//                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
////                    }else{
////                        Toast.makeText(context, "Please fill all the fields Marked with *", Toast.LENGTH_SHORT).show()
////                    }
//
//                    /* TODO: Handle click */ },
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


//@Composable
//fun DropdownSelector(items: List<String>, selected: String?, onSelected: (String) -> Unit) {
//    var expanded by remember { mutableStateOf(false) }
//    Box {
//        OutlinedButton(onClick = { expanded = true }) {
//            Text(selected ?: "Select")
//        }
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            items.forEach { item ->
//                DropdownMenuItem(
//                    text = { Text(item) },
//                    onClick = {
//                        onSelected(item)
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}



fun generateSixDigitRandomNumber(): Int {
    return Random.nextInt(1000000, 1000000000)  // Generates a random number between 100000 (inclusive) and 1000000 (exclusive)
}





//package com.d12.companionwatchlist.screens
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLayoutDirection
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.d12.companionwatchlist.R
//import com.d12.companionwatchlist.model.WatchListEntity
//import com.d12.companionwatchlist.utils.DatePickerField
//import com.d12.companionwatchlist.utils.StatusBarColor
//import com.d12.companionwatchlist.viewmodel.WatchListViewModel
//import compose.icons.FontAwesomeIcons
//import compose.icons.fontawesomeicons.Solid
//import compose.icons.fontawesomeicons.solid.Plus
//import org.koin.androidx.compose.koinViewModel
//import kotlin.random.Random
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddToWatchlistScreen(navController: NavController) {
//    val accent = colorResource(id = R.color.polynesian_blue)
//    StatusBarColor(accent)
//    val context = LocalContext.current
//    val watchListViewModel: WatchListViewModel = koinViewModel()
//
//    // Fields
//    var title by remember { mutableStateOf("") }
//    var notes by remember { mutableStateOf("") }
//    var total by remember { mutableStateOf("") }
//    var type by remember { mutableStateOf("") }
//    var expandedType by remember { mutableStateOf(false) }
//    var expandedCategory by remember { mutableStateOf(false) }
//    var category by remember { mutableStateOf("") }
//    var selectedDate by remember { mutableStateOf("") }
//    var link by remember { mutableStateOf("") }
//
//    val typeList = listOf("TV Show", "Book")
//    val categoryList = listOf(
//        "Other", "Action", "Drama", "Comedy", "Mystery", "Romance",
//        "Documentary", "Fantasy", "Thriller", "Self-Help", "Fiction", "Non-Fiction"
//    )
//
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text("Add to Watchlist", color = Color.White) },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back",
//                            tint = Color.White
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                    containerColor = accent
//                )
//            )
//        },
//        containerColor = colorResource(id = R.color.light_bg_color),
//        bottomBar = {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Button(
//                    onClick = {
//                        when {
//                            title.isEmpty() -> Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
//                            type.isEmpty() -> Toast.makeText(context, "Select type (Book or TV Show)", Toast.LENGTH_SHORT).show()
//                            total.isEmpty() -> Toast.makeText(context, "Enter total pages/episodes", Toast.LENGTH_SHORT).show()
//                            category.isEmpty() -> Toast.makeText(context, "Select a category", Toast.LENGTH_SHORT).show()
//                            else -> {
//                                watchListViewModel.insertWatchlist(
//                                    WatchListEntity(
//                                        watchListTitle = title,
//                                        expectedCompleteDate = selectedDate,
//                                        link = link,
//                                        type = type,
//                                        notes = notes,
//                                        category = category,
//                                        noEpisodesPage = total.toInt(),
//                                        watchlistId = generateSixDigitRandomNumber().toString()
//                                    )
//                                )
//                                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
//                                navController.popBackStack()
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(56.dp),
//                    shape = RoundedCornerShape(16.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = accent)
//                ) {
//                    Icon(
//                        imageVector = FontAwesomeIcons.Solid.Plus,
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier.size(18.dp)
//                    )
//                    Spacer(Modifier.width(8.dp))
//                    Text(
//                        text = "Save to Watchlist",
//                        color = Color.White,
//                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
//                    )
//                }
//            }
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
//                    start = padding.calculateStartPadding(LocalLayoutDirection.current) + 16.dp,
//                    top = padding.calculateTopPadding(),
//                    end = padding.calculateEndPadding(LocalLayoutDirection.current) + 16.dp,
//                    bottom = padding.calculateBottomPadding()
//                )
//                .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.spacedBy(18.dp)
//        ) {
//
//            Text(
//                text = "Add details about books you want to read or TV shows you plan to watch. Keep track and stay organized!",
//                color = Color.Gray,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(top = 8.dp)
//            )
//
//            // 🟣 Card Section
//            Card(
//                shape = RoundedCornerShape(20.dp),
//                colors = CardDefaults.cardColors(containerColor = Color.White),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
//
//                    // Title
//                    InputField(value = title, onValueChange = { title = it }, label = "Title *", accent)
//
//                    InputField(value = link, onValueChange = { link = it }, label = "Link (optional)", accent)
//
//                    InputField(
//                        value = notes,
//                        onValueChange = { notes = it },
//                        label = "Notes / Description",
//                        accent,
//                        singleLine = false,
//                        minHeight = 100.dp
//                    )
//
//                    // Type Dropdown
//                    DropdownField(
//                        label = "Watchlist Type *",
//                        value = type,
//                        expanded = expandedType,
//                        onExpandedChange = { expandedType = !expandedType },
//                        options = typeList,
//                        onSelect = { type = it; expandedType = false },
//                        accent = accent
//                    )
//
//                    // Total
//                    InputField(
//                        value = total,
//                        onValueChange = { total = it },
//                        label = "Total Pages / Episodes *",
//                        accent,
//                        keyboardType = KeyboardType.Number
//                    )
//
//                    // Category Dropdown
//                    DropdownField(
//                        label = "Category *",
//                        value = category,
//                        expanded = expandedCategory,
//                        onExpandedChange = { expandedCategory = !expandedCategory },
//                        options = categoryList,
//                        onSelect = { category = it; expandedCategory = false },
//                        accent = accent
//                    )
//
//                    DatePickerField(
//                        label = "Expected Completion Date",
//                        value = selectedDate,
//                        onDateSelected = { selectedDate = it }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun InputField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    accent: Color,
//    singleLine: Boolean = true,
//    minHeight: Dp = 56.dp,
//    keyboardType: KeyboardType = KeyboardType.Text
//) {
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        label = { Text(label) },
//        modifier = Modifier
//            .fillMaxWidth()
//            .heightIn(min = minHeight),
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedBorderColor = accent,
//            unfocusedBorderColor = Color.Gray,
//            focusedLabelColor = accent,
//            cursorColor = accent
//        ),
//        singleLine = singleLine,
//        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
//    )
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DropdownField(
//    label: String,
//    value: String,
//    expanded: Boolean,
//    onExpandedChange: () -> Unit,
//    options: List<String>,
//    onSelect: (String) -> Unit,
//    accent: Color
//) {
//    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { onExpandedChange() }) {
//        OutlinedTextField(
//            value = value,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text(label) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
//            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = accent,
//                unfocusedBorderColor = Color.Gray,
//                focusedLabelColor = accent
//            )
//        )
//        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange() }) {
//            options.forEach {
//                DropdownMenuItem(text = { Text(it) }, onClick = { onSelect(it) })
//            }
//        }
//    }
//}
//
//fun generateSixDigitRandomNumber(): Int = Random.nextInt(1000000, 1000000000)
//
//@Preview(showBackground = true)
//@Composable
//fun AddToWatchlistPreview() {
//    AddToWatchlistScreen(navController = rememberNavController())
//}

