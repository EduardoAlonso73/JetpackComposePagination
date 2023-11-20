package com.mrntlu.pagination.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrntlu.pagination.utils.ListState
import com.mrntlu.pagination.viewmodels.AssociationManuelPagingViewModel
import kotlinx.coroutines.launch

@Composable
fun ManuelPagingListScreen() {
    val viewModel = hiltViewModel<AssociationManuelPagingViewModel>()
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val shouldStartPaginate: State<Boolean> = remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -5) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    val articles = viewModel.newsList

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && viewModel.listState == ListState.IDLE)
            viewModel.getAssociation()
    }

    LazyColumn(state = lazyColumnListState) {
        items(
            items = articles
        ) {  article ->
            ContentCard {
                ItemDescription(label = "Ein", description = article.ein.toString())
                ItemDescription(label = "Strein", description = article.strein.toString())
                ItemDescription(label = "Name", description = article.name.toString())
                ItemDescription(label = "subName", description = article.subName.toString())
                ItemDescription(label = "City", description = article.city.toString())
                ItemDescription(label = "Ntee Code", description = article.nteeCode.toString())
                ItemDescription(
                    label = "RawNtee Code",
                    description = article.rawNteeCode.toString()
                )
                ItemDescription(label = "Subseccd", description = article.subseccd.toString())
                ItemDescription(label = "Score", description = article.score.toString())
            }

        }

        item(
            key = viewModel.listState,
        ) {
            when (viewModel.listState) {
                ListState.LOADING -> CircularProgress("Refresh Loading")
                ListState.PAGINATING -> CircularProgress("Pagination Loading")
                ListState.PAGINATION_EXHAUST -> {
                    PaginationExhaust() {
                        coroutineScope.launch {
                            lazyColumnListState.animateScrollToItem(0)
                        }
                    }

                }

                else -> {}
            }
        }
    }
}

@Composable
private fun ContentCard(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(
                color = Color(0x97E6E6E6),
                shape = RoundedCornerShape(size = 12.dp)
            )
    ) {
        content()
    }

}

@Composable
private fun ItemDescription(label: String, description: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = description, maxLines = 1, overflow = TextOverflow.Ellipsis)

    }

}

@Composable
private fun PaginationExhaust(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(imageVector = Icons.Rounded.Face, contentDescription = "")

        Text(text = "Nothing left.")

        TextButton(
            modifier = Modifier
                .padding(top = 8.dp),
            elevation = ButtonDefaults.elevation(0.dp),
            onClick = onClick,
            content = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = ""
                    )

                    Text(text = "Back to Top")

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
            }
        )
    }
}

@Composable
private fun CircularProgress(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title)
        CircularProgressIndicator(color = Color.Black)
    }
}
