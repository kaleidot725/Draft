package jp.kaleidot725.emomemo.view.pages

import androidx.navigation.NavBackStackEntry

sealed class Page(val route: String) {
    object Main : Page(route = "main")
    object AddNoteBook : Page(route = "main/add")
    object DeleteNotebook : Page(route = "main/remove/{notebookId}") {
        fun createRoute(notebookId: Int) = "main/remove/$notebookId"
        fun getArgumentId(entry: NavBackStackEntry): Int {
            return entry.arguments?.getString("notebookId")?.toInt() ?: 0
        }
    }

    object Memo : Page(route = "memo/{memoId}") {
        fun createRoute(memoId: Int) = "memo/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Int {
            return entry.arguments?.getString("memoId")?.toInt() ?: 0
        }
    }

    object DeleteMemo : Page(route = "memo/delete/{memoId}") {
        fun createRoute(memoId: Int) = "memo/delete/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Int {
            return entry.arguments?.getString("memoId")?.toInt() ?: 0
        }
    }
}
