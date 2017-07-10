package com.gluigip.kotlin.kotlinchat.component

import com.gluigip.kotlin.kotlinchat.logic.ChatBroadcaster
import com.gluigip.kotlin.kotlinchat.logic.Session
import com.gluigip.kotlin.kotlinchat.model.Message
import com.vaadin.event.ShortcutAction
import com.vaadin.shared.ui.ContentMode
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.viritin.button.MButton
import org.vaadin.viritin.fields.MTextField
import org.vaadin.viritin.label.MLabel
import org.vaadin.viritin.layouts.MHorizontalLayout
import org.vaadin.viritin.layouts.MPanel
import org.vaadin.viritin.layouts.MVerticalLayout
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Named


/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
@Named
@UIScope
class ChatComponent(val broadcaster: ChatBroadcaster, val session: Session) : CustomComponent() {

    private val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)

    private val chatBox: MPanel
    private val chatLabel: MLabel
    private val chatContent = StringBuilder()
    private val input: MTextField

    init {
        input = MTextField()
        input.placeholder = "New message"
        input.focus()

        chatLabel = MLabel("").withFullWidth().withContentMode(ContentMode.HTML)

        chatBox = MPanel(MVerticalLayout(chatLabel).withFullWidth())
                .withFullHeight().withFullWidth().withStyleName(ValoTheme.PANEL_WELL)

        val enter = MButton("Enviar", { sendMessage(it) })
                .withClickShortcut(ShortcutAction.KeyCode.ENTER)
                .withStyleName(ValoTheme.BUTTON_PRIMARY)

        val sentBox = MHorizontalLayout(input, enter).expand(input)

        val content = MVerticalLayout(chatBox, sentBox)
                .expand(chatBox).withFullWidth().withFullHeight()

        val panel = MPanel("Chat").withFullHeight().withFullWidth()
                .withContent(content)
        compositionRoot = panel
        setSizeFull()

        initChatBox(broadcaster.registerListener { receiveMessage(it) })
    }

    private fun sendMessage(event: Button.ClickEvent) {
        if (input.value.isEmpty()) {
            return
        }
        broadcaster.sendMessage(Message(input.value, session.user!!))
        input.value = ""
    }

    private fun receiveMessage(message: Message) {
        ui.access {
            chatLabel.value = updateChatContent(message)
            chatBox.scrollTop = Integer.MAX_VALUE
        }
    }


    private fun initChatBox(messages: List<Message>) {
        messages.forEach() { updateChatContent(it) }
        chatLabel.value = chatContent.toString()
        chatBox.scrollTop = Integer.MAX_VALUE
    }

    private fun updateChatContent(message: Message): String {
        chatContent.append("<p style=\"margin: 0px; font-weight: bold; font-size: 13px;\">")
                .append(message.date.format(formatter)).append(" - ").append(message.user.displayName)
                .append(":</p>&nbsp;&nbsp;&nbsp;&nbsp;")
                .append(message.text)
        return chatContent.toString()
    }
}