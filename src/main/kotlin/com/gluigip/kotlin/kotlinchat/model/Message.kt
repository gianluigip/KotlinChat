package com.gluigip.kotlin.kotlinchat.model

import java.time.LocalDateTime


/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
class Message(val text: String, val user: User) {
    val date: LocalDateTime = LocalDateTime.now()
}