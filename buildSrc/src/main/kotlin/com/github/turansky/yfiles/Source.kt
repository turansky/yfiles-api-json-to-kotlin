package com.github.turansky.yfiles

import com.github.turansky.yfiles.correction.J_METHODS
import com.github.turansky.yfiles.correction.jsequence
import com.github.turansky.yfiles.correction.optionalArray
import org.json.JSONObject

internal class Source(api: JSONObject) {
    private val types = api
        .jsequence("namespaces")
        .optionalArray("namespaces")
        .jsequence("types")

    private val typeMap = types.associateBy { it.uid }

    fun types() = types.asSequence()
    fun type(className: String) = typeMap.getValue(className)

    fun allMethods(vararg methodNames: String): Sequence<JSONObject> =
        types.asSequence()
            .map { it.optionalArray(J_METHODS) + it.optionalArray("staticMethods") }
            .flatMap { it.asSequence() }
            .filter { it.getString("name") in methodNames }

    private val JSONObject.uid: String
        get() = if (has("es6name")) {
            getString("es6name")
        } else {
            getString("name")
        }
}