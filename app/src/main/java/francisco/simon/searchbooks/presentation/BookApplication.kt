package francisco.simon.searchbooks.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import francisco.simon.searchbooks.di.ApplicationComponent
import francisco.simon.searchbooks.di.DaggerApplicationComponent

class BookApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as BookApplication).component
}