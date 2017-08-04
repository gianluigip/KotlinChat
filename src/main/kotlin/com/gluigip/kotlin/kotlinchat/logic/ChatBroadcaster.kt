package com.gluigip.kotlin.kotlinchat.logic

import com.gluigip.kotlin.kotlinchat.datastore.MessageRepository
import com.gluigip.kotlin.kotlinchat.model.Message
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named


/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
@Named
@ApplicationScoped
class ChatBroadcaster(val repository: MessageRepository) {

    private val listeners = HashSet<(Message) -> Unit>()

    @Synchronized fun registerListener(listener: (Message) -> Unit): List<Message> {
        listeners.add(listener)
        return repository.retrieveAll(-1)
    }

    @Synchronized fun unregisterListener(listener: (Message) -> Unit) {
        listeners.remove(listener)
    }

    @Synchronized fun sendMessage(message: Message) {
        repository.save(message)
        listeners.forEach { it.invoke(message) }
    }
}