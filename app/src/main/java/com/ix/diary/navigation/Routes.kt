package com.ix.diary.navigation

enum class Routes(val route: String) {
    Root("root"),
    Main("main"),

    Authentication("authentication"),
    Diary("diary"),
    Entry("entry?id={id}"),

}
