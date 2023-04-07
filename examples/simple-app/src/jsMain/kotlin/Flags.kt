@file:Suppress("unused")

import yfiles.graph.GraphItemTypes.Companion.EDGE
import yfiles.graph.GraphItemTypes.Companion.LABEL
import yfiles.graph.GraphItemTypes.Companion.NODE
import yfiles.input.GraphViewerInputMode
import yfiles.lang.contains
import yfiles.lang.or

fun flags() {
    val mode = GraphViewerInputMode {
        clickableItems = NODE or EDGE or LABEL
    }

    println(NODE in mode.clickableItems)
    println(EDGE in mode.clickableItems)
}
