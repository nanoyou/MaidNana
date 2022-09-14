package com.github.nanoyou.maidnana.web

import com.github.nanoyou.maidnana.constant.MaidNanaConstant
import com.github.nanoyou.maidnana.web.controller.hello
import io.bkbn.kompendium.core.plugin.NotarizedApplication
import io.bkbn.kompendium.core.routes.redoc
import io.bkbn.kompendium.oas.OpenApiSpec
import io.bkbn.kompendium.oas.info.Info
import io.bkbn.kompendium.oas.server.Server
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.net.URI

object T

fun main() {
    val server = embeddedServer(Netty, MaidNanaConstant.WEB_PORT) {
        install(ContentNegotiation) {
            gson {  }
        }
        install(NotarizedApplication()) {
            spec = OpenApiSpec(
                info = Info(
                    title = "MaidNana Web API Doc",
                    version = MaidNanaConstant.VERSION,
                    description = "为 MaidNana 提供 web 服务",
                ),
                servers = mutableListOf(
                    Server(
                        url = URI("http://127.0.0.1:5277/"),
                        description = "本机",
                    )
                )
            )
        }
        routing {
            static("/") {
                myResources("META-INF/public")
                myDefaultResource("META-INF/public/index.html")
            }
            route("/api") {
                redoc("MaidNana API 文档")
                hello()
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
