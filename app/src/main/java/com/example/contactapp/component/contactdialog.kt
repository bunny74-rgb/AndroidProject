package com.example.contactapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.contactapp.model.Contact

@Composable
fun ContactDialog(
    contact: Contact,
    onDismiss: () -> Unit,
    onSave: (name: String, phone: String) -> Unit
) {
    var editName by remember(contact.id) {
        mutableStateOf(contact.name)
    }
    var editPhone by remember(contact.id) {
        mutableStateOf(contact.phone)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Chỉnh Sửa Liên Hệ", fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = editName,
                    onValueChange = { editName = it },
                    label = { Text("Tên liên hệ") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = editPhone,
                    onValueChange = { editPhone = it },
                    label = { Text("Số điện thoại") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(editName.trim(), editPhone.trim())
                },
                enabled = editName.isNotBlank() && editPhone.isNotBlank()
            ) {
                Text("Lưu")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy")
            }
        }


    )
}