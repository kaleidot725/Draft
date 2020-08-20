package jp.kaleidot725.emomemo.ui.memo

import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentMemoBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.EmptyStatus
import jp.kaleidot725.emomemo.ui.MainViewModel
import jp.kaleidot725.emomemo.ui.common.controller.MessageItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_memo.message_edit_text
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MemoFragment : Fragment(R.layout.fragment_memo) {
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val memoViewModel: MemoViewModel by viewModel()
    private val binding: FragmentMemoBinding by viewBinding()
    private val navController: NavController get() = findNavController()

    private lateinit var messageItemRecyclerViewController: MessageItemRecyclerViewController
    private lateinit var messageItemLayoutManager: LinearLayoutManager
    private lateinit var messageItemDecoration: DividerItemDecoration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageItemRecyclerViewController = MessageItemRecyclerViewController()
        messageItemLayoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.VERTICAL
        }
        messageItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
            setDrawable(resources.getDrawable(R.drawable.divider, requireContext().theme))
        }

        binding.mainViewModel = mainViewModel
        binding.memoViewModel = memoViewModel
        binding.recyclerView.apply {
            adapter = messageItemRecyclerViewController.adapter
            layoutManager = messageItemLayoutManager
            addItemDecoration(messageItemDecoration)
        }

        binding.voiceButton.setOnClickListener {
            showRecordAudioWithPermissionCheck()
        }

        binding.sendButton.setOnClickListener {
            mainViewModel.createMessage(memoViewModel.inputMessage.value ?: "")
            memoViewModel.reset()
        }

        mainViewModel.messages.observe(viewLifecycleOwner, Observer {
            messageItemRecyclerViewController.setData(it)
            binding.recyclerView.smoothScrollToPosition(it.count())
        })

        mainViewModel.emptyStatus.observe(viewLifecycleOwner, Observer {
            binding.emptyMessageTextView.text = when (it) {
                EmptyStatus.MESSAGE -> getString(R.string.memo_no_message_text)
                else -> ""
            }
        })

        mainViewModel.selectedMemo.observe(viewLifecycleOwner, Observer {
            requireActivity().title = it.title
        })
    }

    override fun onDestroyView() {
        hideSoftKeyBoard()
        super.onDestroyView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(RECORD_AUDIO)
    fun showRecordAudio() {
        navController.navigate(R.id.action_memoFragment_to_audioRecognizerFragment)
    }

    @OnShowRationale(RECORD_AUDIO)
    fun showRationaleForRecordAudio(request: PermissionRequest) {
        request.proceed()
    }

    @OnPermissionDenied(RECORD_AUDIO)
    fun onRecordAudioDenied() {
        Toast.makeText(context, R.string.memo_fragment_audio_permission_denied, Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(RECORD_AUDIO)
    fun onRecordAudioNeverAskAgain() {
        Toast.makeText(context, R.string.memo_fragment_audio_permission_never_ask_again, Toast.LENGTH_SHORT).show()
    }

    private fun hideSoftKeyBoard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(message_edit_text.windowToken, 0)
    }
}
