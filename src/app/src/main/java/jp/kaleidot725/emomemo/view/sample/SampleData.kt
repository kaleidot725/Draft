package jp.kaleidot725.emomemo.view.sample

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.MessageEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.domain.usecase.MemoDetails

object SampleData {
    val memoList = listOf(
        MemoEntity(id = 0, notebookId = 0, title = "TEST1"),
        MemoEntity(id = 1, notebookId = 0, title = "TEST2"),
        MemoEntity(id = 2, notebookId = 0, title = "TEST3"),
        MemoEntity(id = 3, notebookId = 0, title = "TEST4"),
        MemoEntity(id = 4, notebookId = 0, title = "TEST5"),
        MemoEntity(id = 5, notebookId = 0, title = "TEST6"),
        MemoEntity(id = 6, notebookId = 0, title = "TEST7"),
        MemoEntity(id = 7, notebookId = 0, title = "TEST8"),
        MemoEntity(id = 8, notebookId = 0, title = "TEST9"),
        MemoEntity(id = 9, notebookId = 0, title = "TEST10"),
    )

    val messageList = listOf(
        MessageEntity(id = 0, memoId = 0, value = "MESSAGE1", time = 0L),
        MessageEntity(id = 0, memoId = 1, value = "MESSAGE1", time = 0L),
        MessageEntity(id = 0, memoId = 2, value = "MESSAGE3", time = 0L),
        MessageEntity(id = 0, memoId = 3, value = "MESSAGE4", time = 0L),
        MessageEntity(id = 0, memoId = 4, value = "MESSAGE5", time = 0L),
        MessageEntity(id = 0, memoId = 5, value = "MESSAGE6", time = 0L),
        MessageEntity(id = 0, memoId = 6, value = "MESSAGE7", time = 0L),
        MessageEntity(id = 0, memoId = 7, value = "MESSAGE8", time = 0L),
        MessageEntity(id = 0, memoId = 8, value = "MESSAGE9", time = 0L),
        MessageEntity(id = 0, memoId = 9, value = "MESSAGE10", time = 0L),
    )

    val notebookList = listOf(
        NotebookEntity(id = 0, title = "NOTEBOOK1"),
        NotebookEntity(id = 1, title = "NOTEBOOK2"),
        NotebookEntity(id = 2, title = "NOTEBOOK3"),
        NotebookEntity(id = 3, title = "NOTEBOOK4"),
        NotebookEntity(id = 4, title = "NOTEBOOK5"),
        NotebookEntity(id = 5, title = "NOTEBOOK6"),
        NotebookEntity(id = 6, title = "NOTEBOOK7"),
        NotebookEntity(id = 7, title = "NOTEBOOK8"),
        NotebookEntity(id = 8, title = "NOTEBOOK9"),
        NotebookEntity(id = 9, title = "NOTEBOOK10")
    )

    val memoDetailsList = listOf(
        MemoDetails(memo = memoList[0], lastMessage = messageList[0], messageCount = 1),
        MemoDetails(memo = memoList[1], lastMessage = messageList[1], messageCount = 1),
        MemoDetails(memo = memoList[2], lastMessage = messageList[2], messageCount = 1),
        MemoDetails(memo = memoList[3], lastMessage = messageList[3], messageCount = 1),
        MemoDetails(memo = memoList[4], lastMessage = messageList[4], messageCount = 1),
        MemoDetails(memo = memoList[5], lastMessage = messageList[5], messageCount = 1),
        MemoDetails(memo = memoList[6], lastMessage = messageList[6], messageCount = 1),
        MemoDetails(memo = memoList[7], lastMessage = messageList[7], messageCount = 1),
        MemoDetails(memo = memoList[8], lastMessage = messageList[8], messageCount = 1),
        MemoDetails(memo = memoList[9], lastMessage = messageList[9], messageCount = 1),
    )
}