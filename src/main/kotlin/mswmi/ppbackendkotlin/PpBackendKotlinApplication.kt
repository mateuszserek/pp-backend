package mswmi.ppbackendkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PpBackendKotlinApplication

fun main(args: Array<String>) {
    runApplication<PpBackendKotlinApplication>(*args)
}
