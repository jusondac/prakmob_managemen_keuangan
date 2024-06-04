package id.ac.unpas.managemen_keuangan.ui.screens.UserScreens

import androidx.compose.foundation.clickable
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
fun FormUserScreen(modifier: Modifier = Modifier, id : String? = null) {


    val viewModel = hiltViewModel<UserViewModel>()
    val scope = rememberCoroutineScope()

    val name = remember { mutableStateOf(TextFieldValue("")) }
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val password= remember { mutableStateOf(TextFieldValue("")) }


    Column(modifier = modifier
        .fillMaxWidth()) {

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {

            OutlinedTextField(
                label = { Text(text = "Name") },
                modifier = Modifier
                    .fillMaxWidth(),
                value = name.value, onValueChange = {
                    name.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth(),
                value = email.value, onValueChange = {
                    email.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth(),
                value = password.value, onValueChange = {
                    password.value = it
                })


            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, name.value.text, email.value.text, password.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), name.value.text, email.value.text, password.value.text)
                        }
                    }
                }) {
                    Text(text = "Simpan")
                }

                Button(modifier = Modifier.weight(5f), onClick = {
                    name.value = TextFieldValue("")
                    email.value = TextFieldValue("")
                    password.value = TextFieldValue("")
                }) {
                    Text(text = "Batal")
                }
            }
        }


        viewModel.isDone.observe(LocalLifecycleOwner.current) {
            if (it) {
                name.value = TextFieldValue("")
                email.value = TextFieldValue("")
                password.value = TextFieldValue("")
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
            email.value = TextFieldValue(it.email)
            password.value = TextFieldValue(it.password)
        }
    }

}