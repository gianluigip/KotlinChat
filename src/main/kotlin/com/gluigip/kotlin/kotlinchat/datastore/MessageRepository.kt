package com.gluigip.kotlin.kotlinchat.datastore

import com.gluigip.kotlin.kotlinchat.model.Message

interface MessageRepository{

    fun save(message: Message)
    fun retrieveAll(limit: Int): List<Message>

}