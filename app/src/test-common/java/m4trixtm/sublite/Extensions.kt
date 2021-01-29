package m4trixtm.sublite

inline fun <T> T.assertThat(block: T.() -> Unit) = block()

inline fun <T> Iterable<T>.assertForeach(block: T.(position: Int) -> Unit) =
    forEachIndexed { index, item ->
        item.run { block(index) }
    }