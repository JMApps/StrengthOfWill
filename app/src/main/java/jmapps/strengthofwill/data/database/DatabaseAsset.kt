package jmapps.strengthofwill.data.database

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

private var databaseVersion = 1

class DatabaseAsset(context: Context?) :
    SQLiteAssetHelper(context, "StrengthDB", null, databaseVersion) {
    init {
        setForcedUpgrade(databaseVersion)
    }
}