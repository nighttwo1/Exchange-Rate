package com.nighttwo1.presentation.currencyicons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nighttwo1.presentation.CurrencyIcons

public val CurrencyIcons.IcExchange: ImageVector
    get() {
        if (_icExchange != null) {
            return _icExchange!!
        }
        _icExchange = Builder(
            name = "IcExchange", defaultWidth = 44.0.dp, defaultHeight = 44.0.dp,
            viewportWidth = 44.0f, viewportHeight = 44.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF26278D)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(44.0f, 22.0f)
                curveTo(44.0f, 34.1503f, 34.1503f, 44.0f, 22.0f, 44.0f)
                curveTo(9.8497f, 44.0f, 0.0f, 34.1503f, 0.0f, 22.0f)
                curveTo(0.0f, 9.8497f, 9.8497f, 0.0f, 22.0f, 0.0f)
                curveTo(34.1503f, 0.0f, 44.0f, 9.8497f, 44.0f, 22.0f)
                close()
                moveTo(23.23f, 25.75f)
                curveTo(23.23f, 26.0214f, 23.3183f, 26.2855f, 23.4816f, 26.5022f)
                curveTo(23.645f, 26.719f, 23.8745f, 26.8767f, 24.1354f, 26.9516f)
                curveTo(24.3963f, 27.0264f, 24.6744f, 27.0143f, 24.9278f, 26.917f)
                curveTo(25.1812f, 26.8198f, 25.3961f, 26.6427f, 25.54f, 26.4125f)
                lineTo(28.9f, 23.0537f)
                curveTo(29.016f, 22.9376f, 29.1081f, 22.7998f, 29.1708f, 22.648f)
                curveTo(29.2336f, 22.4963f, 29.2659f, 22.3337f, 29.2658f, 22.1696f)
                curveTo(29.2658f, 22.0054f, 29.2334f, 21.8428f, 29.1705f, 21.6911f)
                curveTo(29.1076f, 21.5395f, 29.0155f, 21.4017f, 28.8993f, 21.2856f)
                curveTo(28.7832f, 21.1696f, 28.6453f, 21.0775f, 28.4936f, 21.0147f)
                curveTo(28.3419f, 20.952f, 28.1793f, 20.9197f, 28.0152f, 20.9197f)
                curveTo(27.851f, 20.9198f, 27.6884f, 20.9522f, 27.5367f, 21.0151f)
                curveTo(27.3851f, 21.078f, 27.2473f, 21.1701f, 27.1312f, 21.2862f)
                lineTo(25.7312f, 22.6862f)
                verticalLineTo(13.25f)
                curveTo(25.7312f, 12.9185f, 25.5995f, 12.6005f, 25.3651f, 12.3661f)
                curveTo(25.1307f, 12.1317f, 24.8127f, 12.0f, 24.4812f, 12.0f)
                curveTo(24.1497f, 12.0f, 23.8318f, 12.1317f, 23.5973f, 12.3661f)
                curveTo(23.3629f, 12.6005f, 23.2312f, 12.9185f, 23.2312f, 13.25f)
                verticalLineTo(25.6875f)
                verticalLineTo(25.75f)
                horizontalLineTo(23.23f)
                close()
                moveTo(20.5183f, 17.4978f)
                curveTo(20.6816f, 17.7145f, 20.77f, 17.9786f, 20.77f, 18.25f)
                verticalLineTo(18.3125f)
                verticalLineTo(30.75f)
                curveTo(20.77f, 31.0815f, 20.6383f, 31.3995f, 20.4039f, 31.6339f)
                curveTo(20.1694f, 31.8683f, 19.8515f, 32.0f, 19.52f, 32.0f)
                curveTo(19.1884f, 32.0f, 18.8705f, 31.8683f, 18.6361f, 31.6339f)
                curveTo(18.4017f, 31.3995f, 18.27f, 31.0815f, 18.27f, 30.75f)
                verticalLineTo(21.3138f)
                lineTo(16.8687f, 22.7138f)
                curveTo(16.7534f, 22.8331f, 16.6155f, 22.9284f, 16.463f, 22.9939f)
                curveTo(16.3105f, 23.0594f, 16.1464f, 23.0939f, 15.9805f, 23.0953f)
                curveTo(15.8145f, 23.0968f, 15.6499f, 23.0651f, 15.4963f, 23.0023f)
                curveTo(15.3427f, 22.9394f, 15.2031f, 22.8466f, 15.0857f, 22.7292f)
                curveTo(14.9684f, 22.6119f, 14.8755f, 22.4723f, 14.8127f, 22.3187f)
                curveTo(14.7498f, 22.1651f, 14.7182f, 22.0005f, 14.7197f, 21.8345f)
                curveTo(14.7211f, 21.6685f, 14.7556f, 21.5045f, 14.8211f, 21.352f)
                curveTo(14.8866f, 21.1995f, 14.9818f, 21.0616f, 15.1012f, 20.9462f)
                lineTo(18.46f, 17.5875f)
                curveTo(18.6038f, 17.3573f, 18.8187f, 17.1802f, 19.0721f, 17.083f)
                curveTo(19.3255f, 16.9857f, 19.6037f, 16.9736f, 19.8646f, 17.0484f)
                curveTo(20.1255f, 17.1233f, 20.3549f, 17.281f, 20.5183f, 17.4978f)
                close()
            }
        }
            .build()
        return _icExchange!!
    }

private var _icExchange: ImageVector? = null

@Preview
@Composable
fun IcExchangePreview() {
    CurrencyIcons.IcExchange
}