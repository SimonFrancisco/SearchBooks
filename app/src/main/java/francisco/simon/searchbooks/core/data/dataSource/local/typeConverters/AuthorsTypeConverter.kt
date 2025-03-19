package francisco.simon.searchbooks.core.data.dataSource.local.typeConverters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AuthorsTypeConverter @Inject constructor() {

    @TypeConverter
    fun jsonToList(authors: List<String>): String {
        return Json.encodeToString(authors)
    }

    @TypeConverter
    fun toTags(authors: String): List<String> {
        return Json.decodeFromString<List<String>>(authors)
    }
}