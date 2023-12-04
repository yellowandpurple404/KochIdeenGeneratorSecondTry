package com.example.kochideengenerator.data.datasource

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kochideengenerator.domain.model.Idea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Idea::class],
    version = 1
)
abstract class IdeasDatabase : RoomDatabase() {
    abstract val ideasDao: IdeasDao

    companion object {
        @Volatile
        private var INSTANCE: IdeasDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): IdeasDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, IdeasDatabase::class.java, "user_database"
                )
                    // Wipes and rebuilds instead of migrating if no M¬igration object.
                    .fallbackToDestructiveMigration()
                    .addCallback(IdeasDatabaseCallback(scope))
                    .build()
                Log.d("Andreas","Hello from after build")
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class IdeasDatabaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.d("Andreas", "Hello from after onOpen")
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.ideasDao)
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * This is where more UserEntity data could be added to prime the database.
         */
        fun populateDatabase(ideasDao: IdeasDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            ideasDao.deleteAll()

            // Device Items Pre-load
            if (ideasDao.getListOfIdeas().isEmpty()) {
                ideasDao.deleteAll()

                // Create list of devices
                val ideas = mutableListOf<Idea>()
                ideas.add(Idea("Dream", "HTC", "", 0))
                ideas.add(
                    Idea(
                        "Droid", "Motorola", "dd", 4
                    )
                )

            }

        }

    }
}