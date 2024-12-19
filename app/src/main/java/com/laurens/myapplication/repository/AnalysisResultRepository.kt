package com.laurens.myapplication.repository

import androidx.annotation.WorkerThread
import com.laurens.myapplication.database.AnalysisResult
import com.laurens.myapplication.database.AnalysisResultDao
import kotlinx.coroutines.flow.Flow

class AnalysisResultRepository(private val analysisResultDao: AnalysisResultDao) {

    val allResults: Flow<List<AnalysisResult>> = analysisResultDao.getAll()

    @WorkerThread
    suspend fun delete(analysisResult: AnalysisResult) {
        analysisResultDao.delete(analysisResult)
    }
}