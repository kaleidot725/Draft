package jp.kaleidot725.draft.view.sample

import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.data.entity.NotebookEntity

object SampleData {
    val memoList = listOf(
        MemoEntity(id = 0, notebookId = 0, title = "TEST1", content = "CONTENT1"),
        MemoEntity(id = 1, notebookId = 0, title = "TEST2", content = "CONTENT2"),
        MemoEntity(id = 2, notebookId = 0, title = "TEST3", content = "CONTENT3"),
        MemoEntity(id = 3, notebookId = 0, title = "TEST4", content = "CONTENT4"),
        MemoEntity(id = 4, notebookId = 0, title = "TEST5", content = "CONTENT5"),
        MemoEntity(id = 5, notebookId = 0, title = "TEST6", content = "CONTENT6"),
        MemoEntity(id = 6, notebookId = 0, title = "TEST7", content = "CONTENT7"),
        MemoEntity(id = 7, notebookId = 0, title = "TEST8", content = "CONTENT8"),
        MemoEntity(id = 8, notebookId = 0, title = "TEST9", content = "CONTENT9"),
        MemoEntity(id = 9, notebookId = 0, title = "TEST10", content = "CONTENT10"),
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
}
