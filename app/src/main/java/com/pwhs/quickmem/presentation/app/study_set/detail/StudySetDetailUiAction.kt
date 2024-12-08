package com.pwhs.quickmem.presentation.app.study_set.detail

import com.pwhs.quickmem.core.data.enums.LearnMode

sealed class StudySetDetailUiAction {
    data object Refresh : StudySetDetailUiAction()
    data class OnIdOfFlashCardSelectedChanged(val id: String) : StudySetDetailUiAction()
    data object OnDeleteFlashCardClicked : StudySetDetailUiAction()
    data class OnStarFlashCardClicked(val id: String, val isStarred: Boolean) :
        StudySetDetailUiAction()

    data object OnEditStudySetClicked : StudySetDetailUiAction()
    data object OnEditFlashCardClicked : StudySetDetailUiAction()
    data object OnDeleteStudySetClicked : StudySetDetailUiAction()
    data class OnResetProgressClicked(val id: String) : StudySetDetailUiAction()
    data object OnMakeCopyClicked : StudySetDetailUiAction()
    data class NavigateToLearn(val learnMode: LearnMode, val isGetAll: Boolean) :
        StudySetDetailUiAction()
}