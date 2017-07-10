package com.gluigip.kotlin.kotlinchat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinChatApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinChatApplication::class.java, *args)
}
