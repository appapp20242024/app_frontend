package com.pwhs.quickmem.presentation.app.flashcard.edit

import android.net.Uri
import com.pwhs.quickmem.domain.model.flashcard.EditFlashCardModel

data class EditFlashCardUiState(
    val flashcardId: String = "",
    val term: String = "",
    val definition: String = "",
    val definitionImageUri: Uri? = null,
    val definitionImageURL: String? = null,
    val hint: String? = null,
    val showHint: Boolean = false,
    val explanation: String? = null,
    val showExplanation: Boolean = false,
    val isLoading: Boolean = false,
)

fun EditFlashCardUiState.toEditFlashCardModel(): EditFlashCardModel {
    return EditFlashCardModel(
        term = term.trim(),
        definition = definition.trim(),
        definitionImageURL = definitionImageURL,
        hint = hint ?: "",
        explanation = explanation ?: "",
    )
}
