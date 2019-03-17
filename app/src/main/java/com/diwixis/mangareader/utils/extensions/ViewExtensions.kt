package com.diwixis.mangareader.utils.extensions

import android.view.View

/**
 * Набор extension для типа View
 *
 * @author П. Густокашин (Diwixis)
 */

/**  View становится видимой */
fun View.visible() {
    visibility = View.VISIBLE
}

/**  View становится невидимой */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**  View крывается с экрана */
fun View.gone() {
    visibility = View.GONE
}