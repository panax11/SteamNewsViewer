package com.example.steamnewsrssviewer.steamappdb

import androidx.room.*
import com.example.steamnewsrssviewer.steamappdata.App

@Dao
interface RoomSteamAppDao {
    @Query("select * from orm_steam_app")
    fun getAll(): List<RoomSteamApp>

    @Query("select * from orm_steam_app where title like :title ")
    fun getSteamAppByTitle(title: String): List<RoomSteamApp>

    @Query("select count(*) from orm_steam_app")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(steamApp: RoomSteamApp)

    @Transaction
    fun insertAll(steamApps: List<App>){
        for(data in steamApps){
            insert(RoomSteamApp(data.appid, data.name))
        }
    }

    @Delete
    fun delete(steamApp: RoomSteamApp)
}