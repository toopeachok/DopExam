package ru.milya.gui.components
import ru.milya.gui.graphics.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel : JPanel(){
    var painter: MutableList<Painter>
    init{
        painter = mutableListOf()
    }
    override fun paint(g: Graphics?) {
        super.paint(g)
        for( i in painter){
            i.paint(g)

        }
    }
    fun addPaint(p:Painter){
        painter.add(p)
    }
    fun removePaint(p:Painter){
        painter.remove(p)
    }




}

