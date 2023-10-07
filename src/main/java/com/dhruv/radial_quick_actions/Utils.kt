package com.dhruv.radial_quick_actions

import android.graphics.Point
import androidx.compose.runtime.Composable
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt


fun calculateAngle(a: Point, b: Point, c: Point): Double {
    val ab: Double = sqrt((b.x - a.x).toDouble().pow(2.0) + (b.y - a.y).toDouble().pow(2.0))
    val bc: Double = sqrt((c.x - b.x).toDouble().pow(2.0) + (c.y - b.y).toDouble().pow(2.0))
    val ac: Double = sqrt((c.x - a.x).toDouble().pow(2.0) + (c.y - a.y).toDouble().pow(2.0))
    val ratio : Double = (ab * ab + ac * ac - bc * bc) /( 2 * ac * ab)
    var degre = acos(ratio) *(180/Math.PI)
    if(c.y > b.y) degre = 360 - degre
    return degre
}

fun getAngles(n:Int): MutableList<Float> {
    val angles = mutableListOf<Float>()
    val min = 180F/( if(n <= 1) 1 else n-1 )
    var a = -min
    for(i in 0 until n){
        a += min
        angles.add(a)
    }
    return angles
}