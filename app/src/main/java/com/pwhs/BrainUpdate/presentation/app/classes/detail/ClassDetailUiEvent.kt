package com.pwhs.quickmem.presentation.app.classes.detail

sealed class ClassDetailUiEvent {
    data object OnJoinClass : ClassDetailUiEvent()
    data object NavigateToWelcome : ClassDetailUiEvent()
    data object OnNavigateToAddFolder : ClassDetailUiEvent()
    data object OnNavigateToRemoveMembers : ClassDetailUiEvent()
    data object OnNavigateToAddStudySets : ClassDetailUiEvent()
    data class ShowError(val message: String) : ClassDetailUiEvent()
    data object ClassDeleted : ClassDetailUiEvent()
    data object NavigateToEditClass : ClassDetailUiEvent()
    data object ExitClass : ClassDetailUiEvent()
    data object InviteToClassSuccess : ClassDetailUiEvent()
}