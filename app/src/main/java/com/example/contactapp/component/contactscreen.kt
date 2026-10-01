package com.example.contactapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactapp.model.Contact
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.consumeWindowInsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen() {
    val contacts = remember {
        mutableStateListOf(
            Contact("user1", name = "Nguyen Van A", phone = "0123456789"),
            Contact("user2",name = "Le Thi B", phone = "0987654321"),
            Contact("user3", name = "Tran Van C", phone = "0121987654"),
            Contact("user4", name = "Loan", phone = "323")
        )
    }

    var searchQuery by remember { mutableStateOf("") }
    var idInput by remember { mutableStateOf("") }
    var nameInput by remember { mutableStateOf("") }
    var phoneInput by remember { mutableStateOf("") }
    var editingContact by remember { mutableStateOf<Contact?>(null) }

    val filteredContacts = contacts.filter { contact ->
        contact.name.contains(searchQuery, ignoreCase = true) ||
                contact.phone.contains(searchQuery)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Danh Bạ Điện Thoại",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding()
                .padding(horizontal = 24.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Tìm kiếm tên hoặc SĐT...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(filteredContacts, key = { it.id }) { contact ->
                    ContactItem(
                        contact = contact,
                        onContactClick = { editingContact = contact }
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                TextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    placeholder = { Text("Tên liên hệ") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    singleLine = true
                )

                TextField(
                    value = phoneInput,
                    onValueChange = { phoneInput = it },
                    placeholder = { Text("Số điện thoại") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    singleLine = true
                )

                Button(
                    onClick = {
                        if (nameInput.isNotBlank() && phoneInput.isNotBlank()) {
                            contacts.add(
                                Contact(
                                    id = idInput.trim() ,
                                    name = nameInput.trim(),
                                    phone = phoneInput.trim()
                                )
                            )
                            nameInput = ""
                            phoneInput = ""
                        }
                    },
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Thêm liên hệ")
                }
            }
        }
    }

    editingContact?.let { contact ->
        ContactDialog(
            contact = contact,
            onDismiss = { editingContact = null },
            onSave = { newName, newPhone ->
                val index = contacts.indexOfFirst { it.id == contact.id }

                if (index != -1) {
                    contacts[index] = contact.copy(
                        name = newName,
                        phone = newPhone
                    )
                }

                editingContact = null
            }
        )
    }
}