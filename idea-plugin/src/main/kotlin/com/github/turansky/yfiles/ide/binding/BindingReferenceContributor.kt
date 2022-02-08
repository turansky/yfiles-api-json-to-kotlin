package com.github.turansky.yfiles.ide.binding

import com.github.turansky.yfiles.ide.binding.BindingDirective.*
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.xml.XmlAttributeValue
import com.intellij.util.ProcessingContext

internal class BindingReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(XmlAttributeValue::class.java),
            BindingReferenceProvider(),
            PsiReferenceRegistrar.HIGHER_PRIORITY
        )
    }
}

private class BindingReferenceProvider : PsiReferenceProvider() {
    override fun getReferencesByElement(
        element: PsiElement,
        context: ProcessingContext,
    ): Array<out PsiReference> {
        element as XmlAttributeValue

        if (!element.bindingEnabled)
            return PsiReference.EMPTY_ARRAY

        val binding = element.value.toBinding()
            ?: return PsiReference.EMPTY_ARRAY

        val value = element.value
        val valueOffset = element.valueTextRange.startOffset - element.textRange.startOffset

        val result = mutableListOf<PsiReference>()
        BindingParser.parse(value).forEach { (token, range, directive) ->
            when (token) {
                BindingToken.KEYWORD -> when (directive) {
                    BINDING,
                    TEMPLATE_BINDING,
                    -> result += ClassReference(
                        element = element,
                        rangeInElement = range.shiftRight(valueOffset),
                        className = binding.parentReference
                    )

                    CONVERTER,
                    PARAMETER,
                    -> result += PropertyReference(
                        element = element,
                        rangeInElement = range.shiftRight(valueOffset),
                        property = Properties.TEMPLATE_CONVERTERS
                    )

                    else -> Unit
                }

                BindingToken.ARGUMENT -> when (directive) {
                    TEMPLATE_BINDING,
                    -> result += ContextPropertyReference(
                        element = element,
                        rangeInElement = range.shiftRight(valueOffset),
                        propertyName = binding.name!!
                    )

                    else -> Unit
                }

                else -> Unit
            }
        }

        return result.toTypedArray()
    }
}
