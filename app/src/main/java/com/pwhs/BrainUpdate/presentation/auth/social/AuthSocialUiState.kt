package com.pwhs.quickmem.presentation.auth.social

import com.pwhs.quickmem.core.data.enums.AuthProvider
import com.pwhs.quickmem.core.data.enums.UserRole

data class AuthSocialUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val provider: AuthProvider? = null,
    val email: String = "",
    val birthDay: String = "",
    val birthdayError: String = "",
    val avatarUrl: String = "",
    val role: UserRole = UserRole.STUDENT,
    val fullName: String = "",
    val token: String = "",
)