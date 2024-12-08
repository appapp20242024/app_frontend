package com.pwhs.quickmem.core.datastore

import android.content.Context
import android.util.Patterns
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pwhs.quickmem.util.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class AppManager(private val context: Context) {
    companion object {
        val IS_FIRST_RUN = booleanPreferencesKey("IS_FIRST_RUN")
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
        val USER_ID = stringPreferencesKey("USER_ID")
        val USER_FULL_NAME = stringPreferencesKey("USER_FULL_NAME")
        val USER_AVATAR = stringPreferencesKey("USER_AVATAR")
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val USER_EMAIL = stringPreferencesKey("USER_EMAIL")
        val USER_ROLE = stringPreferencesKey("USER_ROLE")
        val USER_BIRTHDAY = stringPreferencesKey("USER_BIRTHDAY")
        val PUSH_NOTIFICATIONS = booleanPreferencesKey("PUSH_NOTIFICATIONS")
        val APP_PUSH_NOTIFICATIONS = booleanPreferencesKey("APP_PUSH_NOTIFICATIONS")
        val USER_COINS = intPreferencesKey("USER_COINS")
        val ENABLED_STUDY_SCHEDULE = booleanPreferencesKey("ENABLED_STUDY_SCHEDULE")
        val TIME_STUDY_SCHEDULE = stringPreferencesKey("TIME_STUDY_SCHEDULE")
        val IS_PLAY_SOUND = booleanPreferencesKey("IS_PLAY_SOUND")
    }

    val isFirstRun: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_FIRST_RUN] ?: true
        }
    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }
    val userId: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ID] ?: ""
        }
    val userFullName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FULL_NAME] ?: ""
        }
    val username: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_NAME] ?: ""
        }
    val userAvatarUrl: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_AVATAR] ?: ""
        }
    val userEmail: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL] ?: ""
        }
    val pushNotifications: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PUSH_NOTIFICATIONS] ?: true
        }
    val appPushNotifications: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[APP_PUSH_NOTIFICATIONS] ?: false
        }

    val userRole: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ROLE] ?: ""
        }

    val userBirthday: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_BIRTHDAY] ?: ""
        }

    val userCoins: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[USER_COINS] ?: 0
        }

    val enabledStudySchedule: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[ENABLED_STUDY_SCHEDULE] ?: false
        }

    val timeStudySchedule: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[TIME_STUDY_SCHEDULE] ?: ""
        }

    val isPlaySound: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_PLAY_SOUND] ?: true
        }

    suspend fun saveIsFirstRun(isFirstRun: Boolean) {
        Timber.d("Saving is first run: $isFirstRun")
        context.dataStore.edit { preferences ->
            preferences[IS_FIRST_RUN] = isFirstRun
        }
    }

    suspend fun saveIsLoggedIn(isLoggedIn: Boolean) {
        Timber.d("Saving is logged in: $isLoggedIn")
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }

    suspend fun saveUserId(userId: String) {
        Timber.d("Saving user id: $userId")
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    suspend fun saveUserFullName(userFullName: String) {
        Timber.d("Saving user full name: $userFullName")
        context.dataStore.edit { preferences ->
            preferences[USER_FULL_NAME] = userFullName
        }
    }

    suspend fun saveUserAvatar(userAvatar: String) {
        Timber.d("Saving user avatar: $userAvatar")
        context.dataStore.edit { preferences ->
            preferences[USER_AVATAR] = userAvatar
        }
    }

    suspend fun saveUserName(userName: String) {
        Timber.d("Saving user name: $userName")
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
        }
    }

    suspend fun saveUserEmail(email: String) {
        require(email.isNotEmpty()) { "Email cannot be empty" }
        require(Patterns.EMAIL_ADDRESS.matcher(email).matches()) { "Invalid email address" }
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
        }
    }

    suspend fun savePushNotifications(pushNotifications: Boolean) {
        Timber.d("Saving push notifications: $pushNotifications")
        context.dataStore.edit { preferences ->
            preferences[PUSH_NOTIFICATIONS] = pushNotifications
        }
    }

    suspend fun saveAppPushNotifications(appPushNotifications: Boolean) {
        Timber.d("Saving app push notifications: $appPushNotifications")
        context.dataStore.edit { preferences ->
            preferences[APP_PUSH_NOTIFICATIONS] = appPushNotifications
        }
    }

    suspend fun saveUserRole(userRole: String) {
        Timber.d("Saving user role: $userRole")
        context.dataStore.edit { preferences ->
            preferences[USER_ROLE] = userRole
        }
    }

    suspend fun saveUserBirthday(birthday: String) {
        Timber.d("Saving user birthday: $birthday")
        context.dataStore.edit { preferences ->
            preferences[USER_BIRTHDAY] = birthday
        }
    }

    suspend fun saveUserCoins(coins: Int) {
        Timber.d("Saving user coins: $coins")
        context.dataStore.edit { preferences ->
            preferences[USER_COINS] = coins
        }
    }

    suspend fun saveEnabledStudySchedule(enabled: Boolean) {
        Timber.d("Saving enabled study schedule: $enabled")
        context.dataStore.edit { preferences ->
            preferences[ENABLED_STUDY_SCHEDULE] = enabled
        }
    }

    suspend fun saveTimeStudySchedule(time: String) {
        Timber.d("Saving time study schedule: $time")
        context.dataStore.edit { preferences ->
            preferences[TIME_STUDY_SCHEDULE] = time
        }
    }

    suspend fun saveIsPlaySound(isPlaySound: Boolean) {
        Timber.d("Saving is play sound: $isPlaySound")
        context.dataStore.edit { preferences ->
            preferences[IS_PLAY_SOUND] = isPlaySound
        }
    }

    suspend fun clearAllData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}