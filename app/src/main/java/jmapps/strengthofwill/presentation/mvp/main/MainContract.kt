package jmapps.strengthofwill.presentation.mvp.main

import android.text.SpannableStringBuilder

interface MainContract {

    interface MainView {

        fun showDatabaseExceptions(e: String)

        fun showParagraph(paragraph: String)

        fun showChapterTitle(title: String)

        fun showChapterContent(content: String)

        fun showFavoriteState(state: Boolean)

        fun saveFavoriteNumber(keyFavorite: String, state: Boolean)
    }

    interface MainPresenter {

        fun showContentFromDatabase()

        fun stringBuilder(str: String): SpannableStringBuilder

        fun addRemoveFavorite(isChecked: Boolean)
    }
}