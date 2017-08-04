package com.gluigip.kotlin.kotlinchat.logic

import com.gluigip.kotlin.kotlinchat.model.Message
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named


/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
@Named
@ApplicationScoped
class ChatBroadcaster {
    private val chat = ArrayList<Message>()
    private val listeners = HashSet<(Message) -> Unit>()

    @Synchronized fun registerListener(listener: (Message) -> Unit): List<Message> {
        listeners.add(listener)
        return chat
    }

    @Synchronized fun unregisterListener(listener: (Message) -> Unit) {
        listeners.remove(listener)
    }

    @Synchronized fun sendMessage(message: Message) {
        chat.add(message)
        listeners.forEach { it.invoke(message) }
    }
}