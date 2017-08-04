package com.gluigip.kotlin.kotlinchat.ui.view

import com.gluigip.kotlin.kotlinchat.ui.component.ChatComponent
import com.gluigip.kotlin.kotlinchat.ui.logic.Session
import com.gluigip.kotlin.kotlinchat.ui.utils.ExtendedCustomComponent
import com.gluigip.kotlin.kotlinchat.ui.utils.horizontalLayout
import com.gluigip.kotlin.kotlinchat.ui.utils.label
import com.gluigip.kotlin.kotlinchat.ui.utils.verticalLayout
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.themes.ValoTheme


/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
const val CHAT_VIEW_NAME = ""

@SpringView(name = CHAT_VIEW_NAME)
class ChatView(val chat: ChatComponent, val userSession: Session) : ExtendedCustomComponent(), View {

    override fun enter(viewChangeEvent: ViewChangeListener.ViewChangeEvent) {
        setSizeFull()

        verticalLayout {
            withFullHeight().withFullWidth()

            label("Welcome ${userSession.user?.displayName}", Alignment.TOP_CENTER).withStyleName(ValoTheme.LABEL_H1)

            val mainContent = horizontalLayout {
                withFullHeight().withFullWidth()
                with(chat).expand(chat)
            }
            withExpand(mainContent, 1f)
        }
    }
}