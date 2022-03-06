package jp.kaleidot725.emomemo.view.pages

sealed class Page(val route: String) {
    object Main : Page(route = "main")
//    object Library : Page(route = "library")
//    object Details : Page(route = "details/{id}") {
//        fun createRoute(id: Int) = "details/$id"
//        fun getArgumentId(entry: NavBackStackEntry): Int {
//            return entry.arguments?.getString("id")?.toInt() ?: 0
//        }
//    }
}