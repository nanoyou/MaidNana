package com.github.nanoyou.maidnana.web

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import java.io.File

object T {}
fun main() {
    val server = embeddedServer(Netty, 5277) {
        install(ContentNegotiation) {
            gson {  }
        }
        routing {
            static("/") {
                myResources("META-INF/public")
                myDefaultResource("META-INF/public/index.html")
            }
            route("/api") {
                get("hello") {
                    val r = HashMap<String, String>()
                    r["data"] = "world"
                    context.respond(r)
                }
            }
        }
    }
    server.start(wait = true)
}
private fun String?.combinePackage(resourcePackage: String?) = when {
    this == null -> resourcePackage
    resourcePackage == null -> this
    else -> "$this.$resourcePackage"
}
fun Route.myResources(resourcePackage: String? = null) {
    val packageName = staticBasePackage.combinePackage(resourcePackage)
    get("{path...}") {
        val relativePath = call.parameters.getAll("path")?.joinToString(File.separator) ?: return@get
        val content = call.resolveResource(relativePath, packageName, T.javaClass.classLoader)
        if (content != null) {
            call.respond(content)
        }
    }
}

fun Route.myDefaultResource(resource: String, resourcePackage: String? = null) {
    val packageName = staticBasePackage.combinePackage(resourcePackage)
    get {
        val content = call.resolveResource(resource, packageName, T.javaClass.classLoader)
        if (content != null) {
            call.respond(content)
        }
    }
}
