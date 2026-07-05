package io.github.shibin.iconswitcher.preferencesManager


import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    companion object {
        private const val PREF_NAME = "icon_switcher_pref"
        private const val KEY_CURRENT_ICON = "current_icon"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getCurrentIcon(): String? {
        return preferences.getString(KEY_CURRENT_ICON, null)
    }

    fun saveCurrentIcon(icon: String) {
        preferences.edit()
            .putString(KEY_CURRENT_ICON, icon)
            .apply()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
}