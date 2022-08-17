package com.yannsimajchel.portfolio

import com.yannsimajchel.portfolio.application.base.UiEffect
import com.yannsimajchel.portfolio.application.base.UiEvent
import com.yannsimajchel.portfolio.application.base.UiState


class MainContract {

    sealed class Effect : UiEffect {

    }

    sealed class Event : UiEvent {
        data class OnUserUpdateDarkMode(val isDarkModeActivated: Boolean) : Event()

    }

    data class State(
        val isDarkMode: Boolean,
    ) : UiState
}