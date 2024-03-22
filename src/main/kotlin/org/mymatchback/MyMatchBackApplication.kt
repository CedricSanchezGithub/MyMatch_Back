package org.mymatchback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyMatchBackApplication

fun main(args: Array<String>) {
    runApplication<MyMatchBackApplication>(*args)
}
