package jp.kaleidot725.emomemo.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentLicenseBinding
import jp.kaleidot725.emomemo.extension.viewBinding

class LicenseFragment : Fragment(R.layout.fragment_license) {
    private val binding: FragmentLicenseBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.licenseWebView.loadUrl("file:///android_asset/licenses.html")
    }
}
