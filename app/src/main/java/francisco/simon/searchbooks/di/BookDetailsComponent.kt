package francisco.simon.searchbooks.di

import dagger.BindsInstance
import dagger.Subcomponent
import francisco.simon.searchbooks.domain.bookDetails.entity.Book
import francisco.simon.searchbooks.presentation.ViewModelFactory

@Subcomponent(modules = [BookDetailsViewModelModule::class])
interface BookDetailsComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance book: Book
        ): BookDetailsComponent
    }
}