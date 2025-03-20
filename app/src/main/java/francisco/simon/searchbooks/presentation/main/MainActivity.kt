package francisco.simon.searchbooks.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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


