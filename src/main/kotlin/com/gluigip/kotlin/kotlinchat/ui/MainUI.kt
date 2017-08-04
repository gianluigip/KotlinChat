package com.gluigip.kotlin.kotlinchat.ui

import com.gluigip.kotlin.kotlinchat.ui.logic.LogInNavigator
import com.vaadin.annotations.Push
import com.vaadin.annotations.Theme
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewDisplay
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.spring.annotation.SpringViewDisplay
import com.vaadin.ui.Component
import com.vaadin.ui.UI
import org.vaadin.viritin.layouts.MPanel


/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
@Push
@SpringUI()
@Theme("valo")
@SpringViewDisplay
class MainUI(val logInNavigator: LogInNavigator) : UI(), ViewDisplay {

    private val main: MPanel = MPanel().withFullHeight().withFullWidth()

    override fun showView(view: View) {
        content = view as Component
    }

    override fun init(vaadinRequest: VaadinRequest) {
        content = main
        logInNavigator.registerNavigator(navigator)
    }


}