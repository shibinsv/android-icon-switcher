package io.github.shibin.iconswitcher.repository

import io.github.shibin.iconswitcher.manager.AliasManager
import io.github.shibin.iconswitcher.preferencesManager.PreferencesManager
import io.github.shibin.iconswitcher.result.IconUpdateResult
import io.github.shibin.iconswitcher.validator.IconValidator

class IconRepository(
    private val aliasManager: AliasManager,
    private val preferencesManager: PreferencesManager,
    private val validator: IconValidator

) {

    fun updateIcon(icon: String): IconUpdateResult {

        val iconInfo = validator.findIcon(icon)
            ?: return IconUpdateResult.InvalidIcon(icon)

        val current = preferencesManager.getCurrentIcon()

        if (current == iconInfo.name) {
            return IconUpdateResult.AlreadyApplied(icon)
        }

        val switched = aliasManager.switchIcon(iconInfo.name)

        if (!switched) {
            return IconUpdateResult.SwitchFailed(icon)
        }

        preferencesManager.saveCurrentIcon(iconInfo.name)

        return IconUpdateResult.Success(iconInfo.name)
    }
}