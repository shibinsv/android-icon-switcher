package io.github.shibin.iconswitcher.result

sealed interface IconUpdateResult {

    data class Success(
        val icon: String
    ) : IconUpdateResult

    data class AlreadyApplied(
        val icon: String
    ) : IconUpdateResult

    data class InvalidIcon(
        val requested: String
    ) : IconUpdateResult

    data class SwitchFailed(
        val requested: String
    ) : IconUpdateResult
}