package com.github.turansky.yfiles.correction

import com.github.turansky.yfiles.IVISUAL_TEMPLATE
import com.github.turansky.yfiles.json.get

internal fun applyVisualTemplateHacks(source: Source) {
    source.type("IVisualTemplate").apply {
        setSingleTypeParameter("in T")

        flatMap(METHODS)
            .map { it[PARAMETERS]["dataObject"] }
            .onEach { it.changeNullability(false) }
            .forEach { it[TYPE] = "T" }
    }

    source.types().forEach {
        val className = it[NAME]
        it.optFlatMap(PROPERTIES)
            .filter { it[TYPE] == IVISUAL_TEMPLATE }
            .forEach { it[TYPE] = "$IVISUAL_TEMPLATE<${getVisualTemplateParameter(className)}>" }

        it.optFlatMap(METHODS)
            .filter { it.has(RETURNS) }
            .map { it[RETURNS] }
            .filter { it[TYPE] == IVISUAL_TEMPLATE }
            .forEach { it[TYPE] = "$IVISUAL_TEMPLATE<${getVisualTemplateParameter(className)}>" }
    }
}

internal fun getVisualTemplateParameter(className: String): String =
    when (className) {
        "HandleInputMode" -> "IHandle"
        "DefaultStripeInputVisualizationHelper" -> "IStripe"
        else -> "*"
    }
