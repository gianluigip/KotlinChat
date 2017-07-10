# KotlinChat
A basic app to try Kotlin with Spring Boot, Vaadin and Gradle

The purpose of this repo is to try a mix of new technologies together:
* Kotlin, a new JVM language that is gaining popularity
* Spring Boot 2 which use the new Spring Framework 5 with enchanted support for Kotlin
* Vaadin 8, the new version of the web framework with better support for lambdas
* Gradle, supporting a modern build tool.

The project itself is a basic Vaadin application, with one UI and two views, it inject some services to try the integration with Spring.

Disclaimer: The project is not mended to follow all the best practices and you will found a very simplistic approach to some topics as login or session.

# Build and Execute

```
gradlew build
```
It will compile the project.

```
gradlew bootRun
```

It will start the app at the URL http://localhost:8080/

# New Way to Define Layout

Check ComponentsBuilder.kt, using the extension functions with the syntactic sugar provided by Kotlin we can have a very nice and clean layout definition:

```kotlin
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
```
