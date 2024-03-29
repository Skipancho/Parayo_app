package com.e.parayo_app.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable

private fun borderBG(
    borderColor: String = "#1F000000",
    bgColor : String = "#FFFFFF",
    borderWidthLeft : Int = 0,
    borderWidthTop : Int = 0,
    borderWidthRight : Int = 0,
    borderWidthBottom : Int = 0,
):LayerDrawable{
    val layerDrawable = arrayOf<Drawable>(
        ColorDrawable(Color.parseColor(borderColor)),
        ColorDrawable(Color.parseColor(bgColor))
    ).let(::LayerDrawable)

    layerDrawable.setLayerInset(
        1,
        borderWidthLeft,
        borderWidthTop,
        borderWidthRight,
        borderWidthBottom
    )

    /*
    val drawables = arrayOf<Drawable>(
        ColorDrawable(Color.parseColor(borderColor)),
        ColorDrawable(Color.parseColor(bgColor))
    )

    val layerDrawable = LayerDrawable(drawables)
     */

    return layerDrawable
}

fun borderLeft(
    color: String = "#1F000000",
    width:Int
) = borderBG(
    borderColor = color,
    borderWidthLeft = width
)

fun borderTop(
    color: String = "#1F000000",
    width:Int
) = borderBG(
    borderColor = color,
    borderWidthTop = width
)

fun borderRight(
    color: String = "#1F000000",
    width:Int
) = borderBG(
    borderColor = color,
    borderWidthRight = width
)

fun borderBottom(
    color: String = "#1F000000",
    width:Int
) = borderBG(
    borderColor = color,
    borderWidthBottom = width
)