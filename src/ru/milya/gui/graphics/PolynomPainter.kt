package ru.milya.ru.milya.gui.graphics

import ru.milya.gui.graphics.CartesianPainter
import ru.milya.gui.graphics.Painter
import ru.milya.gui.graphics.convertation.Converter
import ru.milya.polynoms.Newton
import ru.milya.polynoms.Polynom
import ru.milya.ru.milya.polynoms.Derivative
import java.awt.Color
import java.awt.Graphics

class PolynomPainter(dp1: CartesianPainter, n: Polynom ) : Painter{
    var graf = Polynom()
    var dp: CartesianPainter
   // var der = Derivative(doubleArrayOf(0.0))

    init{
        dp=dp1
        graf = n

    }


    override fun paint(g: Graphics?) {

        //PointPaint(g)
        PolynomPaint(g)
        ///der = Derivative(graf.coeficients)
        //DerivativePaint(g)


    }
   /* fun addPoint(x: Double, y: Double){
        if(!mas.containsKey(x)) {

            graf.add(Pair(x, y))
            mas[x] = y
        }
    }

    fun deletePoint(x: Double, y: Double){
        if(mas.containsKey(x)) {

            mas.remove(x)
            if(mas.size>0) {
                graf = Newton(mas)
            }else graf =Newton()
        }
    }
    */

    /*
    fun contain(x: Double): Boolean{
        var cont = false
        for (i in Converter.xCrt2Scr(x, dp.plane )-10..Converter.xCrt2Scr(x, dp.plane )+10){
            if(mas.containsKey(Converter.xScr2Crt(i, dp.plane))){
                cont = true
            }
        }
        return cont

    }
     */
/*
    private fun PointPaint(g:Graphics?){
        if(g!=null){
            for(i in graf){
                g.color = Color.CYAN
              g.fillOval(Converter.xCrt2Scr(i.key, dp.plane)-3,Converter.yCrt2Scr(i.value, dp.plane)-3,
                        6,6)

            }
        }
    }

 */
    private fun PolynomPaint(g:Graphics?) {
        if (g != null) {
            var i = (dp.plane.xMin)
            while(i<(dp.plane.xMax ).toInt()){
                i+=0.001
                g.drawLine(Converter.xCrt2Scr(i,dp.plane),
                        Converter.yCrt2Scr(graf.invoke(i),dp.plane),
                        Converter.xCrt2Scr((i+0.001),dp.plane),
                        Converter.yCrt2Scr(graf.invoke((i+0.001)),dp.plane))
            }


        }
    }
    fun repaint( dif: Derivative){
        graf.coef=dif.coeficients.clone()
    }

    /* private fun DerivativePaint(g:Graphics?){
         if (g != null) {
             g.color=Color.RED
             var i = (dp.plane.xMin )
             while(i<(dp.plane.xMax ).toInt()){
                 i+=0.001
                 g.drawLine(Converter.xCrt2Scr(i,dp.plane),
                         Converter.yCrt2Scr(der.invoke(i),dp.plane),
                         Converter.xCrt2Scr((i+0.001),dp.plane),
                         Converter.yCrt2Scr(der.invoke((i+0.001)),dp.plane))
             }
             g.color=Color.BLUE
         }
     }


     */

}