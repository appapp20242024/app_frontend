package com.pwhs.quickmem.data.dto.flashcard

import com.google.gson.annotations.SerializedName

data class WriteStatusFlashCardDto(
    @SerializedName("writeStatus")
    val writeStatus: String,
)