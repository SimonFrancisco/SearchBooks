package francisco.simon.searchbooks.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import francisco.simon.searchbooks.presentation.bookDetails.BookDetailsViewModel

@Module
interface BookDetailsViewModelModule {

    @[Binds IntoMap ViewModelKey(BookDetailsViewModel::class)]
    fun bindBookDetailsViewModel(viewModel: BookDetailsViewModel):ViewModel

}