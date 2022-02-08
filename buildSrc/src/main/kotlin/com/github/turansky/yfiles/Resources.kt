package com.github.turansky.yfiles

import com.github.turansky.yfiles.ContentMode.CLASS
import java.io.File

internal fun generateResourceTypes(
    devguide: File,
    context: GeneratorContext,
) {
    val keyDeclarations = devguide
        .readText()
        .substringAfter("""id:"tab_resource_defaults"""")
        .substringAfter("<tbody ")
        .substringBefore("</tbody>")
        .splitToSequence("</para>")
        .map { it.substringAfterLast(">") }
        .chunked(2)
        .filter { it.size == 2 }
        .map { constDeclaration(it.first(), it.last()) }
        .joinToString(separator = "\n\n", postfix = "\n")

    // language=kotlin
    context["yfiles.lang.Hotkey"] = """
        @JsName("String")
        external class Hotkey
        private constructor()
        
        inline fun Hotkey(source:String):Hotkey = 
            source.unsafeCast<Hotkey>()
    """.trimIndent()

    // language=kotlin
    context["yfiles.lang.ResourceKey"] = """
        @JsName("String")
        external class ResourceKey<T:Any>
        private constructor()
        
        inline fun <T:Any> ResourceKey(source:String):ResourceKey<T> = 
            source.unsafeCast<ResourceKey<T>>()
        
        object ResourceKeys {
            $keyDeclarations
        }
    """.trimIndent()

    // language=kotlin
    context["yfiles.lang.ResourceMap"] = """
            @JsName("Object")
            external class ResourceMap
            private constructor()
            
            inline fun ResourceMap(block: (ResourceMap) -> Unit):ResourceMap { 
                val map: ResourceMap = js("({})")
                return map.also(block)
            }
            
            inline operator fun <T: Any> ResourceMap.get(key: ResourceKey<T>):T? {
                return asDynamic()[key]
            }
            
            inline fun <T: Any> ResourceMap.getValue(key: ResourceKey<T>):T? {
                return requireNotNull(get(key))
            }

            inline operator fun <T: Any> ResourceMap.set(
               key: ResourceKey<T>, 
               value: T
            ) {
                asDynamic()[key] = value
            }
        """.trimIndent()

    // language=kotlin
    context["yfiles.lang.Resources", CLASS] =
        """
            @JsName("resources")
            external object Resources {
                val invariant: ResourceMap
            }

            inline operator fun Resources.get(locale: String):ResourceMap? {
                return asDynamic()[locale]
            }
            
            inline fun Resources.getOrCreate(locale: String):ResourceMap {
                return get(locale) 
                    ?: ResourceMap { set(locale, it) }
            }
            
            inline operator fun Resources.set(
                locale: String,
                value: ResourceMap
            ) {
                asDynamic()[locale] = value
            }
        """.trimIndent()
}

private fun constDeclaration(
    key: String,
    defaultValue: String,
): String {
    val name = key
        .replace(Regex("([a-z])([A-Z])"), "$1_$2")
        .replace(".", "__")
        .toUpperCase()

    return if (key.endsWith("Key")) {
        hotkeyDeclaration(key, name, defaultValue)
    } else {
        keyDeclaration(key, name, defaultValue)
    }
}

private fun keyDeclaration(
    key: String,
    name: String,
    defaultValue: String,
): String =
    // language=kotlin
    """
        /**
         * Default value - "$defaultValue"
         */
        inline val $name: ResourceKey<String>
            get() = ResourceKey("$key")
    """.trimIndent()

private fun hotkeyDeclaration(
    key: String,
    name: String,
    defaultValue: String,
): String =
    // language=kotlin
    """
        /**
         * Default hotkey - `$defaultValue`
         */
        inline val $name: ResourceKey<Hotkey>
            get() = ResourceKey("$key")
    """.trimIndent()
