package com.gluigip.kotlin.kotlinchat.utils

import com.vaadin.ui.Component
import com.vaadin.ui.CustomComponent

/**
 * Created by gianluigi.pierini on 10/07/2017.
 */
abstract class ExtendedCustomComponent : CustomComponent() {

    var content: Component
        get() = compositionRoot
        set(value) {
            compositionRoot = value
        }
}