package jp.kaleidot725.emomemo.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.kaleidot725.emomemo.R

class SettingFragment : Fragment(R.layout.fragment_setting) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.setting_fragment_title)
    }
}
