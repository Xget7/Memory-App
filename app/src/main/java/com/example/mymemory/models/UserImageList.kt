package com.example.mymemory.models

import com.google.firebase.database.PropertyName

data class UserImageList (
        @PropertyName("images") val images: List<String>? = null
)
