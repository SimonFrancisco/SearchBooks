package francisco.simon.searchbooks.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import francisco.simon.searchbooks.presentation.searchBooks.components.SearchBookScreen
import francisco.simon.searchbooks.ui.theme.SearchBooksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchBooksTheme {
                MainScreen()
            }
        }
    }
}


