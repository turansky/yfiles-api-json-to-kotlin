import yfiles.layout.LayoutOrientation

@JsExport
@ExperimentalJsExport
class EnumPolygon {
    fun enumTest() {
        // val values = LayoutOrientation.values()
        // val topToBottom = LayoutOrientation.valueOf("TOP_TO_BOTTOM")

        val name = LayoutOrientation.BOTTOM_TO_TOP.name
        val ordinal = LayoutOrientation.LEFT_TO_RIGHT.ordinal
        val s1 = LayoutOrientation.RIGHT_TO_LEFT.toString()
        val s2 = "${LayoutOrientation.TOP_TO_BOTTOM}"
    }
}