package francisco.simon.searchbooks.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import francisco.simon.searchbooks.presentation.favouriteBooks.FavouriteBooksViewModel
import francisco.simon.searchbooks.presentation.searchBooks.SearchBookViewModel

@Module
interface ViewModelModule {

    @[Binds IntoMap ViewModelKey(SearchBookViewModel::class)]
    fun bindSearchBookViewModel(viewModel: SearchBookViewModel): ViewModel

    @[Binds IntoMap ViewModelKey(FavouriteBooksViewModel::class)]
    fun bindFavouritesBookViewModel(viewModel: FavouriteBooksViewModel): ViewModel
}