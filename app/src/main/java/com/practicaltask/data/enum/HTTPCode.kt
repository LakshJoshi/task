package com.practicaltask.data.enum

enum class HTTPCode(val code: Int) {
    SUCCESS(200),
    SUCCESS_1(201),
    FORCE_LOGOUT(401),
    CONTENT_ERROR(422),
    FAILED(500),
}