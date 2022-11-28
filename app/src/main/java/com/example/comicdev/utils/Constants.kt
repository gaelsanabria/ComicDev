package com.example.comicdev.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val PUBLIC_KEY = "f05728d663912d0c996a3ece5921c3b4"
        private const val PRIVATE_KEY = "3f279b9ad1035a437740e188a999dfe7289bebdc"

        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$PUBLIC_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }

    object AppModuleKey {
        const val API_KEY = "apikey"
        const val TIMESTAMP = "ts"
        const val HASH_KEY = "hash"
    }

    object DbConstant {
        const val DB_NAME = "marvel_database"
        const val TABLE_NAME = "character_table"
        const val DB_VERSION = 1
    }

    object CharacterConstant {
        const val NO_DESCRIPTION_AVAILABLE = "No Description Available"
        const val COMICS = "Comics:"
        const val SERIES = "Series:"
    }
}