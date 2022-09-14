package com.github.nanoyou.maidnana.web.controller

import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private fun Route.documentation() {
    install(NotarizedRoute()) {
        parameters = listOf(
            Parameter(
                name = "echo",
                `in` = Parameter.Location.path,
                schema = TypeDefinition.STRING
            )
        )
        get = GetInfo.builder {
            summary("返回传入的字符串")
            description("传入什么,返回什么")
            response {
                responseCode(HttpStatusCode.OK)
                responseType<HelloResponse>()
                description("成功!")
            }
        }
    }
}

fun Route.hello() {
    route("hello/{echo}") {
        documentation()
        get {
            context.respond(HelloResponse(call.parameters["echo"] ?: ""))
        }
    }
}
data class HelloResponse(val echo: String)
