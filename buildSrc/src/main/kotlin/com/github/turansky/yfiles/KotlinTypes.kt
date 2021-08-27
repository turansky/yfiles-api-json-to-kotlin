package com.github.turansky.yfiles

internal val UNIT = Unit::class.simpleName!!
internal val ANY = Any::class.simpleName!!

internal val STRING: String = String::class.simpleName!!
internal val INT: String = Int::class.simpleName!!
internal val DOUBLE: String = Double::class.simpleName!!
internal val BOOLEAN: String = Boolean::class.simpleName!!

internal const val PROMISE = "kotlin.js.Promise"
internal const val READ_ONLY_PROPERTY = "kotlin.properties.ReadOnlyProperty"
internal const val READ_WRITE_PROPERTY = "kotlin.properties.ReadWriteProperty"
internal const val KCLASS = "kotlin.reflect.KClass"
internal const val KPROPERTY = "kotlin.reflect.KProperty"

internal const val BLOB = "org.w3c.files.Blob"

internal const val ELEMENT = "org.w3c.dom.Element"
internal const val HTML_ELEMENT = "org.w3c.dom.HTMLElement"
internal const val SVG_ELEMENT = "org.w3c.dom.svg.SVGElement"
internal const val SVG_SVG_ELEMENT = "org.w3c.dom.svg.SVGSVGElement"

internal const val WEBGL2_RENDERING_CONTEXT = "org.khronos.webgl.WebGL2RenderingContext"

internal fun getKotlinType(type: String): String? =
    STANDARD_TYPE_MAP[type]

private val STANDARD_TYPE_MAP = mapOf(
    JS_VOID to "Nothing?",

    JS_ANY to ANY,
    JS_OBJECT to ANY,
    JS_BOOLEAN to "Boolean",
    JS_STRING to STRING,
    JS_NUMBER to "Number",
    "Date" to "kotlin.js.Date",
    "Function" to "() -> $UNIT",

    "Event" to "org.w3c.dom.events.Event",
    "KeyboardEvent" to "org.w3c.dom.events.KeyboardEvent",

    "Document" to "org.w3c.dom.Document",
    "Node" to "org.w3c.dom.Node",
    JS_ELEMENT to ELEMENT,
    "HTMLElement" to HTML_ELEMENT,
    "HTMLInputElement" to "org.w3c.dom.HTMLInputElement",
    "HTMLDivElement" to "org.w3c.dom.HTMLDivElement",

    "ImageData" to "org.w3c.dom.ImageData",
    "CanvasRenderingContext2D" to "org.w3c.dom.CanvasRenderingContext2D",

    JS_SVG_ELEMENT to SVG_ELEMENT,
    JS_SVG_DEFS_ELEMENT to "org.w3c.dom.svg.SVGDefsElement",
    "SVGGElement" to "org.w3c.dom.svg.SVGGElement",
    "SVGImageElement" to "org.w3c.dom.svg.SVGImageElement",
    "SVGPathElement" to "org.w3c.dom.svg.SVGPathElement",
    "SVGTextElement" to "org.w3c.dom.svg.SVGTextElement",
    JS_SVG_SVG_ELEMENT to SVG_SVG_ELEMENT,

    "WebGLProgram" to "org.khronos.webgl.WebGLProgram",
    "WebGLRenderingContext" to "org.khronos.webgl.WebGLRenderingContext",
    "WebGL2RenderingContext" to WEBGL2_RENDERING_CONTEXT,

    JS_BLOB to BLOB,

    "Promise" to PROMISE,
    JS_CLASS to "kotlin.js.JsClass"
)

val STANDARD_IMPORTED_TYPES = STANDARD_TYPE_MAP
    .values
    .asSequence()
    .filter { "." in it }
    .plus(READ_ONLY_PROPERTY)
    .plus(READ_WRITE_PROPERTY)
    .plus(KCLASS)
    .plus(KPROPERTY)
    .toSet()
