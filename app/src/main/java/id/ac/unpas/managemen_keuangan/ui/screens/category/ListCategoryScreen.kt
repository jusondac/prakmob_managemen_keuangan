package id.ac.unpas.managemen_keuangan.ui.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import id.ac.unpas.agenda.models.Manajemen
import id.ac.unpas.managemen_keuangan.screens.composables.ConfirmationDialog
import kotlinx.coroutines.launch

@Composable
fun ListCategoryScreen(modifier: Modifier = Modifier, onDelete: () -> Unit, onClick: (String) -> Unit) {

    val scope = rememberCoroutineScope()
    val viewModel = hiltViewModel<TodoViewModel>()

    val list: List<Manajemen> by viewModel.todos.observeAsState(listOf())
    val name = remember { mutableStateOf("TODO") }
    val openDialog = remember {
        mutableStateOf(false)
    }
    val activeId = remember {
        mutableStateOf("")
    }
    val deleting = remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = name.value, modifier = Modifier.fillMaxWidth())
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(list.size) { index ->
                val item = list[index]
                CategoryItem(item = item, onEditClick = { id ->
                    onClick(id)
                }, onDeleteClick = { id ->
                    deleting.value = true
                    activeId.value = id
                    openDialog.value = true
                })
            }
        }
    }

    if (openDialog.value) {
        ConfirmationDialog(onDismiss = {
            openDialog.value = false
        }) {
            scope.launch {
                viewModel.delete(activeId.value)
            }
            openDialog.value = false
        }
    }

    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        if (it) {
            name.value = "Loading..."
        } else {
            name.value = "TODO"
        }
    }

    viewModel.isDeleted.observe(LocalLifecycleOwner.current) {
        if (deleting.value && it) {
            deleting.value = false
            onDelete()
        }
    }
}