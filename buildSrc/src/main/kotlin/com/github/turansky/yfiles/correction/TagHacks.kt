package com.github.turansky.yfiles.correction

import com.github.turansky.yfiles.GeneratorContext
import com.github.turansky.yfiles.JS_ANY
import com.github.turansky.yfiles.JS_OBJECT
import org.json.JSONObject

internal const val TAG = "yfiles.graph.Tag"

internal fun generateTagUtils(context: GeneratorContext) {
    // language=kotlin
    context[TAG] =
        """
            |package yfiles.graph
            |
            |external interface Tag
            |
            |fun Tag(source:Any):Tag = 
            |    source.unsafeCast<Tag>()
        """.trimMargin()
}

internal fun applyTagHacks(source: Source) {
    val likeObjectTypes = setOf(
        JS_OBJECT,
        JS_ANY
    )

    typedItems(source)
        .filter { looksLikeTag(it[NAME]) }
        .filter { it[TYPE] in likeObjectTypes }
        .forEach { it[TYPE] = TAG }
}

private fun looksLikeTag(name: String): Boolean =
    name == "tag" || (name.endsWith("Tag") && name != "styleTag")

private fun typedItems(source: Source): Sequence<JSONObject> =
    source.types()
        .filterNot { it[NAME] == "GraphMLIOHandler" }
        .flatMap {
            (it.optFlatMap(CONSTRUCTORS) + it.optFlatMap(METHODS) + it.optFlatMap(STATIC_METHODS))
                .optFlatMap(PARAMETERS)
                .plus(it.optFlatMap(PROPERTIES))
        }
