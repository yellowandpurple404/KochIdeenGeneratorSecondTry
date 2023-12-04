package com.example.kochideengenerator.data.repository

import com.example.kochideengenerator.data.datasource.IdeasDao
import com.example.kochideengenerator.domain.model.Idea
import kotlinx.coroutines.flow.Flow

class IdeasRepository(private val dao: IdeasDao) {

    fun getIdeas(): Flow<List<Idea>>{
        return dao.getIdeas()
    }

fun getListOfIdeas() = dao.getListOfIdeas()

}