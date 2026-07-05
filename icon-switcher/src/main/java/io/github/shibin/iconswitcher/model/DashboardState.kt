package io.github.shibin.iconswitcher.model

data class DashboardState(
    val currentIcon: IconInfo? = null,
    val firestoreValue: String = "",
    val connected: Boolean = false,
    val logs: List<String> = emptyList()
)