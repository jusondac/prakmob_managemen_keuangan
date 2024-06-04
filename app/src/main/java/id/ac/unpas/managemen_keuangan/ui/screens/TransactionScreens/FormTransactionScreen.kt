package id.ac.unpas.managemen_keuangan.ui.screens.TransactionScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.benasher44.uuid.uuid4
import id.ac.unpas.managemen_keuangan.models.Category
import id.ac.unpas.managemen_keuangan.models.User
import id.ac.unpas.managemen_keuangan.ui.screens.category.CategoryViewModel
import id.ac.unpas.managemen_keuangan.ui.screens.UserScreens.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun FormTransactionScreen(modifier: Modifier = Modifier, id : String? = null) {

    var expanded by remember { mutableStateOf(false) }
    val CategoryviewModel = hiltViewModel<CategoryViewModel>()
    var selectedCategory by remember { mutableStateOf("")}

    var expanded_user by remember { mutableStateOf(false) }
    val UserviewModel = hiltViewModel<UserViewModel>()
    var selectedUser by remember { mutableStateOf("")}

    var textFiledSize by remember { mutableStateOf(Size.Zero) }

    val category_icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    val user_icon = if(expanded_user){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }


    val list: List<Category> by CategoryviewModel.categories.observeAsState(listOf())
    val user: List<User> by UserviewModel.users.observeAsState(listOf())
//    val list = listOf("rizki", "haikal")

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
                modifier = Modifier
                    .fillMaxWidth(),
                value = name.value, onValueChange = {
                    name.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Category") },
                modifier = Modifier.fillMaxWidth().onGloballyPositioned {coordinates ->
                    textFiledSize = coordinates.size.toSize()
                },
                value = selectedCategory, onValueChange = { selectedCategory = it },
                trailingIcon = {
                    Icon(category_icon,"", Modifier.clickable { expanded = !expanded })
                }
            )
            
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false},
                modifier = Modifier
                    .width(with(LocalDensity.current){textFiledSize.width.toDp()})
            ) {
                list.forEach{category ->
                    DropdownMenuItem(
                        text = {Text(category.name)},
                        onClick = {
                            selectedCategory = category.name
                            expanded = false
                        })
                }
            }

            OutlinedTextField(
                label = { Text(text = "User") },
                modifier = Modifier.fillMaxWidth().onGloballyPositioned {coordinates ->
                    textFiledSize = coordinates.size.toSize()
                },
                value = selectedUser, onValueChange = { selectedUser = it },
                trailingIcon = {
                    Icon(user_icon,"", Modifier.clickable { expanded_user = !expanded_user })
                }
            )

            DropdownMenu(
                expanded = expanded_user,
                onDismissRequest = { expanded_user = false},
                modifier = Modifier
                    .width(with(LocalDensity.current){textFiledSize.width.toDp()})
            ) {
                user.forEach{user ->
                    DropdownMenuItem(
                        text = {Text(user.name)},
                        onClick = {
                            selectedUser= user.name
                            expanded_user = false
                        })
                }
            }

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
                            viewModel.update(id, name.value.text, selectedCategory,user_id.value.text, date.value.text, amount.value.text, description.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), name.value.text, selectedCategory,user_id.value.text, date.value.text.toLong(), amount.value.text.toDouble(), description.value.text)
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

