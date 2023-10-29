package com.example.laredoutebetatest.data.model

data class ReviewFormResponse(
    val backgroundUrl: String?,
    val imageUrl: String?,
    val name: String?,
    val fields: List<ReviewField>?
) {
    data class ReviewField(
        val id: String?,
        val type: String?,
        val title: String?,
        val placeholder: String?,
        val options: List<NameValue>?,
        val buttonText: String?
    )

    data class NameValue(
        val name: String?,
        val value: String?
    )
}



