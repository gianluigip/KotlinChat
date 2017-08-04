package com.gluigip.kotlin.kotlinchat.ui

import com.gluigip.kotlin.kotlinchat.logic.Session
import com.gluigip.kotlin.kotlinchat.ui.view.LOGIN_VIEW_NAME
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
//@PreserveOnRefresh
class MainUI(val session: Session) : UI(), ViewDisplay {

    private val main: MPanel = MPanel().withFullHeight().withFullWidth()

    override fun showView(view: View) {
        content = view as Component
    }

    override fun init(vaadinRequest: VaadinRequest) {
        content = main
        navigator.addViewChangeListener {
            if (!session.isLoggedIn() && it.viewName != LOGIN_VIEW_NAME) {
                navigator.navigateTo(LOGIN_VIEW_NAME)
                false
            } else {
                true
            }
        }
    }
}