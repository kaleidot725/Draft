package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MemoDao
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity

val DUMMY_MEMO_LIST = listOf(
    MemoEntity(0, "英語", "発音の仕方"),
    MemoEntity(1, "英語", "発音の応用"),
    MemoEntity(2, "数学", "微分・積分の仕方"),
    MemoEntity(3, "数学", "二次方程式についてのまとめ"),
    MemoEntity(4, "ただのメモ", "今日の買い物"),
    MemoEntity(5, "ただのメモ", "明日の買い物"),
    MemoEntity(6, "ごみばこ", "単語を覚えるためのメモ"),
    MemoEntity(7, "ごみばこ", "ラーメンをうまくつくるためのメモ")
)

class MemoRepository(private val dao: MemoDao) {
    fun insert(memo: MemoEntity) {
        dao.insert(memo)
    }

    fun update(memo: MemoEntity) {
        dao.update(memo)
    }

    fun delete(memoEntity: MemoEntity) {
        dao.delete(memoEntity)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    fun getAll(): List<MemoEntity> {
        return dao.getAll()
    }

    fun getMemo(id: Int): MemoEntity {
        return dao.getMemo(id)
    }
}
