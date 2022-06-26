package jp.kaleidot725.draft.view.pages

import androidx.navigation.NavBackStackEntry

sealed class Destination(val route: String) {
    object Notebook : Destination(route = "notebook")

    object AddNoteBook : Destination(route = "notebook/add/notebook")

    object DeleteNotebook : Destination(route = "notebook/remove/{notebookId}") {
        fun createRoute(notebookId: Long) = "notebook/remove/$notebookId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("notebookId")?.toLong() ?: 0L
        }
    }

    object EditNotebook : Destination(route = "notebook/edit/{notebookId}") {
        fun createRoute(notebookId: Long) = "notebook/edit/$notebookId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("notebookId")?.toLong() ?: 0L
        }
    }

    object NotebookBottom : Destination(route = "notebook/bottom/{notebookId}") {
        fun createRoute(notebookId: Long) = "notebook/bottom/$notebookId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("notebookId")?.toLong() ?: 0L
        }
    }

    object Memo : Destination(route = "memo/{memoId}") {
        fun createRoute(memoId: Long) = "memo/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("memoId")?.toLong() ?: 0L
        }
    }

    object AddMemo : Destination(route = "memo/add/{memoId}") {
        fun createRoute(memoId: Long) = "memo/add/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("memoId")?.toLong() ?: 0L
        }
    }

    object DeleteMemo : Destination(route = "memo/delete/{memoId}") {
        fun createRoute(memoId: Long) = "memo/delete/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("memoId")?.toLong() ?: 0L
        }
    }

    object EditMemo : Destination(route = "memo/edit/{memoId}") {
        fun createRoute(memoId: Long) = "memo/edit/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("memoId")?.toLong() ?: 0L
        }
    }

    object MemoBottom : Destination(route = "memo/bottom/{memoId}") {
        fun createRoute(memoId: Long) = "memo/bottom/$memoId"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("memoId")?.toLong() ?: 0L
        }
    }
}
