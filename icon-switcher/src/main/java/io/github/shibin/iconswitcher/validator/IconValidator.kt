package io.github.shibin.iconswitcher.validator

import io.github.shibin.iconswitcher.model.IconConfig
import io.github.shibin.iconswitcher.model.IconInfo

class IconValidator(
    private val config: IconConfig
) {

    fun findIcon(name: String): IconInfo? {
        return config.icons.firstOrNull {
            it.name == name
        }
    }

    fun isValid(name: String): Boolean {
        return findIcon(name) != null
    }

    fun getDefault(): IconInfo {
        return config.icons.first {
            it.name == config.defaultIcon
        }
    }
}