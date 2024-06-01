package id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch

@Composable
fun FormTransactionScreen(modifier: Modifier = Modifier, id : String? = null) {

    val viewModel = hiltViewModel<TransactionViewModel>()
    val scope = rememberCoroutineScope()
    val name = remember { mutableStateOf(TextFieldValue("")) }
    val category_id = remember { mutableStateOf(TextFieldValue("")) }
    val user_id = remember { mutableStateOf(TextFieldValue("")) }
    val date = remember { mutableStateOf(TextFieldValue("")) }
    val amount = remember { mutableStateOf(TextFieldValue("")) }
    val description = remember { mutableStateOf(TextFieldValue("")) }


    Column(modifier = modifier
        .fillMaxWidth()) {

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            OutlinedTextField(
                label = { Text(text = "Name") },
                modifier = Modifier.fillMaxWidth(),
                value = name.value, onValueChange = {
                    name.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Category") },
                modifier = Modifier.fillMaxWidth(),
                value = category_id.value, onValueChange = {
                    category_id.value = it
                })

            OutlinedTextField(
                label = { Text(text = "User") },
                modifier = Modifier.fillMaxWidth(),
                value = user_id.value, onValueChange = {
                    user_id.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Date") },
                modifier = Modifier.fillMaxWidth(),
                value = date.value, onValueChange = {
                    date.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Amount") },
                modifier = Modifier.fillMaxWidth(),
                value = amount.value, onValueChange = {
                    amount.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                value = description.value, onValueChange = {
                    description.value = it
                })



            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, name.value.text, category_id.value.text,user_id.value.text, date.value.text, amount.value.text, description.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), name.value.text, category_id.value.text,user_id.value.text, date.value.text, amount.value.text, description.value.text)
                        }
                    }
                }) {
                    Text(text = "Simpan")
                }

                Button(modifier = Modifier.weight(5f), onClick = {
                    name.value = TextFieldValue("")
                    category_id.value = TextFieldValue("")
                    user_id.value = TextFieldValue("")
                    date.value = TextFieldValue("")
                    amount.value = TextFieldValue("")
                    description.value = TextFieldValue("")
                }) {
                    Text(text = "Batal")
                }
            }
        }


        viewModel.isDone.observe(LocalLifecycleOwner.current) {
            if (it) {
                name.value = TextFieldValue("")
                category_id.value = TextFieldValue("")
                user_id.value = TextFieldValue("")
                date.value = TextFieldValue("")
                amount.value = TextFieldValue("")
                description.value = TextFieldValue("")
            }
        }

        LaunchedEffect(id) {
            if (id != null) {
                scope.launch {
                    viewModel.find(id)
                }
            }
        }

        viewModel.item.observe(LocalLifecycleOwner.current) {
            name.value = TextFieldValue(it.name)
            category_id.value = TextFieldValue(it.category_id)
            user_id.value = TextFieldValue(it.user_id)
            date.value = TextFieldValue(it.date.toString())
            amount.value = TextFieldValue(it.amount.toString())
            description.value = TextFieldValue(it.description)
        }
    }

}