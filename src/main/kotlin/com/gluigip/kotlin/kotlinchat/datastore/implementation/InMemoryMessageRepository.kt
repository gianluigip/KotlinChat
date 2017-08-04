package com.gluigip.kotlin.kotlinchat.datastore.implementation

import com.gluigip.kotlin.kotlinchat.datastore.MessageRepository
import com.gluigip.kotlin.kotlinchat.model.Message
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named

@Named
@ApplicationScoped
class InMemoryMessageRepository : MessageRepository {

    private val chat = ArrayList<Message>()

    override fun save(message: Message) {
        chat.add(message)
    }

    override fun retrieveAll(limit: Int): List<Message> {
        return chat
    }

}