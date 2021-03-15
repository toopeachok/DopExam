package ru.milya.ru.milya.polynoms

import ru.milya.polynoms.Newton
import ru.milya.polynoms.Polynom

class Derivative(newton: Newton): Polynom() {
    init{
        coef=newton.coeficients.clone()
        for (i in 0 until newton.coeficients.size-1){
            coef[i]= (i+1)*newton.coeficients[i+1]
        }
        coef[newton.coeficients.size-1]=0.0
        correcrPower()
    }

}