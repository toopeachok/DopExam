package ru.milya.gui.graphics

import ru.milya.gui.graphics.convertation.CartesianScreenPlane
import ru.milya.gui.graphics.convertation.Converter
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.max

class CartesianPainter(val plane: CartesianScreenPlane) : Painter{
    private enum class SizeType(val value: Int){
        Small(1),Medium(3),Large(7)
    }


    override fun paint(g: Graphics?) {
        drawAxes(g)
        drawMark(g)
    }

    private val xZeroPos: List<Int>
        get(){
            val z = Converter.yCrt2Scr(0.0, plane)
            if (z<0||z >=plane.height){
                return listOf(0,plane.height)
            }else return listOf(z,z)
        }
    private val yZeroPos: List<Int>
        get(){
            val z = Converter.xCrt2Scr(0.0, plane)
            if (z<0||z >=plane.width){
                return listOf(0,plane.width)
            }else return listOf(z,z)
        }


    private fun drawAxes(g: Graphics?) {
        if (g!=null) {
            g.color = Color.BLACK
            g.drawLine(0,
                    Converter.yCrt2Scr(0.0, plane),
                    plane.width,
                    Converter.yCrt2Scr(0.0, plane)
            )
            g.drawLine(
                    Converter.xCrt2Scr(0.0, plane),
                    0,
                    Converter.xCrt2Scr(0.0, plane),
                    plane.height
            )
        }
    }
    private fun drawMark(g: Graphics?){
        if (g!=null) {
            //x


            var number = max(abs(plane.xMin), abs(plane.xMax))
            var s = 1
            while (number>10){
                s++
                number/=10
            }
            val f = pow(10.0,s.toDouble())

            for (i in  (plane.xMin*10).toInt()..(plane.xMax*10).toInt()) {
                g.color=Color.DARK_GRAY
                var d = SizeType.Large.value
                if ((i % (f/2).toInt())!=0 ) {
                    d = SizeType.Medium.value;
                }

                val x=Converter.xCrt2Scr((i/ 10.0),plane)
                if (f>10.0 && (i % (f/2).toInt() != 0 || i==0)) continue
                val (z1,z2) = xZeroPos
                if(z1==z2) {
                    g.drawLine(x, z1 - d, x, z1 + d) //туть
                }else{
                    g.drawLine(x, z1+1 - d, x, z1+1 + d) //туть
                    g.drawLine(x, z2+1 - d, x, z2+1 + d) //туть
                }
                if (i % (f/2).toInt() != 0 || i==0) continue
                xLabel(g,x,i/10.0)
                //линейка нужна с проверкой

               // g.drawString((i / 10.0).toString(), x - 8, y0 + 20)
            }
            //y+
            var number1 = max(abs(plane.yMin), abs(plane.yMax))
            var s1 = 1
            while (number1>10){
                s1++
                number1/=10
            }
            val f1 = pow(10.0,s1.toDouble())
            for (i in  (plane.yMin*10).toInt()..(plane.yMax*10).toInt()) {
                g.color=Color.DARK_GRAY
                var d = SizeType.Large.value
                if ((i % (f1/2).toInt())!=0 ) {
                    d = SizeType.Medium.value;
                }
                val y = Converter.yCrt2Scr((i / 10.0),plane)
                if (f1>10.0 && (i % (f1/2).toInt() != 0 || i==0)) continue
                val (z1,z2) = yZeroPos
                if(z1==z2) {
                    g.drawLine(z1 - d, y, z1 + d, y)
                }else{
                    g.drawLine(z1 - d, y, z1 + d, y)
                    g.drawLine(z2 - d, y, z2 + d, y)
                }
                if (i % (f1/2).toInt()!= 0 || i==0) continue
                yLabel(g,y,i/10.0)
               // g.drawString((i / 10.0).toString(), x0 + d, y + 5)
            }

        }
    }
    private fun xLabel(g:Graphics, pos:Int , value: Double ){
        g.font = Font("Cambria", Font.BOLD, 12)
        val m = g.fontMetrics
        g.color = Color.ORANGE
        val (z1,z2) = xZeroPos
        val dx= -m.getStringBounds(value.toString() , g).width.toInt()/2
        val dy = m.getStringBounds(value.toString(),g).height.toInt() + SizeType.Large.value +1
        if(z1!=z2){
            g.drawString(value.toString(),pos+dx,z1+dy)
            g.drawString(value.toString(),pos+dx,z2-dy)
        }else g.drawString(value.toString(),pos+dx,z1+dy)
    }
    private fun yLabel(g:Graphics, pos:Int , value: Double ){
        g.font = Font("Cambria", Font.BOLD, 12)
        val m = g.fontMetrics
        g.color = Color.ORANGE
        val (z1,z2) = yZeroPos
        val dx= 2+SizeType.Large.value

        val dy = m.getStringBounds(value.toString(),g).height.toInt()/3
        if(z1!=z2){
            g.drawString(value.toString(),z1+dx,pos+dy)
            g.drawString(value.toString(),z2-dx-m.getStringBounds(value.toString(),g).height.toInt() - 3,pos+dx)
        }else g.drawString(value.toString(),z1+dx,pos+dy)
    }

}

