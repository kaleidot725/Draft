package jp.kaleidot725.emomemo.ui.memo

import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.os.Bundle
import android.view.ActionMode
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentMemoBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.ui.common.ActionModeEvent
import jp.kaleidot725.emomemo.ui.common.controller.ActionModeController
import jp.kaleidot725.emomemo.ui.common.controller.MessageItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_memo.message_edit_text
import kotlinx.android.synthetic.main.fragment_memo.recycler_view
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions
import timber.log.Timber

@RuntimePermissions
class MemoFragment : Fragment(R.layout.fragment_memo) {
    private val viewModel: MemoViewModel by viewModel()
    private val binding: FragmentMemoBinding by viewBinding()
    private val navController: NavController get() = findNavController()
    private lateinit var epoxyController: MessageItemRecyclerViewController
    private lateinit var actionModeController: ActionModeController
    private val refreshObserver: LifecycleObserver = object : DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) = viewModel.refresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.memoViewModel = viewModel
        binding.recyclerView.setup()
        binding.voiceButton.setOnClickListener { showRecordAudioWithPermissionCheck() }
        binding.sendButton.setOnClickListener { viewModel.create() }

        viewModel.messagesWithSelectedSet.observe(viewLifecycleOwner, Observer {
            epoxyController.submitList(it.messages)
            epoxyController.submitSelectedList(it.selectedMessages)
            epoxyController.requestForcedModelBuild()
            hideSoftKeyBoard()
            scrollToLatestMessage()
        })

        viewModel.actionMode.observe(viewLifecycleOwner, Observer {
            when (it) {
                ActionModeEvent.ON -> actionModeController.startActionMode(requireActivity())
                ActionModeEvent.OFF -> actionModeController.cancelActionMode()
                else -> Timber.w("invalid actionEvent")
            }
        })

        viewModel.navEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MemoViewModel.NavEvent.NavigateEditMessage -> navigateEditMessage(it.message)
            }
        })

        navController.getBackStackEntry(R.id.memoFragment).lifecycle.addObserver(refreshObserver)
    }

    override fun onDestroyView() {
        hideSoftKeyBoard()
        navController.getBackStackEntry(R.id.homeFragment).lifecycle.removeObserver(refreshObserver)
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

    private fun scrollToLatestMessage() {
        binding.recyclerView.scrollToPosition(0)
    }

    private fun hideSoftKeyBoard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(message_edit_text.windowToken, 0)
    }

    private fun navigateEditMessage(message: MessageEntity) {
        val action = MemoFragmentDirections.actionMemoFragmentToEditMessageDialogFragment(message)
        navController.navigate(action)
    }

    private fun EpoxyRecyclerView.setup() {
        actionModeController = ActionModeController(
            R.menu.memo_action_menu,
            ActionMode.TYPE_PRIMARY,
            onAction = {
                when (it.itemId) {
                    R.id.delete -> viewModel.deleteAction()
                    R.id.edit -> viewModel.editAction()
                }
            },
            onDestroy = { viewModel.cancelAction() }
        )

        epoxyController = MessageItemRecyclerViewController(
            onClickMessage = { viewModel.select(it) },
            onLongTapMessage = { viewModel.startAction(it) }
        )

        val drawable = resources.getDrawable(R.drawable.divider, requireContext().theme)
        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply { setDrawable(drawable) }

        this.setController(epoxyController)
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        this.addItemDecoration(decoration)
    }
}
