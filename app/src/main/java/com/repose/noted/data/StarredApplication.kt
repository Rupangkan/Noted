package com.repose.noted.data

import android.app.Application

class StarredApplication : Application() {
    val database: StarredRoomDatabase by lazy { StarredRoomDatabase.getDatabase(this) }
}