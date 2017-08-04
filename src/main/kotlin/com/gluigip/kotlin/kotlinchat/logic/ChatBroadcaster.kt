package com.gluigip.kotlin.kotlinchat.logic

import com.gluigip.kotlin.kotlinchat.model.Message

interface ChatBroadcaster {

    fun registerListener(listener: (Message) -> Unit): List<Message>

    fun unregisterListener(listener: (Message) -> Unit)

    fun sendMessage(message: Message)
}