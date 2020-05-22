package com.github.turansky.yfiles

internal fun summary(source: String): String {
    return source
        .fixApiLinks()
        .fixMarkdown()
}

private val TYPE_CLEAN_REGEX_1 = Regex("( data-type=\"[a-zA-Z.]+)<[^\"]+")
private val TYPE_CLEAN_REGEX_2 = Regex("( data-type=\"[a-zA-Z.]+)&lt;[^\"]+")

private val TYPE_REGEX = Regex("<api-link data-type=\"([a-zA-Z0-9.]+)\"></api-link>")
private val TYPE_TEXT_REGEX = Regex("<api-link data-type=\"([a-zA-Z0-9.]+)\" data-text=\"([^\"]+)\"></api-link>")

private val MEMBER_REGEX = Regex("<api-link data-type=\"([a-zA-Z.]+)\" data-member=\"([a-zA-Z0-9_]+)\"></api-link>")
private val MEMBER_TEXT_REGEX = Regex("<api-link data-type=\"([a-zA-Z.]+)\" data-member=\"([a-zA-Z0-9_]+)\" data-text=\"([^\"]+)\"></api-link>")

private val DIGIT_CLEAN_REGEX = Regex("(\\.[0-9]+)d")

private fun String.fixApiLinks(): String {
    return this
        .replace(" data-text=\"\"", "")
        .replace(" infinite\"\"=\"\"", "")
        .replace(TYPE_CLEAN_REGEX_1, "$1")
        .replace(TYPE_CLEAN_REGEX_2, "$1")
        .replace(TYPE_REGEX, "[$1]")
        .replace(TYPE_TEXT_REGEX, "[$2][$1]")
        .replace(MEMBER_REGEX, "[$1.$2]")
        .replace(MEMBER_TEXT_REGEX, "[$3][$1.$2]")
        .replace("[$JS_OBJECT.", "[$YOBJECT.")
        .replace("[yfiles.lang.Object.", "[$YOBJECT.")
        .replace("[$JS_STRING]", "[$STRING]")
        .replace("[$JS_NUMBER]", "[Number]")
        .replace("[$JS_NUMBER.", "[$DOUBLE.")
        .replace("[Number.", "[$DOUBLE.")
        .replace("[$JS_BOOLEAN]", "[$BOOLEAN]")
        .replace("more NaN values", "more `NaN` values")
        .replace(">evt<", ">event<")
        .replace(">evt.", ">event.")
        .replace("&apos;", "'")
        .replace("&quot;", "\"")
        .also { check("<api-link" !in it) }
}

private val ENCODED_GENERIC_START = Regex("(<code>[^<]*)&lt;")

private fun String.fixMarkdown(): String {
    return replace(ENCODED_GENERIC_START, "$1<")
        .replace("<y-dataprovider-doc>", "A data provider key ")
        .replace("</y-dataprovider-doc>", ".")
        .replace("<y-dataacceptor-doc>", "A data acceptor key ")
        .replace("</y-dataacceptor-doc>", ".")
        .replace(" &lt;default> ", " `<default>` ")
        .replace(" &lt;img> ", " `<img>` ")
        .replace(" IEnumerable>TSource&lt; ", " `IEnumerable<TSource>` ")
        .replace("IEnumerable&lt;IEdge&gt;", "IEnumerable<IEdge>")
        .replace("<pre><code>", "\n```\n")
        .replace("</code></pre>", "\n```\n")
        .replace("<code>", "`")
        .replace("</code>", "`")
        .replace("<p>", "")
        .replace("</p>", "\n")
        .replace("<b>", "**")
        .replace("</b>", "**")
        .replace("<i>", "*")
        .replace("</i>", "*")
        .replace("<em>", "*")
        .replace("</em>", "*")
        .replace("<ul><li>", "\n$LIST_MARKER ")
        .replace("</li><li>", "\n$LIST_MARKER ")
        .replace("</li></ul>", "")
        .replace(DIGIT_CLEAN_REGEX, "$1")
}
