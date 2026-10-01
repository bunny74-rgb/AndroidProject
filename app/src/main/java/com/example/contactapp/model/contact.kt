package com.example.contactapp.model
import java.util.UUID
data class Contact (
    val id: String = UUID.randomUUID().toString(),
    val phone : String,
    val name : String

    )