package shibin.kmp.androidiconswitcher.data

import io.github.shibin.iconswitcher.model.IconConfig
import io.github.shibin.iconswitcher.model.IconInfo
import shibin.kmp.androidiconswitcher.R

object DemoData {

    val iconConfig = IconConfig(
        defaultIcon = "default",
        icons = listOf(

            IconInfo(
                name = "default",
                alias = ".DefaultIcon",
                previewIcon = R.drawable.preview_default
            ),

            IconInfo(
                name = "blue",
                alias = ".BlueIcon",
                previewIcon = R.drawable.preview_blue
            ),

            IconInfo(
                name = "red",
                alias = ".RedIcon",
                previewIcon = R.drawable.preview_red
            ),
            IconInfo(
                name = "green",
                alias = ".GreenIcon",
                previewIcon = R.drawable.preview_green
            )
        )
    )
}