package io.github.shibin.iconswitcher.manager

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import io.github.shibin.iconswitcher.model.IconConfig

class AliasManager(
    private val context: Context,
    private val config: IconConfig
) {

    private val packageManager = context.packageManager

    fun switchIcon(iconName: String): Boolean {

        val target = config.icons.firstOrNull {
            it.name == iconName && it.enabled
        } ?: return false

        config.icons.forEach { icon ->

            val component = ComponentName(
                context.packageName,
                context.packageName + icon.alias
            )

            packageManager.setComponentEnabledSetting(
                component,
                if (icon == target)
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                else
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }

        return true
    }
}