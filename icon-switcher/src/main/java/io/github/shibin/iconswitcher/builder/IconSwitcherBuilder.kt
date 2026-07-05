package io.github.shibin.iconswitcher.builder

import android.content.Context
import io.github.shibin.iconswitcher.IconSwitcher
import io.github.shibin.iconswitcher.listener.IconSwitcherListener
import io.github.shibin.iconswitcher.model.IconConfig
import io.github.shibin.iconswitcher.provider.RemoteProvider

class IconSwitcherBuilder(
    val context: Context
) {

    internal var config: IconConfig? = null
    internal var provider: RemoteProvider? = null
    internal var listener: IconSwitcherListener? = null

    internal var loggingEnabled = false
    internal var cachingEnabled = true

    fun config(config: IconConfig) = apply {
        this.config = config
    }

    fun provider(provider: RemoteProvider) = apply {
        this.provider = provider
    }

    fun listener(listener: IconSwitcherListener) = apply {
        this.listener = listener
    }

    fun enableLogging(enabled: Boolean) = apply {
        loggingEnabled = enabled
    }

    fun enableCaching(enabled: Boolean) = apply {
        cachingEnabled = enabled
    }

    fun build() {

        require(config != null) {
            "IconConfig is required."
        }

        require(provider != null) {
            "RemoteProvider is required."
        }

        IconSwitcher.initializeInternal(
            context = context,
            config = config!!,
            provider = provider!!,
            listener = listener,
            loggingEnabled = loggingEnabled,
            cachingEnabled = cachingEnabled
        )
    }
}
