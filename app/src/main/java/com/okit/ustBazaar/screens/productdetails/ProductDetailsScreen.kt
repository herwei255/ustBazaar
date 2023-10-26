package com.okit.ustBazaar.screens.productdetails

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.okit.ustBazaar.components.CustomButton
import com.okit.ustBazaar.components.DrawableButton
import com.okit.ustBazaar.components.ReactiveBookmarkIcon
import com.okit.ustBazaar.sealed.Orientation
import com.okit.ustBazaar.ui.theme.Dimension
import com.okit.ustBazaar.utils.addMoveAnimation
import com.okit.ustBazaar.utils.getValidColor

@Composable
fun ProductDetailsScreen(
    productId: Int,
    cartItemsCount: Int,
    isOnCartStateProvider: () -> Boolean,
    isOnBookmarksStateProvider: () -> Boolean,
    onUpdateCartState: (productId: Int) -> Unit,
    onUpdateBookmarksState: (productId: Int) -> Unit,
    onBackRequested: () -> Unit,
    productDetailsViewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        productDetailsViewModel.getProductDetails(productId = productId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(Dimension.pagePadding)
            .verticalScroll(
                rememberScrollState()),
                        horizontalAlignment = Alignment . CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimension.pagePadding),
            ) {
                val product by remember { productDetailsViewModel.product }
                val color by remember { productDetailsViewModel.selectedColor }
                val size by remember { productDetailsViewModel.selectedSize }
                val scale by remember { productDetailsViewModel.sizeScale }
                val animatedScale by animateFloatAsState(
                    targetValue = scale,
                    animationSpec = TweenSpec(
                        durationMillis = 500,
                        easing = LinearEasing,
                    )
                )

                /** Details screen header */
                DetailsHeader(
                    modifier = Modifier
                        .addMoveAnimation(
                            orientation = Orientation.Vertical,
                            from = (-100).dp,
                            to = 0.dp,
                            duration = 700,
                        ),
                    cartItemsCount = cartItemsCount,
                    onBackRequested = onBackRequested,
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Dimension.pagePadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    product?.let {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimension.pagePadding)
                                .weight(1f),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center)
//                            .offset(x = (-20).dp)
                                , horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Image(
                                //     painter = rememberAsyncImagePainter(model = it.colors?.find { productColor ->
                                //         productColor.colorName == color
                                //     }?.image ?: it.image
                                //     ),
                                //     contentDescription = null,
                                //     contentScale = ContentScale.Fit
                                // )
//                        Image(
//                            painter = rememberAsyncImagePainter(data = "https://i.pinimg.com/1200x/85/7b/e2/857be27895892ba6f90690145d68e7ee.jpg"),
//                            contentDescription = null,
//                            contentScale = ContentScale.Fit
//                        )
                                AsyncImage(
                                    model = "https://i.pinimg.com/1200x/85/7b/e2/857be27895892ba6f90690145d68e7ee.jpg",
                                    contentDescription = "Translated description of what the image contains",
                                    contentScale = ContentScale.Crop
                                )
                                Canvas(modifier = Modifier.fillMaxWidth()) {

                                }
                            }
                            /** Price section */
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .addMoveAnimation(
                                        orientation = Orientation.Vertical,
                                        from = 200.dp,
                                        to = 0.dp,
                                        duration = 700,
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "HK$${it.price}",
                                    style = MaterialTheme.typography.h4,
                                )
                                Spacer(modifier = Modifier.width(Dimension.pagePadding))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Total likes"
                                    )
                                    Text(
//                                text = "${it.totalLikes} likes",
                                        text = "6 likes",
                                        style = MaterialTheme.typography.h4,
                                    )
                                }
                            }
                            /** Bookmarking button */
                            ReactiveBookmarkIcon(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .addMoveAnimation(
                                        orientation = Orientation.Horizontal,
                                        from = 60.dp,
                                        to = 0.dp,
                                        duration = 700,
                                    ),
                                iconSize = Dimension.smIcon,
                                isOnBookmarks = isOnBookmarksStateProvider(),
                                onBookmarkChange = { onUpdateBookmarksState(productId) }
                            )
                            /** colors section */
