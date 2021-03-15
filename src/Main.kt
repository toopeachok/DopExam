package ru.milya

import ru.milya.gui.MainWindow
import ru.milya.polynoms.Lagrange
import ru.milya.polynoms.Polynom
import ru.milya.polynoms.Newton
import ru.milya.ru.milya.polynoms.Derivative
import java.lang.Math.exp


fun main(){
    println("Hello, World!")
    var x =9
    var y: Int
    val a = -2 //const, в том смысле что не меняется указатель на его область памяти, но значение внутри этой памяти можем менять
    //рекомендуется использовать val , если оно не будет меняться
    val c1= doubleArrayOf(1.0,3.0,3.0,5.0,0.0)
    val c2= doubleArrayOf(0.0,0.0,-3.0)
    val c3= doubleArrayOf(1.0,2.0,3.0)
    val p1 = Polynom(c1)
    val p2 = Polynom(c2)
    val p3 = p1+p2
    val l0 = Lagrange(mutableMapOf(0.0 to exp(3.0 / 4.0), 1/4.0 to exp(7/8.0),
            1/2.0 to exp(1.0),3/4.0 to exp(9/8.0), 1.0 to exp(5/4.0)))

val l1 = Newton(mutableMapOf(0.0 to 0.0))
    l1.add(-1.0 to 1.0)
   // l1.add(0.0 to 0.0)

    l1.add(1.0 to 1.0)
    println("-----------------------------------")
    println(p1)
   // val d1 = Derivative(c1)
   // println(d1)
    println("-----------------------------------")
    /*
    l1.add(0.0 to -1.0)
    l1.add(1.0 to 1.0)
    l1.add(0.5 to 1.0)
    -1.0 to 1.0,
    0.0 to 0.0,
    1.0 to 1.0
    */
    println(l1)
    println("-----")
    println(p1)
    println(p2)
    println(p2+p1)
    println(p1(3.0))
    val w =MainWindow()
    w.isVisible = true //чтобы показать окно

    //row reduce{{0;0;0;0;1;-0.00064},{1;4;16;64;256;-0.17784},{1;2;4;8;16;-0.011347},{1;1.33333;1.77778;2.37037;3.16049;-0.00228915},{1;1;1;1;1;-0.00073979}}
    println(l0)
}
/*
Написать класс расширяющий верхную панельку, она должна уметь в себе рисовать ( это отдельный класс)
панель должна выводить в себе что-то , что именно она выводит определяет другой класс - пока что это декартовая система координат
осуществить все таким образом, чтобы второй класс можно было заменить на другой , например, чтобы вместо него можно было его наследника постаить
класс реализовывает общий интерфейс
можно штуку , которая будет менять отображаемый шаг
шаг управляется другой панелькой - а это другой класс
надо сделать так чтобы между ними не было зависимости , можно через обработку событий
как рисовать ?
нужен класс конвертер
* */