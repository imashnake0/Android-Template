package com.imashnake.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.imashnake.template.data.Resource
import com.imashnake.template.ui.hotels.HotelsViewModel
import com.imashnake.template.ui.theme.AndroidTemplateTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTemplateTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: HotelsViewModel = koinViewModel()
) {
    val hotels by viewModel.hotels.collectAsStateWithLifecycle()

    Box(modifier.fillMaxSize().statusBarsPadding()) {
        when (hotels) {
            is Resource.Success -> {
                hotels.data?.let { hotels ->
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Text(hotels.location)

                        HorizontalDivider()

                        Text("Date: ${viewModel.date}")

                        Spacer(Modifier.size(10.dp))

                        Column {
                            hotels.hotels.forEach {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(it.hotel)
                                    Text(it.pricePerNight)
                                }
                            }
                        }
                    }
                }
            }

            is Resource.Loading -> {
                Text("Loading...")
            }

            is Resource.Error -> {
                Text("Something went wrong! ${hotels.message}")
            }
        }
    }
}
