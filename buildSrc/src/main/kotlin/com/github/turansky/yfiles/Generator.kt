package com.github.turansky.yfiles

import com.github.turansky.yfiles.correction.applyHacks
import com.github.turansky.yfiles.correction.correctNumbers
import org.json.JSONObject
import java.io.File
import java.net.URL

private val YFILES_NAMESPACE = "yfiles"

private val EXCLUDED_TYPES = sequenceOf(
    "yfiles.lang.Struct",

    "yfiles.lang.AttributeDefinition",

    "yfiles.lang.Enum",
    "yfiles.lang.EnumDefinition",

    "yfiles.lang.Interface",
    "yfiles.lang.InterfaceDefinition",

    "yfiles.lang.ClassDefinition",

    "yfiles.lang.delegate",
    "yfiles.lang.Exception",
    "yfiles.lang.Trait"
)
    .map(::fixPackage)
    .toSet()

private fun loadApiJson(path: String): String {
    return URL(path)
        .readText(DEFAULT_CHARSET)
        .run { substring(indexOf("{")) }
}

fun generateKotlinWrappers(apiPath: String, sourceDir: File) {
    generateWrappers(
        apiPath = apiPath,
        sourceDir = sourceDir,
        createFileGenerator = ::KotlinFileGenerator
    )
}

private fun generateWrappers(
    apiPath: String,
    sourceDir: File,
    createFileGenerator: (types: Iterable<Type>, functionSignatures: Iterable<FunctionSignature>) -> FileGenerator
) {
    val source = JSONObject(loadApiJson(apiPath))

    applyHacks(source)
    correctNumbers(source)

    val apiRoot = ApiRoot(source)
    val types = apiRoot
        .namespaces
        .asSequence()
        .first { it.id == YFILES_NAMESPACE }
        .namespaces
        .flatMap { it.types }
        .filterNot { EXCLUDED_TYPES.contains(it.fqn) }

    val functionSignatures = apiRoot.functionSignatures

    ClassRegistry.instance = ClassRegistry(types)

    val fileGenerator = createFileGenerator(types, functionSignatures.values)
    fileGenerator.generate(sourceDir)
}