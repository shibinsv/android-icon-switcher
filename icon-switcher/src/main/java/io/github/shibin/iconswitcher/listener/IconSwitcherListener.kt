package io.github.shibin.iconswitcher.listener

import io.github.shibin.iconswitcher.model.IconInfo

interface IconSwitcherListener {

    /**
     * Library initialized successfully
     */
    fun onInitialized() {}

    /**
     * Icon successfully changed
     */
    fun onIconChanged(icon: IconInfo) {}

    /**
     * Requested icon is already active
     */
    fun onAlreadyApplied(icon: IconInfo) {}

    /**
     * Unknown icon requested
     */
    fun onInvalidIcon(requested: String) {}

    /**
     * Switching failed
     */
    fun onSwitchFailed(requested: String) {}

    /**
     * Any unexpected error
     */
    fun onError(error: Throwable) {}
}