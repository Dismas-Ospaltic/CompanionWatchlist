package com.st11.companionwatchlist.screens



import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import com.st11.companionwatchlist.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.st11.companionwatchlist.navigation.Screen
import com.st11.companionwatchlist.screens.components.CircularPercentageBar
import com.st11.companionwatchlist.screens.components.EditDetailsPopUp
import com.st11.companionwatchlist.screens.components.UpdateStatusPopup
import com.st11.companionwatchlist.utils.DynamicStatusBar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ThumbsUp
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.CircleNotch
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.Eye
import compose.icons.fontawesomeicons.solid.EyeSlash
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.ShareAlt
import compose.icons.fontawesomeicons.solid.Store
import compose.icons.fontawesomeicons.solid.Trash
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.polynesian_blue)
    DynamicStatusBar(colorResource(id = R.color.white))
    // ✅ Define states for search
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var selectedNotes by remember { mutableStateOf("") }
    var selectedItemId by remember { mutableStateOf<String?>(null) }
// Calculate progress
    val viewPercentage by remember { mutableStateOf(0.7f) }
    // ✅ Track selected book for long-press actions
//    var selectedBook by remember { mutableStateOf<Book?>(null) }

    val selectedWishes = remember { mutableStateOf<Set<String>>(emptySet()) }
    var showDialog by remember { mutableStateOf(false) }

    var showEditDialog by remember { mutableStateOf(false) }

    val books = listOf(
        Book("The Name of the Wind", 662, "Fiction", "Fantasy"),
        Book("Dune", 688, "Fiction", "Sci-Fi"),
        Book("Atomic Habits", 320, "Non-Fiction", "Self-Help"),
        Book("The Pragmatic Programmer", 352, "Non-Fiction", "Tech")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = {
                                Text(
                                    "Search...",
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                    } else {
                        Text("Home", color = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { isSearching = !isSearching }) {
                        Icon(
                            imageVector = if (isSearching) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    if (!isSearching) {
//                    Icon(
//                        imageVector = if (isSearching) Icons.Default.Close else Icons.Default.Search,
//                        contentDescription = "Search",
//                        tint = Color.White
//                    )
                        IconButton(onClick = {
//                        isSearching = !isSearching
                        }) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Cog,
                                contentDescription = "heart",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

//                IconButton(onClick = { isSearching = !isSearching }) {
//                    Icon(
//                        imageVector = if (isSearching) Icons.Default.Close else Icons.Default.Search,
//                        contentDescription = "Search",
//                        tint = Color.White
//                    )
//                }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor, // dark green
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

//        bottomBar = {
//            // Bottom bar container
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
//                    .windowInsetsPadding(WindowInsets.navigationBars) // ✅ Push above system nav bar
//            ) {
//                Button(
//                    onClick = { /* TODO: Handle click */
//                        navController.navigate(Screen.AddToWatchlist.route)
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(56.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
//                ) {
//                    Icon(
//                        imageVector = FontAwesomeIcons.Solid.Plus,
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier.size(20.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Add To Watchlist",
//                        color = Color.White,
//                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
//                    )
//                }
//            }
//
//
//        }

        bottomBar = {
            // ✅ Hide Add button if actions are visible
//            if (selectedBook == null) {

            if (selectedWishes.value.isNotEmpty()) {
                // ✅ Action menu appears if long pressed
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 8.dp)
//                        .windowInsetsPadding(WindowInsets.navigationBars)
//                ) {
//                            // Row for Action Items
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceEvenly,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                ActionItem(Icons.Default.Edit, "Edit") { /* edit logic */ }
//                                ActionItem(Icons.Default.Edit, "View") { /* view logic */ }
//                                ActionItem(Icons.Default.Delete, "Delete") { /* delete logic */ }
//                            }
//
////                        }
//                    }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .background(colorResource(id = R.color.white))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ActionItem(Icons.Default.Edit, "Mark as Complete") { /* edit logic */ }
//                        ActionItem(Icons.Default.Edit, "View") { /* view logic */ }
                        ActionItem(Icons.Default.Delete, "Delete") { /* delete logic */ }
                    }
                }


            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .background(colorResource(id = R.color.white))
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.AddToWatchlist.route)
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
                            text = "Add To Watchlist",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }


        }

    ) { paddingValues ->
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
                .background(colorResource(id = R.color.white))
        ) {


            Spacer(modifier = Modifier.height(8.dp)) // space between icon and content


            // Title
            Text(
                text = "My Watchlist",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.dark)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "Do you have Tv shows or Books in your watchlist? add them now",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )



            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF2F4F7) // greyish
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {

//                    itemsIndexed(books) { index, book ->
                    for ((index, book) in books.withIndex()) {

                        val hapticFeedback = LocalHapticFeedback.current
                        val isSelected = selectedWishes.value.contains(book.title)

                        val onClick = {
                            if (selectedWishes.value.isNotEmpty()) {
                                selectedWishes.value = selectedWishes.value.toMutableSet().apply {
                                    if (contains(book.title)) remove(book.title) else add(book.title)
                                }
                            }
                        }

                        val onLongPress = {
                            selectedWishes.value = selectedWishes.value.toMutableSet().apply {
                                if (contains(book.title)) remove(book.title) else add(book.title)
                            }
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        }

                        // Book row
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .combinedClickable(
                                    onClick = onClick,
                                    onLongClick = onLongPress
                                )
//                            .combinedClickable(
//                                onClick = { /* Normal click */ },
//                                onLongClick = { selectedBook = book }
//                                // ✅ show actions
//                            )
                        ) {
                            if (isSelected) {
                                Checkbox(
                                    checked = true,
                                    onCheckedChange = { onLongPress() },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.teal_700),
                                        uncheckedColor = colorResource(id = R.color.darkLight),
                                        checkmarkColor = colorResource(id = R.color.white)
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = book.title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Spacer(Modifier.height(4.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Pages: ${book.pages}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    "Type: ${book.type}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    "Genre: ${book.genre}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(Modifier.height(4.dp))


                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                CircularPercentageBar(
                                    percentage = viewPercentage.coerceIn(0f, 1f),
//            modifier = Modifier
//                .size(48.dp) // adjust size as needed
//                .align(Alignment.TopStart)
                                )
                                IconButton(
                                    onClick = {
                                        selectedNotes = book.title
                                        showSheet = true
                                    },
                                    modifier = Modifier
                                        .size(56.dp) // total button size
                                        .clip(RoundedCornerShape(16.dp)) // round corners
                                ) {
                                    // White Icon on top
                                    Icon(
                                        imageVector = FontAwesomeIcons.Solid.InfoCircle,
                                        contentDescription = "Info",
                                        tint = colorResource(id = R.color.polynesian_blue),
                                        modifier = Modifier
                                            .size(28.dp)
                                    )

                                }
                            }
                        }

                        // Divider except after last item
                        if (index < books.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                thickness = 1.dp,
                                color = Color(0xFFE0E0E0)
                            )
                        }
                    }


//                    // ✅ Action menu appears if long pressed
//                    selectedBook?.let { book ->
//                        Spacer(Modifier.height(16.dp))
//                        Card(
//                            shape = RoundedCornerShape(12.dp),
//                            colors = CardDefaults.cardColors(containerColor = Color.White),
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Column(
//                                Modifier.fillMaxWidth().padding(16.dp),
//                                horizontalAlignment = Alignment.CenterHorizontally
//                            ) {
//                                ActionItem(Icons.Default.Edit, "Edit") { /* edit logic */ }
//                                ActionItem(Icons.Default.Edit, "View") { /* view logic */ }
//                                ActionItem(Icons.Default.Delete, "Delete") { /* delete logic */ }
//                                Spacer(Modifier.height(8.dp))
//                                Button(
//                                    onClick = { selectedBook = null }, // ✅ hide actions
//                                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
//                                    modifier = Modifier.fillMaxWidth()
//                                ) {
//                                    Text("Close Actions", color = Color.White)
//                                }
//                            }
//                        }
//                    }


                }
            }


        }
    }


    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Additional Notes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(selectedNotes)
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDialog = true // Just trigger the flag
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.CircleNotch,
                            contentDescription = "update",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "update Progress", fontSize = 16.sp)
                    }

                    if (showDialog) {
                        UpdateStatusPopup(
                            onDismiss = { showDialog = false },
                            itemId = selectedNotes
                        )


                    }

                    // Edit Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showEditDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Pen,
                            contentDescription = "Edit",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Edit", fontSize = 16.sp)
                    }

                    if (showEditDialog) {
                        EditDetailsPopUp(
                            onDismiss = {  showEditDialog = false },
                            itemId = selectedNotes
                        )
                    }

                        // Delete Button
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Regular.TrashAlt,
                                contentDescription = "Delete",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Delete", fontSize = 16.sp)
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.ShareAlt,
                                contentDescription = "share",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Share Watchlist", fontSize = 16.sp)
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Regular.ThumbsUp,
                                contentDescription = "Mark as complete",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Mark as complete", fontSize = 16.sp)
                        }
                    }
//                Button(
//                    onClick = { showSheet = false },
//                    modifier = Modifier.align(Alignment.End)
//                ) {
//                    Text("Close")
//                }
                }
            }
        }
    }



    // ✅ Local book model + list
    data class Book(
        val title: String,
        val pages: Int,
        val type: String,
        val genre: String
    )


    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview() {
        HomeScreen(navController = rememberNavController())
    }


    @Composable
    fun ActionItem(
        icon: ImageVector,
        text: String,
        onClick: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .clickable { onClick() }
                .size(width = 100.dp, height = 70.dp), // uniform size for consistency
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5) // soft grey background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = text,
                    tint = Color(0xFF1976D2), // blue accent
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = text,
//                style = MaterialTheme.typography.bodyMedium.copy(
//                    fontWeight = FontWeight.SemiBold
//                ),
                    color = Color.Black
                )
            }
        }
    }


//@Composable
//fun ActionItem(icon: ImageVector, text: String, onClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .clickable { onClick() },
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(icon, contentDescription = text, tint = Color.Black, modifier = Modifier.size(20.dp))
//        Spacer(Modifier.width(12.dp))
//        Text(text, style = MaterialTheme.typography.bodyLarge)
//    }
//}

