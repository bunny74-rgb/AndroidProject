package com.example.contactapp.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.contactapp.model.Contact

@Composable
fun ContactItem (contact: Contact, onContactClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable{onContactClick ()},
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = contact.name,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,

        )
        Text(
            text = contact.phone,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}