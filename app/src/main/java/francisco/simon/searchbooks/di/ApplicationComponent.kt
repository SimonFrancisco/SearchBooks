package francisco.simon.searchbooks.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import francisco.simon.searchbooks.presentation.ViewModelFactory

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory
    fun getBookDetailsFactory(): BookDetailsComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}