package com.yworks.yfiles.api.generator

internal val DOUBLE_METHODS = setOf(
    "getNumber",
    "distanceSq",
    "distance",
    "distanceTo",
    "calculateLength",
    "sum",
    "findRayIntersection",
    "findLineIntersection",
    "scalarProduct",
    "distanceToSegment",
    "commitPositionsSmoothly",
    "manhattanDistanceTo",
    "calculateScaleForWidth",
    "calculateScaleForHeight",
    "getCenterX",
    "getCenterY",
    "getX",
    "getY",
    "getWidth",
    "getHeight",
    "getBridgeWidth",
    "getBridgeHeight",
    "getXOffsetForLayoutOrientation",
    "getYOffsetForLayoutOrientation",
    "getSmoothingLength",
    "getMinimumFirstSegmentLength",
    "getMinimumOctilinearSegmentLength",
    "getMinimumLastSegmentLength",
    "getMinimumLayerHeight",
    "getLayerAlignment",
    "getProfit",
    "getValueAt",
    "getValue",
    "getMin",
    "getSlope",
    "getMax",
    "getMaxValue",
    "getMinValue",
    "getDistanceTo",
    "getLength",
    "getPortBorderGap",
    "getPortDistanceDelta"
)

internal val DOUBLE_PROPERTIES = setOf(
    "c",
    "min",
    "max",
    "minValue",
    "maxValue",
    "slope",
    "end",
    "dissimilarityValue",
    "distance",
    "top",
    "left",
    "bottom",
    "right",
    "xOffset",
    "deltaX",
    "deltaY",
    "x",
    "y",
    "width",
    "height",
    "minX",
    "minY",
    "maxX",
    "maxY",
    "centerX",
    "centerY",
    "anchorX",
    "anchorY",
    "upX",
    "upY",
    "angle",
    "initialAngle",
    "fixedRadius",
    "minimumRadius",
    "lastAppliedRadius",
    "spacingBetweenFamilyMembers",
    "offsetForFamilyNodes",
    "lastX",
    "lastY",
    "length",
    "currentEndPointX",
    "currentEndPointY",
    "verticalInsets",
    "horizontalInsets",
    "x2",
    "y2",
    "vectorLength",
    "squaredVectorLength",
    "area",
    "offset",
    "edgeOverlapPenalty",
    "nodeOverlapPenalty",
    "profit",
    "actualMinSize",
    "actualSize",
    "minimumSize",
    "yOffset",
    "ratio",
    "cropLength",
    "scale",
    "nodeScalingFactor",
    "maximumNodeSize",
    "minimumNodeSize",
    "nodeHalo",
    "minimumFirstSegmentLength",
    "minimumLastSegmentLength",
    "minimumLength",
    "minimumSlope",
    "minimumOctilinearSegmentLength",
    "thickness",
    "layerAlignment",
    "minimumLayerHeight",
    "backLoopPenalty",
    "crossingPenalty",
    "overUsagePenalty",
    "swimLaneCrossingWeight",
    "laneTightness",
    "minimumLaneWidth",
    "leftLaneInset",
    "rightLaneInset",
    "computedLanePosition",
    "computedLaneWidth",
    "spacing",
    "sourcePortCandidateHitRadius",
    "snapLineExtension",
    "horizontalGridWidth",
    "verticalGridWidth",
    "finishRadius",
    "weight",
    "totalMilliseconds",
    "totalSeconds",
    "totalMinutes",
    "minimumWidth",
    "leftInset",
    "rightInset",
    "computedWidth",
    "originalWidth",
    "originalPosition",
    "computedPosition",
    "tightness",
    "componentSpacing",
    "maximumError",
    "bundlingQuality",
    "bundlingStrength",
    "edgeSpacing",
    "fixedWidth",
    "rotationAngle",
    "preferredHeight",
    "preferredWidth",
    "scaleFactorY",
    "scaleFactorX",
    "translateX",
    "translateY",
    "customProfit",
    "overlapPenalty",
    "xAlignment",
    "yAlignment",
    "cost",
    "distanceToEdge",
    "minimumHeight",
    "topInset",
    "bottomInset",
    "computedHeight",
    "originalHeight",
    "verticalAlignment",
    "horizontalAlignment",
    "groupNodeCompactness",
    "initialTemperature",
    "finalTemperature",
    "gravityFactor",
    "iterationFactor",
    "preferredEdgeLength",
    "clusteringQuality",
    "compactnessFactor",
    "splitSegmentLength",
    "splitNodeSize",
    "minimumSegmentLength",
    "layerSpacing",
    "maximumChildSectorAngle",
    "minimumBendAngle",
    "radius",
    "sectorStart",
    "sectorSize",
    "location",
    "minimumBackboneSegmentLength",
    "costs",
    "heuristicCosts",
    "preferredPolylineSegmentLength",
    "originX",
    "originY",
    "center",
    "gridOriginX",
    "gridOriginY",
    "edgeLengthPenalty",
    "bendPenalty",
    "edgeCrossingPenalty",
    "nodeCrossingPenalty",
    "groupNodeCrossingPenalty",
    "nodeLabelCrossingPenalty",
    "edgeLabelCrossingPenalty",
    "minimumNodeToEdgeDistancePenalty",
    "minimumGroupNodeToEdgeDistancePenalty",
    "minimumEdgeToEdgeDistancePenalty",
    "minimumNodeCornerDistancePenalty",
    "minimumFirstLastSegmentLengthPenalty",
    "bendsInNodeToEdgeDistancePenalty",
    "monotonyViolationPenalty",
    "partitionGridCellReentrancePenalty",
    "portViolationPenalty",
    "invalidEdgeGroupingPenalty",
    "singleSideSelfLoopPenalty",
    "minimumPolylineSegmentLength",
    "preferredOctilinearSegmentLength",
    "inset",
    "extraCropLength",
    "textSize",
    "zoom",
    "smoothingLength",
    "roundRectArcRadius",
    "targetArrowScale",
    "sourceArrowScale",
    "nodeLabelSpacing",
    "edgeLabelSpacing",
    "angleSum",
    "busAlignment",
    "minimumSlopeHeight",
    "connectorX",
    "connectorY",
    "zoomThreshold",
    "defaultBridgeWidth",
    "clipMargin",
    "defaultBridgeHeight",
    "hitTestRadius",
    "hitTestRadiusTouch",
    "minimumZoom",
    "maximumZoom",
    "mouseWheelZoomFactor",
    "mouseWheelScrollFactor",
    "fontSize",
    "lineSpacing",
    "horizontalSpacing",
    "verticalSpacing",
    "visibilityThreshold",
    "wheelDelta",
    "radiusX",
    "radiusY",
    "miterLimit",
    "verticalOffset",
    "horizontalOffset",
    "maximumTargetZoom",
    "minimumNodeCentrality",
    "maximumNodeCentrality",
    "minimumEdgeCentrality",
    "maximumEdgeCentrality",
    "scaleFactor",
    "epsilon",
    "criticalEdgePriority",
    "portBorderGapRatios",
    "cutoff"
)