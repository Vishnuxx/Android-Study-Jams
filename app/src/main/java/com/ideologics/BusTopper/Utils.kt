package com.ideologics.BusTopper

import android.content.Context
import android.content.SharedPreferences

class Utils {
    companion object{
        fun putString(context : Context , key : String , value : String) {
            val shared : SharedPreferences = context.getSharedPreferences("App" , Context.MODE_PRIVATE)
            val edit = shared.edit()
            edit.putString(key , value)
            edit.commit()
        }
        fun putNumber(context : Context , key : String , value : Int) {
            val shared : SharedPreferences = context.getSharedPreferences("App" , Context.MODE_PRIVATE)
            val edit = shared.edit()
            edit.putInt(key , value)
            edit.commit()
        }

        fun putBoolean(context : Context , key : String , value : Boolean) {
            val shared : SharedPreferences = context.getSharedPreferences("App" , Context.MODE_PRIVATE)
            val edit = shared.edit()
            edit.putBoolean(key , value)
            edit.commit()
        }

        fun getString(context: Context , key : String) : String? {
            val shared = context.getSharedPreferences("App",Context.MODE_PRIVATE);
            return shared.getString( key!! , null)
        }

        fun getInt(context: Context , key : String) : Int? {
            val shared = context.getSharedPreferences("App",Context.MODE_PRIVATE);
            return shared.getInt( key!! , -1)
        }

        fun getBoolean(context: Context , key : String) : Boolean? {
            val shared = context.getSharedPreferences("App",Context.MODE_PRIVATE);
            return shared.getBoolean( key!! , false )
        }

        fun hasAlreadyCreatedAccount(context: Context) : Boolean {
            return Utils.getBoolean(context , "isLoggedIn") == true || Utils.getString(context , "userMode")  != "null"
        }

    }
}