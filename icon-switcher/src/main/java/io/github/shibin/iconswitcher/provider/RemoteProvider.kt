package io.github.shibin.iconswitcher.provider

interface RemoteProvider {

    fun observe(
        onChanged: (String) -> Unit,
        onError: (Throwable) -> Unit = {}
    )

    fun stop()
}