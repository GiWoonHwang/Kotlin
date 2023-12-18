package io.basquiat.request.common.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "rabbitmq")
data class RabbitMQProperties @ConstructorBinding constructor(
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val virtualHost: String,
    val exchange: String,
    val dbQueue: String,
    val dbRoutingKey: String,
    val logQueue: String,
    val logRoutingKey: String,
)