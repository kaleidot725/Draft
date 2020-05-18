package jp.kaleidot725.emomemo.model.repository

import jp.kaleidot725.emomemo.model.dao.MemoDao
import jp.kaleidot725.emomemo.model.entity.Memo

val DUMMY_MEMO_LIST = listOf(
    Memo(0, "英語", "発音の仕方"),
    Memo(1, "英語", "発音の応用"),
    Memo(2, "数学", "微分・積分の仕方"),
    Memo(3, "数学", "二次方程式についてのまとめ"),
    Memo(4, "ただのメモ", "今日の買い物"),
    Memo(5, "ただのメモ", "明日の買い物"),
    Memo(6, "ごみばこ", "単語を覚えるためのメモ"),
    Memo(7, "ごみばこ", "ラーメンをうまくつくるためのメモ")
)

class MemoRepository(private val dao: MemoDao) {
    suspend fun create(tag: String, title: String) {
        val memo = Memo(0, tag, title)
        insert(memo)
    }

    suspend fun insert(memo: Memo) {
        dao.insert(memo)
    }

    suspend fun update(memo: Memo) {
        dao.update(memo)
    }

    suspend fun delete(memo: Memo) {
        dao.delete(memo)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun getAll(): List<Memo> {
        return dao.getAll()
    }

    suspend fun getUser(id: Int): Memo {
        return dao.getMemo(id)
    }
}
