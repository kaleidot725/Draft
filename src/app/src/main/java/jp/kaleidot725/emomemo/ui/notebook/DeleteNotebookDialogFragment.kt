package jp.kaleidot725.emomemo.ui.notebook

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentDeleteNotebookBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeleteNotebookDialogFragment : DialogFragment(R.layout.fragment_delete_notebook) {
    private val navController: NavController get() = findNavController()
    private val binding: FragmentDeleteNotebookBinding by viewBinding()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val deleteNotebookViewModel: DeleteNotebookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainViewModel = mainViewModel
        binding.deleteNotebookViewModel = deleteNotebookViewModel

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.notebookSpinner.apply {
            this.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item).apply {
                this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

            this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    deleteNotebookViewModel.onNotebookSelected(position)
                }
            }
        }

        deleteNotebookViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            when (event) {
                is DeleteNotebookViewModel.NavEvent.Success -> {
                    mainViewModel.deleteNotebook(event.notebook)
                    navController.popBackStack()
                }
                is DeleteNotebookViewModel.NavEvent.Cancel -> {
                    navController.popBackStack()
                }
                else -> {
                    navController.popBackStack()
                }
            }
        })
        deleteNotebookViewModel.fetch()
    }
}
