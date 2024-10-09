package com.example.sdmw.Repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardsRepository(context: Context) {

    private val cardsDao = AppDatabase.getDatabase(context).cardDao()

    suspend fun getAllCards(): List<Card> {
        return withContext(Dispatchers.IO) {
            cardsDao.getAllCards()
        }
    }

    suspend fun upsertCard(card: Card) {
        withContext(Dispatchers.IO) {
            cardsDao.upsertCard(card)
        }
    }

    suspend fun deleteCard(card: Card) {
        withContext(Dispatchers.IO) {
            cardsDao.deleteCard(card)
        }
    }

    suspend fun getCardById(id: Int): Card? {
        return cardsDao.getCardById(id)
    }

}