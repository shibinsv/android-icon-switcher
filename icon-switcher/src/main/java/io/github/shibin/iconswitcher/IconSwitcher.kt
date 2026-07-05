package io.github.shibin.iconswitcher

import android.content.Context
import io.github.shibin.iconswitcher.builder.IconSwitcherBuilder
import io.github.shibin.iconswitcher.listener.IconSwitcherListener
import io.github.shibin.iconswitcher.manager.AliasManager
import io.github.shibin.iconswitcher.model.DashboardState
import io.github.shibin.iconswitcher.model.IconConfig
import io.github.shibin.iconswitcher.preferencesManager.PreferencesManager
import io.github.shibin.iconswitcher.provider.RemoteProvider
import io.github.shibin.iconswitcher.repository.IconRepository
import io.github.shibin.iconswitcher.result.IconUpdateResult
import io.github.shibin.iconswitcher.validator.IconValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object IconSwitcher {

    private lateinit var provider: RemoteProvider
    private lateinit var repository: IconRepository

    private var listener: IconSwitcherListener? = null

    private const val TAG = "IconSwitcher"

    private fun addLog(message: String) {

        val current = _dashboardState.value

        _dashboardState.value = current.copy(
            logs = current.logs + message
        )
    }

    private val _dashboardState =
        MutableStateFlow(DashboardState())

    val dashboardState: StateFlow<DashboardState>
        get() = _dashboardState

    fun builder(
        context: Context
    ): IconSwitcherBuilder {
        return IconSwitcherBuilder(context)
    }

    internal fun initializeInternal(
        context: Context,
        config: IconConfig,
        provider: RemoteProvider,
        listener: IconSwitcherListener?,
        loggingEnabled: Boolean,
        cachingEnabled: Boolean
    ) {

        val preferences = PreferencesManager(context)
        val validator = IconValidator(config)
        val aliasManager = AliasManager(context, config)

        repository = IconRepository(
            aliasManager, preferences, validator
        )

        this.provider = provider
        this.listener = listener

        _dashboardState.value =
            _dashboardState.value.copy(
                connected = true
            )
        provider.observe(
            onChanged = { icon ->

                when (val result = repository.updateIcon(icon)) {

                    is IconUpdateResult.Success -> {

                        val iconInfo = validator.findIcon(result.icon)

                        if (iconInfo != null) {

                            _dashboardState.value =
                                _dashboardState.value.copy(
                                    currentIcon = iconInfo,
                                    firestoreValue = result.icon,
                                    connected = true
                                )

                            listener?.onIconChanged(iconInfo)
                        }
                    }

                    is IconUpdateResult.AlreadyApplied -> {

                        val iconInfo = validator.findIcon(result.icon)

                        if (iconInfo != null) {

                            _dashboardState.value =
                                _dashboardState.value.copy(
                                    currentIcon = iconInfo,
                                    firestoreValue = result.icon,
                                    connected = true
                                )

                            listener?.onAlreadyApplied(iconInfo)
                        }
                    }

                    is IconUpdateResult.InvalidIcon -> {

                        addLog("Invalid icon: ${result.requested}")

                        listener?.onInvalidIcon(result.requested)
                    }

                    is IconUpdateResult.SwitchFailed -> {

                        addLog("Failed to switch: ${result.requested}")

                        listener?.onSwitchFailed(result.requested)
                    }
                }

            },
            onError = {

                _dashboardState.value =
                    _dashboardState.value.copy(
                        connected = false
                    )

                addLog("Firestore error")

                listener?.onError(it)
            }
        )
    }

    fun stop() {
        provider.stop()
    }
}

