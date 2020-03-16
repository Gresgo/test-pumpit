package com.test.pumpit.models

data class IssueModel(
    val title: String ?= "",
    val state: String ?= "",
    val user: User,
    val number: Int = 0,
    val pull_request: Map<String, String> ?= null //checking if its pull request or issue
)

data class ExtendedIssueModel(
    val title: String ?= "",
    val state: String ?= "",
    val user: User,
    val number: Int = 0,
    var created_at: String ?= "",
    val body: String ?= "",
    val labels: ArrayList<LabelModel>
)

data class LabelModel(
    val name: String ?= "",
    val color: String ?= ""
)

data class User(
    val login: String ?= "",
    val avatar_url: String ?= ""
)