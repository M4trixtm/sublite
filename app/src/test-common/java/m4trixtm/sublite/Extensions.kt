package m4trixtm.sublite

import com.google.common.truth.Truth.assertThat

infix fun Any?.equals(expected: Any?) {
    when (this) {
        is String -> assertThat(this).isEqualTo(expected as String?)
        is Int -> assertThat(this).isEqualTo(expected as Int?)
        is Long -> assertThat(this).isEqualTo(expected as Long?)
        is Double -> assertThat(this).isEqualTo(expected as Double?)
        is Float -> assertThat(this).isEqualTo(expected as Float?)
        is Boolean -> assertThat(this).isEqualTo(expected as Boolean?)
    }
}

inline fun <T> T.assertThat(block: T.() -> Unit) = block()

inline fun <T> Iterable<T>.assertForeach(block: T.(position: Int) -> Unit) =
    forEachIndexed { index, item ->
        item.run { block(index) }
    }