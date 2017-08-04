package com.gluigip.kotlin.kotlinchat.ui.utils

import com.vaadin.ui.*
import org.vaadin.viritin.button.MButton
import org.vaadin.viritin.fields.MTextField
import org.vaadin.viritin.label.MLabel
import org.vaadin.viritin.layouts.MFormLayout
import org.vaadin.viritin.layouts.MHorizontalLayout
import org.vaadin.viritin.layouts.MPanel
import org.vaadin.viritin.layouts.MVerticalLayout

/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
fun HasComponents.add(component: Component) = when (this) {
    is ComponentContainer -> this.addComponent(component)
    is SingleComponentContainer -> this.content = component
    is ExtendedCustomComponent -> this.content = component
    else -> throw IllegalArgumentException("Don't know how to add items to $this")
}

fun HasComponents.verticalLayout(alignment: Alignment? = null, init: MVerticalLayout.() -> Unit): MVerticalLayout {
    return align(newComponent(MVerticalLayout(), true, init), alignment)
}


fun HasComponents.horizontalLayout(alignment: Alignment? = null, init: MHorizontalLayout.() -> Unit): MHorizontalLayout {
    return align(newComponent(MHorizontalLayout(), true, init), alignment)
}

fun HasComponents.formLayout(alignment: Alignment? = null, init: MFormLayout.() -> Unit): MFormLayout {
    return align(newComponent(MFormLayout(), true, init), alignment)
}

fun HasComponents.newFormLayout(init: MFormLayout.() -> Unit): MFormLayout {
    return newComponent(MFormLayout(), false, init)
}

fun HasComponents.panel(caption: String? = null, alignment: Alignment? = null, init: MPanel.() -> Unit): MPanel {
    return align(newComponent(MPanel(caption), true, init), alignment)
}

fun HasComponents.textField(caption: String? = null, init: MTextField.() -> Unit = {}): MTextField {
    return newComponent(MTextField(caption), true, init)
}

fun HasComponents.label(caption: String? = null, alignment: Alignment? = null, init: MLabel.() -> Unit = {}): MLabel {
    return align(newComponent(MLabel(caption), true, init), alignment)
}

fun HasComponents.button(caption: String, init: MButton.() -> Unit = {}): MButton {
    return newComponent(MButton(caption), true, init)
}

private fun <R : Component> HasComponents.align(component: R, alignment: Alignment?): R {
    if (this is Layout.AlignmentHandler && alignment != null) setComponentAlignment(component, alignment)
    return component
}

private fun <R : Component> HasComponents.newComponent(component: R, add: Boolean, init: R.() -> Unit): R {
    if (add) {
        add(component)
    }
    component.init()
    return component
}