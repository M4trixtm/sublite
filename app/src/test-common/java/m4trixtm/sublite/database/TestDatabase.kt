package m4trixtm.sublite.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import m4trixtm.sublite.core.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
abstract class TestDatabase {

    lateinit var db: AppDatabase

    @Before
    fun createDB() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries().build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}