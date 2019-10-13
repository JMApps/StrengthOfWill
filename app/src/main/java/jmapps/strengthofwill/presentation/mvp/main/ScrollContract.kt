package jmapps.strengthofwill.presentation.mvp.main

import android.content.SharedPreferences

interface ScrollContract {

    interface ScrollView {


    }

    interface ScrollPresenter {

        fun scrollCount()

        fun saveLastCount(editor: SharedPreferences.Editor)

        fun loadLastCount(preferences: SharedPreferences)
    }
}