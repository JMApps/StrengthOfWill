package jmapps.strengthofwill

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.appbar.AppBarLayout
import jmapps.strengthofwill.data.database.DatabaseAsset
import jmapps.strengthofwill.presentation.mvp.other.OtherContract
import jmapps.strengthofwill.presentation.mvp.other.OtherPresenterImpl
import jmapps.strengthofwill.presentation.ui.about.BottomSheetAboutUs
import jmapps.strengthofwill.presentation.ui.chapters.FragmentChapters
import jmapps.strengthofwill.presentation.ui.favorites.FragmentFavorites
import jmapps.strengthofwill.presentation.ui.main.SectionsPagerAdapter
import jmapps.strengthofwill.presentation.ui.settings.BottomSheetSettings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, OtherContract.OtherView,
    FragmentChapters.GetChapterItem,
    FragmentFavorites.GetFavoriteItem {

    private lateinit var database: SQLiteDatabase
    private lateinit var otherPresenter: OtherPresenterImpl

    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = preferences.edit()

        otherPresenter = OtherPresenterImpl(this, this)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mainViewPager.adapter = sectionsPagerAdapter

        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset < 0) {
                fabChapters.hide()
                fabFavorites.hide()
            } else {
                fabChapters.show()
                fabFavorites.show()
            }
        })

        fabChapters.setOnClickListener(this)
        fabFavorites.setOnClickListener(this)

        loadPosition()
        openDatabase()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.settings -> otherPresenter.getSettings()

            R.id.aboutUs -> otherPresenter.getAboutUs()

            R.id.rateApp -> otherPresenter.rateApp()

            R.id.shareLink -> otherPresenter.shareLink()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fabChapters -> otherPresenter.getListChapters()

            R.id.fabFavorites -> otherPresenter.getFavoriteList()
        }
    }

    override fun getItemPosition(position: Int) {
        mainViewPager.currentItem = position
    }

    override fun showFavoriteList() {
        FragmentFavorites().show(supportFragmentManager, "favorites")
    }

    override fun showListChapters() {
        FragmentChapters().show(supportFragmentManager, "chapters")
    }

    override fun showSettings() {
        BottomSheetSettings().show(supportFragmentManager, "settings")
    }

    override fun showAboutUs() {
        BottomSheetAboutUs().show(supportFragmentManager, "about")
    }

    override fun onStop() {
        super.onStop()
        savePosition()
        closeDatabase()
    }

    override fun onDestroy() {
        super.onDestroy()
        savePosition()
        closeDatabase()
    }

    private fun savePosition() {
        editor.putInt("key_pager_position", mainViewPager.currentItem).apply()
    }

    private fun loadPosition() {
        mainViewPager.currentItem = preferences.getInt("key_pager_position", 0)
    }

    private fun openDatabase() {
        database = DatabaseAsset(this).readableDatabase
    }

    private fun closeDatabase() {
        database.close()
    }
}
