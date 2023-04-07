import yfiles.graph.FreeEdgeLabelModel
import yfiles.graph.ILabel
import yfiles.graph.ILabelModel
import yfiles.graph.ILabelModelParameter

class CustomLabelModelParameter : ILabelModelParameter {
    override val model: ILabelModel
        get() = FreeEdgeLabelModel.INSTANCE

    override fun supports(label: ILabel): Boolean = true
    override fun clone(): ILabelModelParameter = this
}
