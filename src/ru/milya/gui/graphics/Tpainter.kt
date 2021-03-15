package ru.milya.ru.milya.gui.graphics

import ru.milya.gui.graphics.CartesianPainter
import ru.milya.gui.graphics.Painter
import ru.milya.gui.graphics.convertation.Converter
import ru.milya.polynoms.Polynom
import ru.milya.ru.milya.polynoms.Derivative
import java.awt.Graphics

class Tpainter(dp1: CartesianPainter ): Painter {
    var dp: CartesianPainter

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

                g.drawLine(

                    Converter.xCrt2Scr(x(i),dp.plane),
                    Converter.yCrt2Scr(y(i),dp.plane),
                    Converter.xCrt2Scr(x(i+0.001),dp.plane),
                    Converter.yCrt2Scr(y(i+0.001),dp.plane))
            }


        }
    }
    fun y(i:Double ):Double{
        return i*i+2*i
    }
    fun x(i:Double):Double{
        return i*i-2*i
    }



}