package jp.kaleidot725.emomemo.model.repository

import jp.kaleidot725.emomemo.model.dao.MemoDao
import jp.kaleidot725.emomemo.model.entity.Memo

val DUMMY_MEMO_LIST = listOf(
    Memo("0000", "英語", "発音の仕方", ""),
    Memo("0001", "英語", "発音の応用", ""),
    Memo("0002", "数学", "微分・積分の仕方", ""),
    Memo("0003", "数学", "二次方程式についてのまとめ", ""),
    Memo("0004", "ただのメモ", "今日の買い物", ""),
    Memo("0005", "ただのメモ", "明日の買い物", ""),
    Memo("0006", "ごみばこ", "単語を覚えるためのメモ", ""),
    Memo("0007", "ごみばこ", "ラーメンをうまくつくるためのメモ", "")
)

class MemoRepository(private val dao: MemoDao) {
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
        return dao.getUser(id)
    }
}
