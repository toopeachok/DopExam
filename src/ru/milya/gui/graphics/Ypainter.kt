package ru.milya.ru.milya.gui.graphics

import ru.milya.gui.graphics.CartesianPainter
import ru.milya.gui.graphics.Painter
import ru.milya.gui.graphics.convertation.Converter
import ru.milya.polynoms.Polynom
import ru.milya.ru.milya.polynoms.Derivative
import java.awt.Graphics
import kotlin.math.abs

class Ypainter(dp1: CartesianPainter, bar:(Double) -> Double): Painter {
    //bar: (Double) -> Double
    var dp: CartesianPainter
    fun y(x:Double):Double{
       return ((x*x+1)/(4-x*x))
    }
    init{

        dp=dp1
    }
    override fun paint(g: Graphics?) {
        PolynomPaint(g)
    }
    private fun PolynomPaint(g:Graphics?) {
        if (g != null) {
            var i = (dp.plane.xMin)
            while(i<(dp.plane.xMax ).toInt()){
                i+=0.001
                if( abs(Converter.xCrt2Scr(i,dp.plane) - Converter.xCrt2Scr(2.0,dp.plane))>0.01&&
                    abs(Converter.xCrt2Scr(i,dp.plane) - Converter.xCrt2Scr(-2.0,dp.plane))>0.01
                    ){
                g.drawLine(
                    Converter.xCrt2Scr(i,dp.plane),
                    Converter.yCrt2Scr(y(i),dp.plane),
                    Converter.xCrt2Scr((i+0.001),dp.plane),
                    Converter.yCrt2Scr(y(i+0.001),dp.plane))
            }
            }


        }
    }


}