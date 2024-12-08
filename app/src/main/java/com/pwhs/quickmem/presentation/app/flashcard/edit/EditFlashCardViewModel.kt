package com.pwhs.quickmem.presentation.app.flashcard.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pwhs.quickmem.core.datastore.TokenManager
import com.pwhs.quickmem.core.utils.Resources
import com.pwhs.quickmem.domain.repository.FlashCardRepository
import com.pwhs.quickmem.domain.repository.UploadImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditFlashCardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val flashCardRepository: FlashCardRepository,
    private val uploadImageRepository: UploadImageRepository,
    private val tokenManager: TokenManager,
    application: Application
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(EditFlashCardUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<EditFlashCardUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val flashcardId: String = savedStateHandle["flashcardId"] ?: ""
        val term: String = savedStateHandle["term"] ?: ""
        val definition: String = savedStateHandle["definition"] ?: ""
        val definitionImageUrl: String = savedStateHandle["definitionImageUrl"] ?: ""
        val hint: String = savedStateHandle["hint"] ?: ""
        val explanation: String = savedStateHandle["explanation"] ?: ""
        _uiState.update {
            it.copy(
                flashcardId = flashcardId,
                term = term,
                definition = definition,
                definitionImageURL = definitionImageUrl,
                hint = hint,
                explanation = explanation
            )
        }
    }

    fun onEvent(event: EditFlashCardUiAction) {
        when (event) {
            is EditFlashCardUiAction.FlashCardDefinitionChanged -> {
                _uiState.update { it.copy(definition = event.definition) }
            }

            is EditFlashCardUiAction.FlashCardDefinitionImageChanged -> {
                _uiState.update { it.copy(definitionImageUri = event.definitionImageUri) }
            }

            is EditFlashCardUiAction.FlashCardExplanationChanged -> {
                Timber.d("Explanation: ${event.explanation}")
                _uiState.update { it.copy(explanation = event.explanation) }
            }

            is EditFlashCardUiAction.FlashCardHintChanged -> {
                Timber.d("Hint: ${event.hint}")
                _uiState.update { it.copy(hint = event.hint) }
            }

            is EditFlashCardUiAction.FlashCardTermChanged -> {
                _uiState.update { it.copy(term = event.term) }
            }

            is EditFlashCardUiAction.SaveFlashCard -> {
                saveFlashCard()
            }

            is EditFlashCardUiAction.FlashcardIdChanged -> {
                _uiState.update { it.copy(flashcardId = event.flashcardId) }
            }

            is EditFlashCardUiAction.ShowExplanationClicked -> {
                _uiState.update { it.copy(showExplanation = event.showExplanation) }
            }

            is EditFlashCardUiAction.ShowHintClicked -> {
                _uiState.update {
                    it.copy(showHint = event.showHint)
                }
            }

            is EditFlashCardUiAction.UploadImage -> {

                viewModelScope.launch {
                    val token = tokenManager.accessToken.firstOrNull() ?: ""
                    uploadImageRepository.uploadImage(token, event.imageUri)
                        .collect { resource ->
                            when (resource) {
                                is Resources.Success -> {
                                    Timber.d("Success: ${resource.data}")
                                    _uiState.update {
                                        it.copy(
                                            definitionImageURL = resource.data!!.url,
                                            isLoading = false
                                        )
                                    }
                                }

                                is Resources.Error -> {
                                    Timber.e("Error: ${resource.message}")
                                    _uiState.update { it.copy(isLoading = false) }
                                }

                                is Resources.Loading -> {
                                    _uiState.update {
                                        it.copy(isLoading = true)
                                    }
                                }
                            }
                        }
                }
            }

            is EditFlashCardUiAction.RemoveImage -> {
                viewModelScope.launch {
                    val token = tokenManager.accessToken.firstOrNull() ?: ""
                    uploadImageRepository.removeImage(token, event.imageURL)
                        .collect { resource ->
                            when (resource) {
                                is Resources.Success -> {
                                    Timber.d("Success: ${resource.data}")
                                    _uiState.update {
                                        it.copy(
                                            definitionImageURL = "",
                                            definitionImageUri = null,
                                            isLoading = false
                                        )
                                    }
                                }

                                is Resources.Error -> {
                                    Timber.e("Error: ${resource.message}")
                                    _uiState.update { it.copy(isLoading = false) }
                                }

                                is Resources.Loading -> {
                                    _uiState.update {
                                        it.copy(isLoading = true)
                                    }
                                }
                            }
                        }
                }
            }
        }
    }

    private fun saveFlashCard() {
        Timber.d("Saving flashcard: url: ${_uiState.value.definitionImageURL}")
        viewModelScope.launch {
            val token = tokenManager.accessToken.firstOrNull() ?: ""
            val editFlashCardModel = _uiState.value.toEditFlashCardModel()
            Timber.d("EditFlashCardModel: $editFlashCardModel")
            flashCardRepository.updateFlashCard(
                token,
                _uiState.value.flashcardId,
                editFlashCardModel
            ).collect { resource ->
                when (resource) {
                    is Resources.Error -> {
                        Timber.e("Error: ${resource.message}")
                        _uiState.update { it.copy(isLoading = false) }
                    }

                    is Resources.Loading -> {
                        Timber.d("Loading")
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resources.Success -> {
                        Timber.d("FlashCard saved: ${resource.data}")
                        _uiState.update {
                            it.copy(
                                term = "",
                                definition = "",
                                definitionImageURL = null,
                                definitionImageUri = null,
                                hint = null,
                                explanation = null,
                                isLoading = false
                            )
                        }
                        _uiEvent.send(EditFlashCardUiEvent.FlashCardSaved)
                    }
                }
            }
        }
    }
}
