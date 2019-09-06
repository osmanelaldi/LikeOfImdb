package com.example.likeofimdb.models

import java.io.Serializable

class Film : Serializable {

    var name: String? = null
    var category: String? = null
    var hour: String? = null
    var voteImdb: String? = null
    var voteTomato: String? = null
    var voteMetacritic: String? = null
    var description: String? = null
    var posterUrl: String? = null
    var profileUrl: String? = null
    var ssUrlList: List<String>? = null
    var videoUrl: String? = null

    constructor(

    )

    constructor(
        name: String?,
        category: String?,
        hour: String?,
        voteImdb: String?,
        voteTomato: String?,
        voteMetacritic: String?,
        description: String?,
        posterUrl: String?,
        profileUrl: String?,
        ssUrlList: List<String>?,
        videoUrl: String?
    ) {
        this.name = name
        this.category = category
        this.hour = hour
        this.voteImdb = voteImdb
        this.voteTomato = voteTomato
        this.voteMetacritic = voteMetacritic
        this.description = description
        this.posterUrl = posterUrl
        this.profileUrl = profileUrl
        this.ssUrlList = ssUrlList
        this.videoUrl = videoUrl
    }
}