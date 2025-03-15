package com.santimattius.kmp.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.santimattius.kmp.skeleton.shared.domain.Character
import com.santimattius.kmp.skeleton.shared.ui.CharacterUiState
import com.santimattius.kmp.skeleton.shared.ui.CharacterViewModel
import com.santimattius.kmp.skeleton.ui.theme.KMPGradleSkeletonTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setSingletonImageLoaderFactory { context ->
                ImageLoader.Builder(context)
                    .components {
                        add(OkHttpNetworkFetcherFactory())
                    }
                    .build()
            }
            KMPGradleSkeletonTheme {
                CharacterScreen()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterScreen(
    viewModel: CharacterViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors =  TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor =  MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor =  MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor =  MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            val state by viewModel.state.collectAsStateWithLifecycle()
            when (state) {
                is CharacterUiState.Error -> {
                    Text(text = (state as CharacterUiState.Error).message)
                }

                CharacterUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is CharacterUiState.Success -> {
                    val characters = (state as CharacterUiState.Success).characters
                    CharacterList(characters)
                }
            }
        }
    }
}

@Composable
fun CharacterList(characters: List<Character>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = characters, key = { item -> item.id }) { item ->
            CharacterListItem(item = item)
        }
    }
}