package ru.milya.ru.milya.gui.graphics

import ru.milya.gui.graphics.CartesianPainter
import ru.milya.gui.graphics.Painter
import ru.milya.gui.graphics.convertation.Converter
import ru.milya.polynoms.Newton
import java.awt.Color
import java.awt.Graphics

class DerivativePainter(val dp: CartesianPainter) : Painter {
    var graf = Newton()
    private var mas : MutableMap<Double,Double>
    init{
        mas = mutableMapOf()
    }

    override fun paint(g: Graphics?) {
        PolynomPaint(g)

    }
    private fun PolynomPaint(g: Graphics?) {
        if (g != null) {
            var i = (dp.plane.xMin * 10)
            while(i<(dp.plane.xMax * 10).toInt()){
                i+=0.1
                g.drawLine(Converter.xCrt2Scr(i,dp.plane),
                        Converter.yCrt2Scr(graf.invoke(i),dp.plane),
                        Converter.xCrt2Scr((i+0.1),dp.plane),
                        Converter.yCrt2Scr(graf.invoke((i+0.1).toDouble()),dp.plane))
            }
        }
    }

}
