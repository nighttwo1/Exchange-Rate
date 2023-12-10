package com.nighttwo1.presentation.currencyicons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.radialGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.nighttwo1.presentation.CurrencyIcons

public val CurrencyIcons.Bg: ImageVector
    get() {
        if (_bg != null) {
            return _bg!!
        }
        _bg = Builder(name = "Bg", defaultWidth = 360.0.dp, defaultHeight = 358.0.dp, viewportWidth
                = 360.0f, viewportHeight = 358.0f).apply {
            path(fill = radialGradient(0.0f to Color(0xFFEAEAFE), 1.0f to Color(0x00DDF6F3), center
                    = Offset(73.5f,-17.5f), radius = 375.5f), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(0.0f, 350.81f)
                verticalLineTo(0.0f)
                horizontalLineTo(360.0f)
                verticalLineTo(225.242f)
                curveTo(291.122f, 306.456f, 188.33f, 358.0f, 73.5f, 358.0f)
                curveTo(48.346f, 358.0f, 23.769f, 355.527f, 0.0f, 350.81f)
                close()
            }
        }
        .build()
        return _bg!!
    }

private var _bg: ImageVector? = null
