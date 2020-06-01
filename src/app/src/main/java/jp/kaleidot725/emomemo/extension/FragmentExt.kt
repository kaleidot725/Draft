package jp.kaleidot725.emomemo.extension

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import jp.kaleidot725.emomemo.R
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewDataBinding> Fragment.viewBinding(): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T> {
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            val view = thisRef.requireView()
            var binding = view.getTag(R.id.fragment_binding) as? T
            if (binding == null) {
                binding = DataBindingUtil.bind(view)!!
                binding.lifecycleOwner = thisRef.viewLifecycleOwner
                view.setTag(R.id.fragment_binding, binding)
            }
            return binding
        }
    }
