package io.github.shibin.iconswitcher.model

import androidx.annotation.DrawableRes

data class IconInfo(

    val name: String,

    val alias: String,

    @DrawableRes
    val previewIcon: Int,

    val enabled: Boolean = true

)