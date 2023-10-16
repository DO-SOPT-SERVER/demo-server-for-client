package com.example.demoserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoServerForClientApplication

fun main(args: Array<String>) {
    runApplication<DemoServerForClientApplication>(*args)
}
