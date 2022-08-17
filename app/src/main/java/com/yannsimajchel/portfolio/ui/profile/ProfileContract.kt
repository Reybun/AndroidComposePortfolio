package com.yannsimajchel.portfolio.ui.profile

import com.yannsimajchel.portfolio.application.base.UiEffect
import com.yannsimajchel.portfolio.application.base.UiEvent
import com.yannsimajchel.portfolio.application.base.UiState


class ProfileContract {

    sealed class Effect : UiEffect {
        object OnUserSendEmail : Effect()
        object OnUserOpenLinkedIn : Effect()
        object OnUserOpenGithub : Effect()
    }

    sealed class Event : UiEvent {
        object OnUserUpdateDarkMode : Event()
        object OnUserClickOnEmail : Event()
        object OnUserClickOnLinkedIn : Event()
        object OnUserClickOnGithub : Event()
    }

    data class State(
        val isDarkMode: Boolean,
    ) : UiState
}