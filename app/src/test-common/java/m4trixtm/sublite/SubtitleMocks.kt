package m4trixtm.sublite

import m4trixtm.sublite.features.ApiResponse
import m4trixtm.sublite.features.Data
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import m4trixtm.sublite.features.subtitle.entity.SubtitleDetails
import m4trixtm.sublite.features.subtitle.entity.SubtitleDownloadLink
import m4trixtm.sublite.features.subtitle.entity.SubtitleFile
import java.text.SimpleDateFormat
import java.util.*

typealias SubtitleData = Data<Subtitle>

object SubtitleMocks {

    val searchResponse: ApiResponse<Subtitle>
        get() = ApiResponse(
            totalPages = 1,
            totalCount = 10,
            page = 1,
            data = listOf(subtitleSearchData1, subtitleSearchData2)
        )

    val subtitleSearchData1: SubtitleData
        get() = SubtitleData(
            id = "998327",
            type = "subtitle",
            attributes = subtitle1
        )

    val subtitle1: Subtitle
        get() = Subtitle(
            id = "998327",
            language = "pt-BR",
            downloadCount = 677290,
            newDownloadCount = 24,
            isHD = false,
            format = null,
            fps = 25.0f,
            votes = 0,
            points = 0,
            ratings = 0.0f,
            fromTrusted = false,
            foreignPartsOnly = false,
            autoTranslation = false,
            aiTranslated = false,
            uploadDate = dateOf("2014-11-20T02:37:51Z")!!,
            release = "Interstellar.2014.HDCAM.x264.AAC-SUMO",
            details = subtitleDetails1,
            files = subtitleFiles1
        )

    val subtitleDetails1: SubtitleDetails
        get() = SubtitleDetails(
            id = 594208,
            type = "Movie",
            year = 2014,
            title = "Interstellar",
            movieName = "2014 - Interstellar",
            imdbId = 816692,
            tmdbId = 157336
        )

    val subtitleFiles1: List<SubtitleFile>
        get() = listOf(
            SubtitleFile(
                id = 1084435,
                cdNumber = 1,
                fileName = "Interstellar.2014.HDCAM.x264.AAC-SUMO-por.srt"
            )
        )

    val subtitleSearchData2: SubtitleData
        get() = SubtitleData(
            id = "1007788",
            type = "subtitle",
            attributes = subtitle2
        )

    val subtitle2: Subtitle
        get() = Subtitle(
            id = "1007788",
            language = "en",
            downloadCount = 641831,
            newDownloadCount = 266,
            isHD = true,
            format = null,
            fps = 23.976f,
            votes = 7,
            points = 24,
            ratings = 3.4f,
            fromTrusted = true,
            foreignPartsOnly = false,
            autoTranslation = false,
            aiTranslated = false,
            uploadDate = dateOf("2015-03-15T05:42:08Z")!!,
            release = "Interstellar.2014.720p.BluRay.x264-DAA",
            details = subtitleDetails2,
            files = subtitleFiles2
        )

    val subtitleDetails2: SubtitleDetails
        get() = SubtitleDetails(
            id = 594208,
            type = "Movie",
            year = 2014,
            title = "Interstellar",
            movieName = "2014 - Interstellar",
            imdbId = 816692,
            tmdbId = 157336
        )

    val subtitleFiles2: List<SubtitleFile>
        get() = listOf(
            SubtitleFile(
                id = 1094041,
                cdNumber = 1,
                fileName = "Interstellar.2014.720p.BluRay.x264-DAA.srt"
            )
        )

    val downloadLinkResponse: SubtitleDownloadLink
        get() = SubtitleDownloadLink(
            link = "https://www.opensubtitles.com/download/1CBACEA536D247F79BBA19E54A7785D30EE24F8A51C99983DA4D4E6B6D79CDD482A84FA811776927EBB810EC9206744ADF2A6D682599937A237982D70097AC0F1F46198081B3715B915C70BC9BBEB1AE5FF187DE76A639A7E81611D421498B618319C68FD8F3B44AE461147ECAE7EC8B5DB2116BAD4FCF114B68EC1B4A2D8A37525CF4DD53FE5A42B2B4BC9EA6C74FB055B3981410DE76D04518F2036AD2E4CBC8FA212C90FA7F8A6C69FE5404B7F0830D2610C33A26772800E4463B523247C18F3CA66802BE3E23/subfile/1084435.srt",
            fileName = "1084435.srt",
            requests = 6,
            allowed = 100,
            remaining = 94,
            message = ""
        )

    fun dateOf(date: String): Date? =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(date)
}
