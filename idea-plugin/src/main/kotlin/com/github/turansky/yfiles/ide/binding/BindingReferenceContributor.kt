package com.github.turansky.yfiles.ide.binding

import com.github.turansky.yfiles.ide.binding.BindingDirective.BINDING
import com.github.turansky.yfiles.ide.binding.BindingDirective.TEMPLATE_BINDING
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
        context: ProcessingContext
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
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (token) {
                BindingToken.KEYWORD -> when (directive) {
                    BINDING,
                    TEMPLATE_BINDING
                    -> result += ClassReference(
                        element = element,
                        rangeInElement = range.shiftRight(valueOffset),
                        className = binding.parentReference
                    )
                }

                BindingToken.ARGUMENT -> when (directive) {
                    TEMPLATE_BINDING
                    -> result += ContextPropertyReference(
                        element = element,
                        rangeInElement = range.shiftRight(valueOffset),
                        property = (binding as TemplateBinding).property
                    )
                }
            }
        }

        return result.toTypedArray()
    }
}
