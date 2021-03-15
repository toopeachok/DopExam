package ru.milya.gui.components

import java.awt.Color
import javax.swing.*
import javax.swing.border.EtchedBorder

class ControlPanel : JPanel(){
    var colors = arrayOf("BLUE", "RED", "YELLOW", "BLACK")
    private val lXMin: JLabel
    private val lXMax: JLabel
    private val lYMin: JLabel
    private val lYMax: JLabel

    val colorChooser = JColorChooser()
    val chButton1= JButton("Цвет графика")
    val chButton2= JButton("Цвет производной")

    private var color1= Color.BLUE
    fun getColor1():Color {return color1}

    private var color2= Color.RED
    fun getColor2():Color {return color2}private val sSpinner :JSpinner
    private val smSpinner: SpinnerListModel

    private val valChangeListeners = mutableListOf<()->Unit>()
    private val colorListeners = mutableListOf<()->Unit>()
    private val val1Listeners = mutableListOf<()->Unit>()
    private val val2Listeners = mutableListOf<()->Unit>()



    private val sXMin: JSpinner
    private val sXMax: JSpinner
    private val sYMin: JSpinner
    private val sYMax: JSpinner

    val smXMin: SpinnerNumberModel
    val smXMax: SpinnerNumberModel
    val smYMin: SpinnerNumberModel
    val smYMax: SpinnerNumberModel



    companion object{
        private val MIN_SZ = GroupLayout.PREFERRED_SIZE
        private val MAX_SZ = GroupLayout.DEFAULT_SIZE
    }

    //private val valChangeListeners = mutableListOf<()->Unit>()
    //private val colorListeners = mutableListOf<()->Unit>()

    init{
        border = EtchedBorder()

        smSpinner = SpinnerListModel(colors)
        smSpinner.addChangeListener{

        }
        sSpinner =JSpinner(smSpinner)



        lXMin = JLabel()
        lXMax = JLabel()
        lYMin = JLabel()
        lYMax = JLabel()
        lXMin.text = "Xmin:"
        lXMax.text = "Xmax:"
        lYMin.text = "Ymin:"
        lYMax.text = "Ymax:"

        smXMin = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        smXMax = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        smYMin = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        smYMax = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)

        smXMin.addChangeListener{
            smXMax.minimum = smXMin.number.toDouble() + 0.1
            valChangeListeners.forEach { it() }
        }
        smXMax.addChangeListener{
            smXMin.maximum = smXMax.number.toDouble() - 0.1
            valChangeListeners.forEach { it() }
        }
        smYMin.addChangeListener{
            smYMax.minimum = smYMin.number.toDouble() + 0.1
            valChangeListeners.forEach { it() }
        }
        smYMax.addChangeListener{
            smYMin.maximum = smYMax.number.toDouble() - 0.1
            valChangeListeners.forEach { it() }
        }
        chButton1.addActionListener {
            run {
                color1 = JColorChooser.showDialog(this, "Select a color", Color.RED)
                colorListeners.forEach { l -> l() }
            }
        }
        chButton2.addActionListener {
            run {
                color2 = JColorChooser.showDialog(this, "Select a color", Color.RED)
                colorListeners.forEach { l -> l() }
            }
        }

        sXMin = JSpinner(smXMin)
        sXMax = JSpinner(smXMax)
        sYMin = JSpinner(smYMin)
        sYMax = JSpinner(smYMax)

        val BtnY = JButton()
        BtnY.text = "Явно заданная функция"
        val BtnT = JButton()
        BtnT.text = "Параметрически заданная функция"
        val bgClr = ButtonGroup()
        bgClr.add(BtnY)
        bgClr.add(BtnT)
        BtnY.addActionListener {
            val1Listeners.forEach { it() }
        }

        BtnT.addActionListener {
            val2Listeners.forEach { it() }
        }
        val gl = GroupLayout(this)
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(BtnY,MIN_SZ,MIN_SZ,MIN_SZ)
                                .addComponent(BtnT,MIN_SZ,MIN_SZ,MIN_SZ)
                )
                .addGap(8)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(lXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(lYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
        )

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(4)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(BtnY,MIN_SZ,MIN_SZ,MIN_SZ)
                                        .addComponent(lXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(lYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(2)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(sXMin, MIN_SZ, 100, MAX_SZ)
                                        .addComponent(sYMin, MIN_SZ, 100, MAX_SZ)
                        )
                        .addGap(8, 8, Int.MAX_VALUE)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(BtnT,MIN_SZ,MIN_SZ,MIN_SZ)
                                        .addComponent(lXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(lYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(2)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(sXMax, MIN_SZ, 100, MAX_SZ)
                                        .addComponent(sYMax, MIN_SZ, 100, MAX_SZ)
                        )
                        .addGap(4)
        )
        layout = gl
    }

    fun addVal1Listener(l: ()->Unit){
        val1Listeners.add(l)
    }

    fun addVal2Listener(l: ()->Unit){
        val2Listeners.add(l)
    }
    fun addValChangeListener(l: ()->Unit){
        valChangeListeners.add(l)
    }


    fun removeValChangeListener(l: ()->Unit){
        valChangeListeners.remove(l)
    }
    fun addColorListener(l: () -> Unit){
        colorListeners.add(l)
    }
    fun removeColorListener(l: () -> Unit){
        colorListeners.remove(l)
    }
}
