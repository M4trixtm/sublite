package m4trixtm.sublite

import m4trixtm.sublite.features.common.ApiResponse
import m4trixtm.sublite.features.common.Data
import m4trixtm.sublite.features.show.entity.Show
import m4trixtm.sublite.features.show.entity.SubtitlesCounts

typealias FeatureData = Data<Show>

object FeatureMocks {

    val featureApiResponse: ApiResponse<Show>
        get() =
            ApiResponse(listOf(feature1, feature2))

    val feature1: FeatureData
        get() =
            FeatureData(
                id = "693885",
                type = "movie",
                attributes = show1
            )

    val show1: Show
        get() = Show(
            title = "Revenger",
            originalTitle = "리벤져",
            imdbId = 9426186,
            tmdbId = 557968,
            id = "693885",
            year = "2019",
            titleAka = listOf(
                "Revenger"
            ),
            subtitlesCounts = subtitlesCounts1,
            url = "https://www.opensubtitles.com/en/movies/2019-revenger",
            imgUrl = ""
        )

    val subtitlesCounts1: SubtitlesCounts
        get() =
            SubtitlesCounts(
                id = 5,
                el = 3,
                fa = 3,
                es = 3,
                ro = 3,
                ar = 2,
                ko = 2,
                pl = 2,
                sr = 2,
                si = 2,
                bn = 1,
                bg = 1,
                zhCN = 1,
                cs = 1,
                da = 1,
                nl = 1,
                en = 1,
                fi = 1,
                fr = 1,
                de = 1,
                he = 1,
                hu = 1,
                it = 1,
                ja = 1,
                mk = 1,
                ms = 1,
                no = 1,
                ptPT = 1,
                ru = 1,
                sv = 1,
                th = 1,
                tr = 1,
                vi = 1,
                ptBR = 1,
                zhTW = 1
            )

    val feature2: Data<Show>
        get() =
            Data(
                id = "625178",
                type = "movie",
                attributes = show2
            )

    val show2: Show
        get() =
            Show(
                title = "Godzilla: King of the Monsters",
                originalTitle = "Godzilla: King of the Monsters",
                imdbId = 3741700,
                tmdbId = 373571,
                id = "625178",
                year = "2019",
                titleAka = listOf(
                    "Godzilla: King of the Monsters",
                    " Годзила: Кралят на чудовищата",
                    " Godzilla II Král monster",
                    " Godzilla II: King of the Monsters",
                    " Γκοτζίλα II: Ο Βασιλιάς των Τεράτων",
                    " Godzilla: Rey de los Monstruos",
                    " Godzilla II : Roi des Monstres",
                    " גודזילה 2: מלך המפלצות",
                    " Godzilla: Kralj zvijeri",
                    " Godzilla II. - A szörnyek királya",
                    " Godzilla II - King of the Monsters",
                    " ゴジラ キング・オブ・モンスターズ",
                    " 고질라: 킹 오브 몬스터",
                    " Godzila 2",
                    " Godzilla: Briesmoņu karalis",
                    " Godzilla II: Król potworów",
                    " Godzilla II: Rei dos Monstros",
                    " Godzilla II: Regele monştrilor",
                    " Годзилла 2: Король монстров",
                    " Godzilla II: Kráľ monštier",
                    " Godzilla 2: Kralj pošasti",
                    " Годзила 2: Краљ чудовишта",
                    " ก็อดซิลล่า 2 ราชันแห่งมอนสเตอร์",
                    " Godzilla II: Canavarlar Kralı",
                    " Ґодзілла II: Король монстрів",
                    " Chúa tể Godzilla: Đế Vương Bất Tử",
                    " 哥斯拉：怪兽之王"
                ),
                subtitlesCounts = subtitlesCounts2,
                url = "https://www.opensubtitles.com/en/movies/2019-godzilla-king-of-monsters",
                imgUrl = "https://s9.osdb.link/features/8/7/1/625178.jpg"
            )
    val subtitlesCounts2: SubtitlesCounts
        get() =
            SubtitlesCounts(
                en = 23,
                ro = 13,
                ar = 12,
                cs = 11,
                pl = 11,
                ptBR = 11,
                hr = 10,
                el = 9,
                ms = 9,
                hu = 8,
                id = 8,
                es = 8,
                nl = 7,
                fa = 7,
                tr = 7,
                ptPT = 6,
                sr = 6,
                bg = 5,
                he = 5,
                vi = 5,
                zhCN = 4,
                fr = 4,
                no = 4,
                et = 3,
                de = 3,
                ko = 3,
                ru = 3,
                sl = 3,
                zhTW = 3,
                pm = 3,
                bn = 2,
                fi = 2,
                it = 2,
                sv = 2,
                ze = 2,
                da = 1,
                hi = 1,
                ja = 1,
                lt = 1,
                ml = 1,
                si = 1,
                ta = 1,
                tl = 1,
                th = 1
            )
}