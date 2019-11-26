package br.com.caelum.twittelum.bancodedados

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration1Para2 : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val sql : String = "alter table Tweet add column foto text"
        database.execSQL(sql)
    }

}
