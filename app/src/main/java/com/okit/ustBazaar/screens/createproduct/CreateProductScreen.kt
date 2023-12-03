package com.okit.ustBazaar.screens.createproduct

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.okit.ustBazaar.ui.theme.Dimension
import com.okit.ustBazaar.R
import com.okit.ustBazaar.components.SecondaryTopBar
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TextButton
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.okit.ustBazaar.room_models.Product

@Composable
fun CreateProductScreen(
    createProductViewModel: CreateProductViewModel = hiltViewModel()
) {
    //    val uiState by remember { createProductViewModel.uiState }
    var productName by remember { mutableStateOf("") }
    var image by remember { mutableStateOf<Uri?>(null) }
    var productPrice by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }

    val categories = arrayListOf("Apparels", "Dorm", "Free Items","Textbooks")

    val openFilePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Update the selectedImageUri variable with the selected image URI
        image = uri
    }

    fun openFilePicker() {
        openFilePickerLauncher.launch("image/*")
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(horizontal = Dimension.pagePadding),
            verticalArrangement = Arrangement.spacedBy(Dimension.pagePadding)

        ) {
            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.create_product),
                        style = MaterialTheme.typography.h3,
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(Dimension.sm))
            }

            item {
                Text(
                    text = stringResource(id = R.string.product_name),
                    style = MaterialTheme.typography.h6
                )
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.product_description),
                    style = MaterialTheme.typography.h6
                )
                OutlinedTextField(
                    value = productDescription,
                    onValueChange = { productDescription = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.product_price),
                    style = MaterialTheme.typography.h6
                )
                OutlinedTextField(
                    value = productPrice,
                    onValueChange = { productPrice = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                CategorySelection(
                    categories = categories,
                    selectedCategory = productCategory,
                    onCategorySelected = { productCategory = it })
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(color = MaterialTheme.colors.surface)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .clickable {
                            openFilePicker()
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = stringResource(id = R.string.upload_images),
                            tint = MaterialTheme.colors.primary,
                        )
                        Text(
                            text = stringResource(id = R.string.upload_images),
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(Dimension.sm))
            }
            if (image != null) {
                item {
                    Text("Image preview")
                    Image(
                        painter = rememberAsyncImagePainter(model = image),
                        contentDescription = stringResource(id = R.string.product_image_preview),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                                shape = MaterialTheme.shapes.medium
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(Dimension.sm))
            }
            item {
                Button(
                    onClick = {
                        createProductViewModel.createProdcut(
                            Product(
                                name = productName,
                                description = productDescription,
                                price = productPrice.toDouble(),
                                categoryId = categories.indexOf(productCategory) + 1,
                                image = R.drawable.product_place_holder,
                                basicColorName = "black"
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(text = stringResource(id = R.string.create_product))
                }
            }
        }

    }

}

@Composable
fun CategorySelection(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column() {
        Text("Select a category")
        Box(modifier = Modifier.wrapContentSize()) {
            TextButton(
                onClick = { expanded = true }
            ) {
                Text(text = selectedCategory)
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Arrow",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        onClick = {
                            onCategorySelected(category)
                            expanded = false
                        }
                    ) {
                        Text(text = category)
                    }
                }
            }
        }
    }

}