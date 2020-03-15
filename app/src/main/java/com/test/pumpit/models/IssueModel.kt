package com.test.pumpit.models

data class IssueModel(
    val title: String,
    val state: String,
    val user: User,
    val number: Int,
    val pull_request: Map<String, String> ?= null //checking if its pull request or issue
)

data class ExtendedIssueModel(
    val title: String,
    val state: String,
    val user: User,
    val number: Int,
    val created_at: String,
    val body: String,
    val labels: ArrayList<LabelModel>
)

data class LabelModel(
    val name: String,
    val color: String
)

data class User(
    val login: String,
    val avatar: String
)

/**
 *
    {
        "url": "https://api.github.com/repos/android/sunflower/issues/584",
        "repository_url": "https://api.github.com/repos/android/sunflower",
        "labels_url": "https://api.github.com/repos/android/sunflower/issues/584/labels{/name}",
        "comments_url": "https://api.github.com/repos/android/sunflower/issues/584/comments",
        "events_url": "https://api.github.com/repos/android/sunflower/issues/584/events",
        "html_url": "https://github.com/android/sunflower/pull/584",
        "id": 575569908,
        "node_id": "MDExOlB1bGxSZXF1ZXN0MzgzNjk4MjE1",
        "number": 584,
        "title": "Use \"let\" language function instead of \"val\"",
        "user": {
            "login": "acious",
            "id": 18098156,
            "node_id": "MDQ6VXNlcjE4MDk4MTU2",
            "avatar_url": "https://avatars3.githubusercontent.com/u/18098156?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/acious",
            "html_url": "https://github.com/acious",
            "followers_url": "https://api.github.com/users/acious/followers",
            "following_url": "https://api.github.com/users/acious/following{/other_user}",
            "gists_url": "https://api.github.com/users/acious/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/acious/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/acious/subscriptions",
            "organizations_url": "https://api.github.com/users/acious/orgs",
            "repos_url": "https://api.github.com/users/acious/repos",
            "events_url": "https://api.github.com/users/acious/events{/privacy}",
            "received_events_url": "https://api.github.com/users/acious/received_events",
            "type": "User",
            "site_admin": false
            },
        "labels": [
            ],
        "state": "open",
        "locked": false,
        "assignee": null,
        "assignees": [
            ],
        "milestone": null,
        "comments": 1,
        "created_at": "2020-03-04T16:50:52Z",
        "updated_at": "2020-03-04T17:52:08Z",
        "closed_at": null,
        "author_association": "NONE",
        "pull_request": {
            "url": "https://api.github.com/repos/android/sunflower/pulls/584",
            "html_url": "https://github.com/android/sunflower/pull/584",
            "diff_url": "https://github.com/android/sunflower/pull/584.diff",
            "patch_url": "https://github.com/android/sunflower/pull/584.patch"
            },
        "body": "There is no need to use `val` variable.\r\nI think that using `let` better than 'val' declaration "
        }
 */