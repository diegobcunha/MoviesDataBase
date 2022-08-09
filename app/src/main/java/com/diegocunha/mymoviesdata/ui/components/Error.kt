package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.diegocunha.mymoviesdata.R

@Composable
fun ErrorRow(modifier: Modifier = Modifier, title: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_reject),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Text(title)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyGridItemScope.renderError(
    modifier: Modifier = Modifier,
    loadState: CombinedLoadStates
) {
    val message = (loadState.append as? LoadState.Error)?.error?.message ?: return

    ErrorRow(title = message, modifier = modifier)
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorRowPreview() {
    ErrorRow(title = "Oopsie!")
}