package com.brazhnik.fobres.data.database.room.repository

import android.content.Context
import android.util.Log
import com.brazhnik.fobres.data.database.repository.TokenEventRepository
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.database.room.dao.TokenEventDao
import com.brazhnik.fobres.data.database.room.entity.TokenEventEntity
import com.brazhnik.fobres.data.model.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RoomTokenEventRepository {
    companion object : TokenEventRepository {

        var tokenDatabase: FobresDatabase? = null

        fun initializeDB(context: Context): FobresDatabase {
            return FobresDatabase.getDatabaseFobres(context)
        }

        override suspend fun saveToken(token: Token) {
            CoroutineScope(IO).launch {
                tokenDatabase!!.tokenDao().saveToken(TokenEventEntity(token.login, token.token))
            }
        }

        override suspend fun getToken(): Token {
            return try {
                val tokenDao = tokenDatabase!!.tokenDao().getToken()
                Token(login = tokenDao.login, token = tokenDao.token)
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for Token is empty!")
                Token(null.toString(), "404") // TODO Maybe it is not good solve
            }
        }

        override suspend fun deleteToken(token: Token) {
            try {
                tokenDatabase!!.tokenDao().deleteToken(token.token)
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for Token is empty!")
                throw ex
            }
        }

    }
}