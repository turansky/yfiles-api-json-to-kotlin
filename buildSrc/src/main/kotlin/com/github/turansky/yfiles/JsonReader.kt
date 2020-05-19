package com.github.turansky.yfiles

import com.github.turansky.yfiles.correction.*
import com.github.turansky.yfiles.json.strictRemove
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.nio.charset.StandardCharsets.UTF_8

internal fun File.readJson(): JSONObject =
    readText(UTF_8)
        .run { substring(indexOf("{")) }
        .fixInsetsDeclaration()
        .run { JSONObject(this) }

internal fun File.readApiJson(action: JSONObject.() -> Unit): JSONObject =
    readJson()
        .apply { removeNamespaces() }
        .apply { fixInsetsDeclaration() }
        .apply { mergeDeclarations() }
        .toString()
        .fixSystemPackage()
        .fixClassDeclaration()
        .run { JSONObject(this) }
        .apply(action)
        .apply { fixFunctionSignatures() }
        .toString()
        .run { JSONObject(this) }

private fun String.fixSystemPackage(): String =
    replace("\"yfiles.system.", "\"yfiles.lang.")
        .replace("\"system.", "\"yfiles.lang.")

private fun String.fixClassDeclaration(): String =
    replace(""""id":"yfiles.lang.Class"""", """"id":"$YCLASS","es6name":"Class"""")
        .replace(""""name":"Class"""", """"name":"YClass"""")
        .replace(""""yfiles.lang.Class"""", """"$YCLASS"""")
        .replace(""""Array<yfiles.lang.Class>"""", """"Array<$YCLASS>"""")
        .replace(""""yfiles.collections.Map<yfiles.lang.Class,$JS_OBJECT>"""", """"yfiles.collections.Map<$YCLASS,$JS_OBJECT>"""")

private fun String.fixInsetsDeclaration(): String =
    replace("yfiles.algorithms.Insets", "yfiles.algorithms.YInsets")

private fun JSONObject.fixInsetsDeclaration() =
    flatMap(TYPES)
        .firstOrNull { it[ID] == "yfiles.algorithms.YInsets" }
        ?.also { it[NAME] = "YInsets" }

private fun JSONObject.mergeDeclarations() {
    flatMap(TYPES)
        .forEach { it.mergeProperties() }
}

private fun JSONObject.mergeProperties() {
    if (!has(STATIC_PROPERTIES)) {
        return
    }

    if (has(PROPERTIES)) {
        val properties = get(PROPERTIES)
        flatMap(STATIC_PROPERTIES).forEach { properties.put(it) }
    } else {
        set(PROPERTIES, get(STATIC_PROPERTIES))
    }

    strictRemove(STATIC_PROPERTIES)
}

private fun JSONObject.removeNamespaces() {
    val types = flatMap(NAMESPACES)
        .flatMap { it.optFlatMap(NAMESPACES).flatMap(TYPES) + it.optFlatMap(TYPES) }
        .toList()

    set(TYPES, types)
}

private fun JSONObject.fixFunctionSignatures() {
    val signatureMap = getJSONObject("functionSignatures")
    val signatures = JSONArray()
    signatureMap.keySet().forEach { key ->
        signatures.put(
            signatureMap.getJSONObject(key)
                .also { it.put("id", key) }
        )
    }

    put("functionSignatures", signatures)
}
