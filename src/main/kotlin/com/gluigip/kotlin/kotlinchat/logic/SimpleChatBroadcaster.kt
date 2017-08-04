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
class SimpleChatBroadcaster(val repository: MessageRepository) : ChatBroadcaster {

    private val listeners = HashSet<(Message) -> Unit>()

    @Synchronized override fun registerListener(listener: (Message) -> Unit): List<Message> {
        listeners.add(listener)
        return repository.retrieveAll(-1)
    }

    @Synchronized override fun unregisterListener(listener: (Message) -> Unit) {
        listeners.remove(listener)
    }

    @Synchronized override fun sendMessage(message: Message) {
        repository.save(message)
        listeners.forEach { it.invoke(message) }
    }
}