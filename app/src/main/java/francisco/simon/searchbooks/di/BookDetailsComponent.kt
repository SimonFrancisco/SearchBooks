package francisco.simon.searchbooks.di

import dagger.Subcomponent
import francisco.simon.searchbooks.presentation.ViewModelFactory

@Subcomponent
interface BookDetailsComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
        ): BookDetailsComponent
    }
}