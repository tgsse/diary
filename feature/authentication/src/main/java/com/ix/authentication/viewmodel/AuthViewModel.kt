package com.ix.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.ix.authentication.BuildConfig
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


data class AuthState(
    val isLoading: Boolean = false,
//    val featureEntity: FeatureEntity? = null,
)

sealed class AuthEvent {
//    object Init : AuthEvent()
    data class Authenticate(val idToken: String): AuthEvent()
}

//@HiltViewModel
class AuthViewModel: ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()


    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Authenticate -> authenticate(event.idToken)
        }
    }

    private fun authenticate(idToken: String) {
        viewModelScope.launch {
            try {
//                TODO: This belongs in the data module
//                val result = withContext(Dispatchers.IO) {
//                    App.create(BuildConfig.mongoServiceId).login(Credentials.google(idToken, GoogleAuthType.ID_TOKEN))
//                }.loggedIn
//                Timber.d("viewmodel auth result: $result")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}