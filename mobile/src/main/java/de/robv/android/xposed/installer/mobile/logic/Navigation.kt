package de.robv.android.xposed.installer.mobile.logic

import android.support.annotation.IntegerRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import de.robv.android.xposed.installer.R
import de.robv.android.xposed.installer.mobile.ui.fragments.*
import de.robv.android.xposed.installer.mobile.ui.fragments.download.DownloadFragment

enum class Navigation(@IntegerRes val id: Int, @StringRes val title: Int)
{
    NAV_DOWNLOAD(R.id.nav_item_downloads, R.string.nav_item_download),
    NAV_MODULES(R.id.nav_item_modules, R.string.nav_item_modules),
    NAV_HOME(R.id.nav_item_framework, R.string.nav_item_install),
    NAV_LOGS(R.id.nav_item_logs, R.string.nav_item_logs),
    NAV_SETTINGS(R.id.nav_item_settings, R.string.nav_item_settings),
    NAV_SUPPORT(R.id.nav_item_support, R.string.nav_item_support),
    NAV_ABOUT(R.id.nav_item_about, R.string.nav_item_about),
    FRAG_ERROR(R.id.frag_item_error, R.string.error_fragment_title);
}
fun findNavPosById(id: Int): Navigation = when (id) {
    Navigation.NAV_DOWNLOAD.id -> Navigation.NAV_DOWNLOAD
    Navigation.NAV_MODULES.id -> Navigation.NAV_MODULES
    Navigation.NAV_HOME.id -> Navigation.NAV_HOME
    Navigation.NAV_LOGS.id -> Navigation.NAV_LOGS
    Navigation.NAV_SETTINGS.id -> Navigation.NAV_SETTINGS
    Navigation.NAV_SUPPORT.id -> Navigation.NAV_SUPPORT
    Navigation.NAV_ABOUT.id -> Navigation.NAV_ABOUT
    else -> Navigation.FRAG_ERROR
}

fun Navigation.createFragment(): Fragment = when (this) {
    Navigation.NAV_DOWNLOAD -> DownloadFragment.newInstance()
    Navigation.NAV_MODULES -> ModulesFragment.newInstance()
    Navigation.NAV_HOME -> StatusInstallerFragment.newInstance()
    Navigation.NAV_LOGS -> LogsFragment.newInstance()
    Navigation.NAV_SETTINGS -> SettingsFragment.newInstance()
    Navigation.NAV_SUPPORT -> SupportFragment.newInstance()
    Navigation.NAV_ABOUT -> AboutFragment.newInstance()
    else -> ErrorFragment.newInstance()
}

fun Navigation.getTag(): String = when (this) {
    Navigation.NAV_DOWNLOAD -> DownloadFragment.TAG
    Navigation.NAV_MODULES -> ModulesFragment.TAG
    Navigation.NAV_HOME -> StatusInstallerFragment.TAG
    Navigation.NAV_LOGS -> LogsFragment.TAG
    Navigation.NAV_SETTINGS -> SettingsFragment.TAG
    Navigation.NAV_SUPPORT -> SupportFragment.TAG
    Navigation.NAV_ABOUT -> AboutFragment.TAG
    else -> ErrorFragment.TAG
}

fun Navigation.getPos(): Int = when (this) {
    Navigation.NAV_DOWNLOAD -> setNavPos(0, 2)
    Navigation.NAV_MODULES -> 1
    Navigation.NAV_HOME -> setNavPos(2, 0)
    Navigation.NAV_LOGS -> 3
    Navigation.NAV_SETTINGS -> 4
    Navigation.NAV_SUPPORT -> 5
    Navigation.NAV_ABOUT -> 6
    else -> 7
}
private fun setNavPos(bottom: Int, drawer: Int) = if (Utils().isBottomNav()) bottom else drawer
