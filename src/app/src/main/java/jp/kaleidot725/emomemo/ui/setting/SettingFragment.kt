package jp.kaleidot725.emomemo.ui.setting

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import jp.kaleidot725.emomemo.R


class SettingFragment : PreferenceFragmentCompat() {
    private val navController get() = findNavController()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        findPreference<Preference>(getString(R.string.preference_license))?.setOnPreferenceClickListener {
            navController.navigate(R.id.action_settingFragment_to_licenseFragment)
            true
        }

        val packageInfo = requireContext().packageManager.getPackageInfo(requireContext().packageName, 0)
        findPreference<Preference>(getString(R.string.version))?.summary = packageInfo.versionName
    }
}