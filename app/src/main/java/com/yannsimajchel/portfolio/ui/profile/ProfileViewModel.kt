package com.yannsimajchel.portfolio.ui.profile

import androidx.lifecycle.viewModelScope
import com.yannsimajchel.portfolio.application.base.BaseViewModel
import com.yannsimajchel.portfolio.domain.app.ObserveIsDarkModeActivatedUseCase
import com.yannsimajchel.portfolio.domain.app.UpdateDarkModeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val updateDarkModeUseCase: UpdateDarkModeUseCase,
    private val observeIsDarkModeActivatedUseCase: ObserveIsDarkModeActivatedUseCase,
) : BaseViewModel<ProfileContract.Event, ProfileContract.State, ProfileContract.Effect>() {

    override fun createInitialState(): ProfileContract.State {
        return ProfileContract.State(
            isDarkMode = false
        )
    }

    override fun handleEvent(event: ProfileContract.Event) {
        when (event) {
            ProfileContract.Event.OnUserUpdateDarkMode -> updateDarkMode(!currentState.isDarkMode)
            ProfileContract.Event.OnUserClickOnEmail -> setEffect { ProfileContract.Effect.OnUserSendEmail }
            ProfileContract.Event.OnUserClickOnLinkedIn -> setEffect { ProfileContract.Effect.OnUserOpenLinkedIn }
            ProfileContract.Event.OnUserClickOnGithub -> setEffect { ProfileContract.Effect.OnUserOpenGithub }
        }
    }

    init {
        observeIsDarkMode()
    }

    /**
     * Get info if the dark mode is active or not
     */
    private fun observeIsDarkMode() {
        viewModelScope.launch {
            observeIsDarkModeActivatedUseCase().collect {
                setState { copy(isDarkMode = it) }
            }
        }
    }


    /**
     * Get info if the dark mode is active or not
     */
    private fun updateDarkMode(isDarkModeActivated: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateDarkModeUseCase(isDarkModeActivated)
            }
        }
    }
}