package jp.kaleidot725.emomemo.top

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import jp.kaleidot725.emomemo.R

class TopFragment : Fragment() {
    private val navController : NavController get() = findNavController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed(Runnable {
            navController.navigate(R.id.action_topFragment_to_homeFragment)
        }, 3000)
    }
}
