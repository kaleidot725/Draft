package jp.kaleidot725.emomemo.view.pages

sealed class Page(val route: String) {
    object Main : Page(route = "main")
    object AddNoteBook : Page(route = "main/add")
    object RemoveNotebook : Page(route = "main/remove")
    object Memo : Page(route = "memo")
    object AddMemo : Page(route = "memo/add")
    
//    object Library : Page(route = "library")
//    object Details : Page(route = "details/{id}") {
//        fun createRoute(id: Int) = "details/$id"
//        fun getArgumentId(entry: NavBackStackEntry): Int {
//            return entry.arguments?.getString("id")?.toInt() ?: 0
//        }
//    }
}