//                    it.colors?.let { colors ->
//                        ColorsSection(
//                            modifier = Modifier
//                                .align(Alignment.BottomEnd)
//                                .addMoveAnimation(
//                                    orientation = Orientation.Vertical,
//                                    from = 200.dp,
//                                    to = 0.dp,
//                                    duration = 700,
//                                ),
//                            colors = colors.map { color -> color.colorName },
//                            pickedColorProvider = { color },
//                            onColorPicked = productDetailsViewModel::updateSelectedColor,
//                        )
//                    }
                        }
                        /** Posted date and posted by user */
                        Text(
                            modifier = Modifier
                                .padding(horizontal = Dimension.pagePadding)
                                .addMoveAnimation(
                                    orientation = Orientation.Vertical,
                                    from = 100.dp,
                                    to = 0.dp,
                                    duration = 700,
                                ),
                            text = "1 day ago by John Doe",
                            style = MaterialTheme.typography.subtitle2,
                            textAlign = TextAlign.Center,
                        )
                        /** Condition text */
                        Text(
                            modifier = Modifier
                                .padding(horizontal = Dimension.pagePadding)
                                .addMoveAnimation(
                                    orientation = Orientation.Vertical,
                                    from = 100.dp,
                                    to = 0.dp,
                                    duration = 700,
                                ),
                            text = "Lightly used",
                            style = MaterialTheme.typography.subtitle2,
                            textAlign = TextAlign.Center,
                        )
                        /** Product's name */
                        Text(
                            modifier = Modifier
                                .padding(horizontal = Dimension.pagePadding)
                                .addMoveAnimation(
                                    orientation = Orientation.Vertical,
                                    from = 100.dp,
                                    to = 0.dp,
                                    duration = 700,
                                ),
                            text = it.name,
                            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Black),
                            textAlign = TextAlign.Center,
                        )
                        /** Product's description */
                        Text(
                            modifier = Modifier
                                .padding(horizontal = Dimension.pagePadding)
                                .addMoveAnimation(
                                    orientation = Orientation.Vertical,
                                    from = 100.dp,
                                    to = 0.dp,
                                    duration = 700,
                                ),
                            text = it.description,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                        )
                        /** Add / Remove from cart button */
                        CustomButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .addMoveAnimation(
                                    orientation = Orientation.Vertical,
                                    from = -(40.dp),
                                    to = 0.dp,
                                    duration = 700,
                                ),
                            text = if (isOnCartStateProvider()) "Remove from cart" else "Add to cart",
                            onButtonClicked = { onUpdateCartState(productId) },
                            buttonColor = MaterialTheme.colors.primary,
                            contentColor = MaterialTheme.colors.onPrimary,
                            shape = RoundedCornerShape(percent = 50),
                            textStyle = MaterialTheme.typography.button,
                        )
                    }
                }
            }
}

@Composable
fun DetailsHeader(
    modifier: Modifier = Modifier,
    cartItemsCount: Int,
    onBackRequested: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        DrawableButton(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            iconTint = MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background,
            onButtonClicked = onBackRequested,
            shape = MaterialTheme.shapes.medium,
            elevation = Dimension.elevation,
            iconSize = Dimension.smIcon,
            paddingValue = PaddingValues(Dimension.sm),
        )
        Box {
            DrawableButton(
                painter = painterResource(id = R.drawable.ic_shopping_bag),
                iconTint = MaterialTheme.colors.onBackground,
                backgroundColor = MaterialTheme.colors.background,
                onButtonClicked = {
//                    onNavigateToCartRequested()
                },
                shape = MaterialTheme.shapes.medium,
                elevation = Dimension.elevation,
                iconSize = Dimension.smIcon,
                paddingValue = PaddingValues(Dimension.sm),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(Dimension.smIcon.times(0.85f))
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.onBackground),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "$cartItemsCount",
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.background,
                )
            }
        }
    }
}

@Composable
fun SizesSection(
    modifier: Modifier,
    sizes: List<Int>,
    pickedSizeProvider: () -> Int,
    onSizePicked: (size: Int) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement
            .spacedBy(Dimension.pagePadding.div(2)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.size),
            style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
        )
        sizes.forEach { size ->
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = Dimension.elevation,
                        shape = MaterialTheme.shapes.small,
                    )
                    .clip(MaterialTheme.shapes.small)
                    .background(
                        if (pickedSizeProvider() == size) MaterialTheme.colors.primary
                        else MaterialTheme.colors.background,
                    )
                    .clickable { onSizePicked(size) }
                    .padding(Dimension.sm)
            ) {
                Text(
                    text = "$size",
                    style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
                    color = if (pickedSizeProvider() == size) MaterialTheme.colors.onPrimary
                    else MaterialTheme.colors.onBackground,
                )
            }
        }
    }
}

@Composable
fun ColorsSection(
    modifier: Modifier,
    colors: List<String>,
    pickedColorProvider: () -> String,
    onColorPicked: (name: String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement
            .spacedBy(Dimension.pagePadding.div(2)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(Dimension.smIcon)
                    .border(
                        width = 2.dp,
                        color = if (pickedColorProvider() == color) MaterialTheme.colors.primary else Color.Transparent,
                        shape = MaterialTheme.shapes.small,
                    )
                    .padding(Dimension.elevation)
                    .clip(MaterialTheme.shapes.small)
                    .background(color = Color(color.getValidColor()))
                    .clickable { onColorPicked(color) }
            )
        }
        Text(
            text = stringResource(R.string.color),
            style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
        )
    }
}
