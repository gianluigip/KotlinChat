package com.gluigip.kotlin.kotlinchat.ui.logic

import com.gluigip.kotlin.kotlinchat.ui.view.LOGIN_VIEW_NAME
import com.vaadin.navigator.Navigator
import com.vaadin.spring.annotation.UIScope
import javax.inject.Named

@Named
@UIScope
class LogInNavigator(val session: Session) {

    fun registerNavigator(navigator: Navigator) {
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