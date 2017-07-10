package com.gluigip.kotlin.kotlinchat.ui.view

import com.gluigip.kotlin.kotlinchat.component.ChatComponent
import com.gluigip.kotlin.kotlinchat.logic.Session
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.Alignment
import com.vaadin.ui.Component
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.viritin.label.MLabel
import org.vaadin.viritin.layouts.MHorizontalLayout
import org.vaadin.viritin.layouts.MVerticalLayout


/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
const val CHAT_VIEW_NAME = ""

@UIScope
@SpringView(name = CHAT_VIEW_NAME)
class ChatView(val chat: ChatComponent, val session: Session) : CustomComponent(), View {

    private var content: Component? = null

    override fun enter(viewChangeEvent: ViewChangeListener.ViewChangeEvent) {
        val header = MLabel("Welcome " + session.user?.displayName)

        header.addStyleName(ValoTheme.LABEL_H1)

        val mainContent = MHorizontalLayout(chat).expand(chat)
                .withFullHeight().withFullWidth()
        content = MVerticalLayout(header, mainContent).withAlign(header, Alignment.TOP_CENTER)
                .withExpand(mainContent, 1f).withFullHeight().withFullWidth()
        compositionRoot = content
        setSizeFull()
    }
}