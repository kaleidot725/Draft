package jp.kaleidot725.emomemo.view.pages

import androidx.navigation.NavBackStackEntry

sealed class Page(val route: String) {
    object Main : Page(route = "main")
    object AddNoteBook : Page(route = "main/add/notebook")
    object DeleteNotebook : Page(route = "main/remove/{notebookId}") {
        fun createRoute(notebookId: Long) = "main/remove/$notebookId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("notebookId")?.toLong() ?: 0L
        }
    }

    object AddMemo : Page(route = "main/add/memo/{memoId}") {
        fun createRoute(memoId: Long) = "main/add/memo/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("memoId")?.toLong() ?: 0L
        }
    }

    object Memo : Page(route = "memo/{memoId}") {
        fun createRoute(memoId: Long) = "memo/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("memoId")?.toLong() ?: 0L
        }
    }

    object DeleteMemo : Page(route = "memo/delete/{memoId}") {
        fun createRoute(memoId: Long) = "memo/delete/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("memoId")?.toLong() ?: 0L
        }
    }
}
