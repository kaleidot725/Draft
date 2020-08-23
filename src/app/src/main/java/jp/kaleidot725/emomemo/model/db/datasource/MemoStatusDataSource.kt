package jp.kaleidot725.emomemo.model.db.datasource

import androidx.paging.PageKeyedDataSource
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoStatusDataSource(
    private val notebookId: Int,
    private val repository: MemoStatusRepository
) : PageKeyedDataSource<Int, MemoStatusView>() {
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MemoStatusView>) {}

    // API呼び出しをしているので、本来であればここで例外の対処を記述する必要がありますが省略しています。
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MemoStatusView>) {
        // 1 ページ目のデータを取得する
        val page = 1

        // 1 ページに表示するデータ数
        val perPage = params.requestedLoadSize

        // ページに表示するデータを取得する
        val items = repository.getPage(notebookId, page, perPage)

        // 次に表示するページの番号を計算する
        val nextPage = page + 1

        // 取得したデータ、次に表示するページの番号を結果として返す
        callback.onResult(items, null, nextPage)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MemoStatusView>) {
        // params.key には 前の loadInitial や loadAfter の呼び出しで返した nextPage が格納されている
        val page = params.key // 1 ページ目のデータを取得する

        // params.requestedLoadSize には 1ページに表示するデータ数が格納されている。
        val perPage = params.requestedLoadSize

        // ページに表示するデータを取得する
        val items = repository.getPage(notebookId, page, perPage)

        // 次に表示するページの番号を計算する
        val nextPage = page + 1

        // 取得したデータ、次に表示するページの番号を結果として返す
        callback.onResult(items, nextPage)
    }
}
