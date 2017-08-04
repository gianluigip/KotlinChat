package com.gluigip.kotlin.kotlinchat.ui.component

import com.gluigip.kotlin.kotlinchat.logic.ChatBroadcaster
import com.gluigip.kotlin.kotlinchat.logic.Session
import com.gluigip.kotlin.kotlinchat.model.Message
import com.gluigip.kotlin.kotlinchat.ui.utils.*
import com.vaadin.event.ShortcutAction
import com.vaadin.shared.ui.ContentMode
import com.vaadin.spring.annotation.ViewScope
import com.vaadin.ui.Button
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.viritin.fields.MTextField
import org.vaadin.viritin.label.MLabel
import org.vaadin.viritin.layouts.MPanel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Named


/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
@Named
@ViewScope
open class ChatComponent(val broadcaster: ChatBroadcaster, val session: Session) : ExtendedCustomComponent() {

    private val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)

    private lateinit var chatBox: MPanel
    private lateinit var chatLabel: MLabel
    private var chatContent = StringBuilder()
    private lateinit var input: MTextField

    init {
        setSizeFull()

        panel("Chat") {
            withFullHeight().withFullWidth()

            verticalLayout {
                withFullWidth().withFullHeight()

                chatBox = panel {
                    withFullHeight().withFullWidth().withStyleName(ValoTheme.PANEL_WELL)
                    verticalLayout {
                        withFullWidth()
                        chatLabel = label("").withFullWidth().withContentMode(ContentMode.HTML)
                    }
                }
                expand(chatBox)

                horizontalLayout {
                    input = textField { focus() }.withPlaceholder("New message")
                    expand(input)

                    button("Enviar") {
                        addClickListener({ _: Button.ClickEvent? -> sendMessage() })
                    }.withClickShortcut(ShortcutAction.KeyCode.ENTER).withStyleName(ValoTheme.BUTTON_PRIMARY)
                }

            }
        }
        initChatBox(broadcaster.registerListener { receiveMessage(it) })
    }

    private fun sendMessage() {
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
        messages.forEach { updateChatContent(it) }
        chatLabel.value = chatContent.toString()
        chatBox.scrollTop = Integer.MAX_VALUE
    }

    private fun updateChatContent(message: Message): String {
        chatContent.append("""<p style="margin: 0px; font-weight: bold; font-size: 13px;">
                                    ${message.date.format(formatter)} - ${message.user.displayName}:
                            </p>&nbsp;&nbsp;&nbsp;&nbsp;${message.text}""")
        return chatContent.toString()
    }
}