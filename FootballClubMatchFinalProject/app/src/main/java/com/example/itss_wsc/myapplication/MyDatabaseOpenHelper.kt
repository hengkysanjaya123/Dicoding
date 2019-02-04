package com.example.itss_wsc.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.itss_wsc.myapplication.Model.Favorite
import com.example.itss_wsc.myapplication.Model.TeamFavorite
import org.jetbrains.anko.db.*


class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 2) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENTID to TEXT + UNIQUE
            ,
            "DATE" to TEXT,
            "HOMETEAM" to TEXT,
            "HOMESCORE" to TEXT,
            "AWAYTEAM" to TEXT,
            "AWAYSCORE" to TEXT
        )

        db.createTable(
            TeamFavorite.TABLE_TEAM_FAVORITE, true,
            TeamFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavorite.TEAMID to TEXT + UNIQUE,
            "TEAMNAME" to TEXT,
            "TEAMBADGE" to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(TeamFavorite.TABLE_TEAM_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)