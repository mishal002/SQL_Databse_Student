package com.example.sqldatabse.util

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
) : SQLiteOpenHelper(context, "RNW.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE student (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",name TEXT" +
                ",mobile TEXT" +
                ",std TEXT)";
        db!!.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //    Insert Data
    fun insertData(n1: String, m1: String, s1: String) {

        val db = writableDatabase
        val cv = ContentValues()
        cv.put("name", n1)
        cv.put("mobile", m1)
        cv.put("std", s1)
        var res = db.insert("student", null, cv)

        println(res)

    }

    //   Read Data
    @SuppressLint("Range")
    fun readData(): ArrayList<ModelClass> {
        val list = arrayListOf<ModelClass>()
        val db = readableDatabase
        val query = " SELECT * FROM student"
        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                val id = cursor.getString(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val mobile = cursor.getString(cursor.getColumnIndex("mobile"))
                val std = cursor.getString(cursor.getColumnIndex("std"))

                val m1 = ModelClass(id, name, mobile, std)
                list.add(m1)

            } while (cursor.moveToNext())
        }

        return list


    }

    fun deleteData(id: String) {
        var db = writableDatabase

        db.delete("student", "id=$id", null)

    }

    fun updateData(id: String, name: String, mobile: String, std: String) {
        var db = writableDatabase

        val cv = ContentValues()

        cv.put("name", name)
        cv.put("mobile", mobile)
        cv.put("std", std)

        db.update("student", cv, "id = $id", null)
    }
}