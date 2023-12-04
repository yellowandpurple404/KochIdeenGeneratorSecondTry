package com.example.kochideengenerator.data.datasource

import android.net.ipsec.ike.IkeDerAsn1DnIdentification
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kochideengenerator.domain.model.Idea
import kotlinx.coroutines.flow.Flow

@Dao
interface IdeasDao {
    @Query("SELECT*FROM idea")
    fun getIdeas():Flow<List<Idea>>

    @Query("DELETE FROM idea")
    fun deleteAll()

    @Query("SELECT*FROM idea")
    fun getListOfIdeas():List<Idea>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIdeasList(ideasList: List<Idea>)
}