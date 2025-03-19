package francisco.simon.searchbooks.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import francisco.simon.searchbooks.core.data.dataSource.local.db.BookDao
import francisco.simon.searchbooks.core.data.dataSource.local.db.BookDataBase
import francisco.simon.searchbooks.core.data.dataSource.network.api.ApiFactory
import francisco.simon.searchbooks.core.data.dataSource.network.api.ApiService
import francisco.simon.searchbooks.data.favouriteBooks.repository.FavouriteBooksRepositoryImpl
import francisco.simon.searchbooks.data.searchBooks.repository.SearchBookRepositoryImpl
import francisco.simon.searchbooks.domain.favouriteBooks.repository.FavouriteBookRepository
import francisco.simon.searchbooks.domain.searchBook.repository.SearchBookRepository

@Module
interface DataModule {

    @[Binds ApplicationScope]
    fun bindSearchBookRepository(impl: SearchBookRepositoryImpl): SearchBookRepository

    @[Binds ApplicationScope]
    fun bindFavouriteBooksRepository(impl: FavouriteBooksRepositoryImpl): FavouriteBookRepository

    companion object {

        @[Provides ApplicationScope]
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @[Provides ApplicationScope]
        fun providesBookDataBase(context: Context): BookDataBase {
            return BookDataBase.getInstance(context)
        }

        @[Provides ApplicationScope]
        fun provideBookDao(dataBase: BookDataBase): BookDao {
            return dataBase.bookDao()
        }
    }

}