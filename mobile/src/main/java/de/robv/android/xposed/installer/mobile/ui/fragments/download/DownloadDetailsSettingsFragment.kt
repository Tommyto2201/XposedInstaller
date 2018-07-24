package de.robv.android.xposed.installer.mobile.ui.fragments.download

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.preference.Preference
import de.robv.android.xposed.installer.R

import de.robv.android.xposed.installer.core.util.RepoLoader
import de.robv.android.xposed.installer.mobile.ui.activities.DownloadDetailsActivity
import de.robv.android.xposed.installer.mobile.ui.fragments.base.BasePreferenceFragment

@Suppress("DEPRECATION", "UNUSED_ANONYMOUS_PARAMETER")
class DownloadDetailsSettingsFragment : BasePreferenceFragment() {

    companion object {
        val TAG: String = DownloadDetailsSettingsFragment::class.java.simpleName
        fun newInstance() = DownloadDetailsSettingsFragment()
    }
    private var mActivity: DownloadDetailsActivity? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mActivity = activity as DownloadDetailsActivity
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val module = mActivity!!.module ?: return
        val packageName = module.packageName

        addPreferencesFromResource(R.xml.module_prefs)

        val prefs = activity!!.getSharedPreferences("module_settings", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        if (prefs.getBoolean("no_global", true)) {
            for ((key) in prefs.all) {
                if (prefs.getString(key, "") == "global") {
                    editor.putString(key, "").apply()
                }
            }

            editor.putBoolean("no_global", false).apply()
        }

        findPreference("release_type").onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            RepoLoader.getInstance().setReleaseTypeLocal(packageName, newValue as String)
            true
        }
    }
}