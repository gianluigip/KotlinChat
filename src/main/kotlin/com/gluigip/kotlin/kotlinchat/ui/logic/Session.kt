package com.gluigip.kotlin.kotlinchat.ui.logic

import com.gluigip.kotlin.kotlinchat.model.User
import com.vaadin.spring.annotation.UIScope
import java.io.Serializable
import javax.inject.Named

/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
@Named
@UIScope
class Session : Serializable {

    var user: User? = null

    fun isLoggedIn(): Boolean {
        return user != null
    }
}