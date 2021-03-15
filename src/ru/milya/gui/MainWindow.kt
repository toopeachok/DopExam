package ru.milya.gui

import ru.milya.gui.components.ControlPanel
import ru.milya.gui.components.GraphicsPanel
import ru.milya.gui.graphics.CartesianPainter
import ru.milya.gui.graphics.convertation.CartesianScreenPlane
import ru.milya.gui.graphics.convertation.Converter
import ru.milya.polynoms.Newton
import ru.milya.ru.milya.gui.graphics.PolynomPainter
import ru.milya.ru.milya.gui.graphics.Tpainter
import ru.milya.ru.milya.gui.graphics.Ypainter
import ru.milya.ru.milya.polynoms.Derivative
import java.awt.Color
import java.awt.Dimension
import java.awt.event.*
import javax.swing.GroupLayout
import javax.swing.JFrame

class MainWindow : JFrame(){

    private val minSize = Dimension(550, 400)
    private val mainPanel: GraphicsPanel
    private val controlPanel: ControlPanel
    private var graf = Newton()
    private var dif = Derivative(graf)
    private var mas : MutableMap<Double,Double>
    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(minSize.width+200, minSize.height+400)
        mainPanel = GraphicsPanel()
        mainPanel.background = Color.WHITE
        controlPanel = ControlPanel()
        mas = mutableMapOf()
        val gl = GroupLayout(contentPane)


        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addComponent(mainPanel, minSize.height, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(4)
        )

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(mainPanel, minSize.width, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4))
        layout = gl
        pack()
        val plane = CartesianScreenPlane(
                mainPanel.width, mainPanel.height,
                controlPanel.smXMin.number.toDouble(),
                controlPanel.smXMax.number.toDouble(),
                controlPanel.smYMin.number.toDouble(),
                controlPanel.smYMax.number.toDouble()
        )

        val dp = CartesianPainter(plane)
        mainPanel.addPaint(dp)

     //   val gp = PolynomPainter(dp, graf)
       // val gp1 = PolynomPainter(dp,dif)

       // mainPanel.addPaint(gp1)
        mainPanel.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                dp.plane.realWidth = mainPanel.width
                dp.plane.realHeight = mainPanel.height
                repaint()
                //mainPanel.update()
            }
        })
        fun y(double: Double){

        }

        val gp = Ypainter(
            dp,
            { h -> h * h }
        )
        val gp1 = Tpainter(dp)
        mainPanel.addPaint(gp)
        controlPanel.addVal1Listener {
            mainPanel.removePaint(gp1)
            mainPanel.addPaint(gp)
            mainPanel.repaint()

        }
        controlPanel.addVal2Listener {
            mainPanel.removePaint(gp)
            mainPanel.addPaint(gp1)
            mainPanel.repaint()
        }

        controlPanel.addValChangeListener {
            dp.plane.xMin = controlPanel.smXMin.number.toDouble()
            dp.plane.xMax = controlPanel.smXMax.number.toDouble()
            dp.plane.yMin = controlPanel.smYMin.number.toDouble()
            dp.plane.yMax = controlPanel.smYMax.number.toDouble()
            
            mainPanel.repaint()
        }
        controlPanel.addColorListener{
         //   mainPanel.setColor(controlPanel.getColor1())
           // derivednp.setColor(controlPanel.getColor2())
            mainPanel.paint(mainPanel.graphics)

        }

        //для нажатий
        mainPanel.addMouseListener(
                object : MouseAdapter(){
                    override fun mousePressed(e: MouseEvent) {
                        var mas = doubleArrayOf()
                        if(e.button==1) {
                            //ДОБАВИТЬ УЗЕЛ
                            graf.add(Pair(Converter.xScr2Crt(e.x, plane), Converter.yScr2Crt(e.y, plane)))
                            //gp.addPoint(Converter.xScr2Crt(e.x, plane), Converter.yScr2Crt(e.y, plane))
                            dif=Derivative(graf)
                           // gp1.repaint(dif)
                            mainPanel.repaint()
                        }
                        if(e.button==3){

                            graf.delete(Pair(Converter.xScr2Crt(e.x, plane), Converter.yScr2Crt(e.y, plane)))
                            dif=Derivative(graf)
                         //   gp1.repaint(dif)
                            mainPanel.repaint()
                        }
                    }
                }
        )

    }
}