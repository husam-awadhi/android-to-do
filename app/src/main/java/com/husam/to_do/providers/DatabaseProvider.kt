package com.husam.to_do.providers

import android.content.Context
import android.widget.Toast
import io.realm.Realm
import io.realm.Realm.getInstance
import io.realm.Realm.init
import io.realm.RealmConfiguration

class DatabaseProvider {

    private lateinit var realm: Realm

    fun initDatabase(context: Context) {
        try {
            init(context)
        } catch (e: Exception) {
            Toast.makeText(context, "Failed at initiating Database", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDatabaseInstance(): Realm {
        // Open the realm for the UI thread.
        return if (!this::realm.isInitialized) {
            val config: RealmConfiguration = this.getDatabaseConfigs()
            getInstance(config).also { realm = it }
        } else realm
    }

    private fun getDatabaseConfigs(): RealmConfiguration {
        return RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .name("aHVzYW1Bd2FkaGk.db")
            //add config here
            .build()
    }
}
