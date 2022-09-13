package com.github.nanoyou.maidnana.web

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val server = embeddedServer(Netty, 5277) {
        install(ContentNegotiation) {
            gson {  }
        }
        routing {
            resources("META-INF/public")
            route("/api") {
                get("hello") {
                    val r = HashMap<String, String>()
                    r["data"] = "world"
                    context.respond(r)
                }
            }
        }
    }
    server.start(wait = false)
}