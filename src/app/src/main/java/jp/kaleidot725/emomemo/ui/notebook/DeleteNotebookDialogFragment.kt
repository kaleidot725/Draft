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
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeleteNotebookDialogFragment : DialogFragment(R.layout.fragment_delete_notebook) {
    private val navController: NavController get() = findNavController()
    private val binding: FragmentDeleteNotebookBinding by viewBinding()
    private val deleteNotebookViewModel: DeleteNotebookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.deleteNotebookViewModel = deleteNotebookViewModel
        binding.notebookSpinner.adapter = createSpinnerAdapter(null)

        deleteNotebookViewModel.notebook.observe(viewLifecycleOwner, Observer {
            binding.notebookSpinner.adapter = createSpinnerAdapter(it)
            binding.notebookSpinner.onItemSelectedListener = createSpinnerOnItemSelectListener()
        })

        deleteNotebookViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            navController.popBackStack(R.id.homeFragment, true)
            navController.navigate(R.id.homeFragment)
        })
    }

    private fun createSpinnerAdapter(data: List<String>?): ArrayAdapter<String> {
        return if (data != null) {
            ArrayAdapter<String>(requireContext(), R.layout.spinner_item, R.id.item_text_view, data).apply {
                setDropDownViewResource(R.layout.spinner_drop_down_item)
            }
        } else {
            ArrayAdapter<String>(requireContext(), R.layout.spinner_item, R.id.item_text_view).apply {
                setDropDownViewResource(R.layout.spinner_drop_down_item)
            }
        }
    }

    private fun createSpinnerOnItemSelectListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                deleteNotebookViewModel.onNotebookSelected(position)
            }
        }
    }
}
