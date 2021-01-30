package m4trixtm.sublite.core.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import m4trixtm.sublite.features.subtitle.entity.SubtitleRelatedLinks
import java.lang.reflect.Type
import kotlin.reflect.KClass

abstract class ArrayOrSingleObjectTypeAdapter<TypedList : List<Element>, Element : Any>(
    private val elementKClass: KClass<Element>
) : JsonDeserializer<TypedList> {
    override fun deserialize(
        json: JsonElement, typeOfT: Type?, ctx: JsonDeserializationContext
    ): TypedList = when {
        json.isJsonArray -> json.asJsonArray.map {
            ctx.deserialize<Element>(
                it,
                elementKClass.java
            )
        }
        json.isJsonObject -> listOf(ctx.deserialize<Element>(json, elementKClass.java))
        else -> throw RuntimeException("Unexpected JSON type: " + json.javaClass)
    }.toTypedList()

    abstract fun List<Element>.toTypedList(): TypedList
}

class MultiSubtitleRelatedLinksArrayOrObject
    : ArrayOrSingleObjectTypeAdapter<List<SubtitleRelatedLinks>, SubtitleRelatedLinks>(
    SubtitleRelatedLinks::class
) {
    override fun List<SubtitleRelatedLinks>.toTypedList() = listOf(*this.toTypedArray())
}