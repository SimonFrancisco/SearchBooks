package francisco.simon.searchbooks.domain.bookDetails.entity

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: String,
    val author: List<String>,
    val isFavourite: Boolean,
    val imageUrl: String?,
    val title: String,
    val description: String
) : Parcelable {

    companion object {
        val navigationType: NavType<Book> = object : NavType<Book>(false) {
            override fun get(bundle: Bundle, key: String): Book? {
                return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    bundle.getParcelable(key)
                } else {
                    bundle.getParcelable(key, Book::class.java)
                }
            }
            override fun parseValue(value: String): Book {
                return Gson().fromJson(value, Book::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: Book) {
                bundle.putParcelable(key, value)
            }
        }
    }
}
