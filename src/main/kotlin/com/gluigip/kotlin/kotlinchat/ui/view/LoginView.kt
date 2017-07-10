package com.gluigip.kotlin.kotlinchat.ui.view

import com.gluigip.kotlin.kotlinchat.logic.Session
import com.gluigip.kotlin.kotlinchat.model.User
import com.gluigip.kotlin.kotlinchat.utils.*
import com.vaadin.data.HasValue
import com.vaadin.event.ShortcutAction
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.themes.ValoTheme
import org.vaadin.viritin.button.MButton
import org.vaadin.viritin.fields.MTextField

/**
 * Created by gianluigi.pierini on 07/07/2017.
 */
const val LOGIN_VIEW_NAME = "login"

@SpringView(name = LOGIN_VIEW_NAME)
class LoginView(val session: Session) : ExtendedCustomComponent(), View {


    private var loginField: MTextField = MTextField()
    private var loginButton: MButton = MButton()


    init {

        verticalLayout {
            withFullHeight().withFullWidth().withStyleName(ValoTheme.PANEL_WELL)

            panel(Alignment.TOP_CENTER) {
                withWidth("400px")

                verticalLayout {
                    withFullWidth().withMargin(true)

                    label("Welcome to the Chat").withStyleName(ValoTheme.LABEL_COLORED, ValoTheme.LABEL_H1, ValoTheme.LABEL_NO_MARGIN)

                    formLayout(Alignment.TOP_CENTER) {
                        withFullWidth().withMargin(true)

                        loginField = textField("Username:").withPlaceholder("Display Name").addTextChangeListener { fieldLoginListener(it) }

                        loginButton = button("Enter") { isEnabled = false }.addClickListener { -> login(loginField.value) }
                                .withClickShortcut(ShortcutAction.KeyCode.ENTER).withStyleName(ValoTheme.BUTTON_PRIMARY)
                    }
                }
            }
        }
        setSizeFull()
    }

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
        loginField.focus()
    }

    private fun fieldLoginListener(value: HasValue.ValueChangeEvent<String>) {
        loginButton.isEnabled = !value.value.isEmpty()
    }

    fun login(username: String) {
        session.user = User(username)
        ui.navigator.navigateTo(CHAT_VIEW_NAME);
    }
}