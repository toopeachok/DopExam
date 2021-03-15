package ru.milya.polynoms
class Newton():Polynom(){
    var xfx : MutableMap<Double,Double>
    var map: MutableMap <Pair<Int,Int>,Double>
    var pol = Polynom(doubleArrayOf(1.0))
    init{
        xfx = mutableMapOf()
        map = mutableMapOf()

    }
    constructor(xfx:MutableMap<Double,Double>):this(){
        this.xfx = xfx
        val p = Polynom(doubleArrayOf(xfx.values.elementAt(0))) //val use +=
        for (i in 1 until xfx.size){
            val r = difference(Pair(0,i))
            pol *=  Polynom(doubleArrayOf(-xfx.keys.elementAt(i-1),1.0))
            if(r!=null) p += pol*r
            else  break

        }
        coef=p.coeficients
    }


    private fun difference(key: Pair<Int, Int>) : Double{
        if (!map.containsKey(key)){
            var y1 :Double
            /*if(key.first==key.second){y1=xfx.values.elementAt(key.first)}
            else{*/
            if(Math.abs(key.first-key.second)==1){
                y1= (xfx.values.elementAt(key.second)-xfx.values.elementAt(key.first))/
                            (xfx.keys.elementAt(key.second)-xfx.keys.elementAt(key.first))
            }
            else {
                y1 =(difference(Pair(key.first+1,key.second)) -
                        difference(Pair(key.first,key.second-1)))/
                        (xfx.keys.elementAt(key.second)-xfx.keys.elementAt(key.first))
            }

            map.put(key,y1)
            return y1
        }else{
            return map.getValue(key)
        }
    }

    fun add(point:Pair<Double, Double>) {
        if (!xfx.containsKey(point.first)) {
            xfx.put(point.first, point.second)

            val s = xfx.size - 1
            if (s > 0) {
                val r = difference(Pair(0, s))
                pol *= Polynom(doubleArrayOf(-xfx.keys.elementAt(s - 1), 1.0))
                if (r != null) this += pol * r
            } else this += Polynom(doubleArrayOf(xfx.values.elementAt(s)))
        }
    }
    fun delete(point:Pair< Double,Double>){
        if(xfx.containsKey(point.first)) {
            xfx.remove(point.first)
            val n: Newton
            if(xfx.size>0) {
                n = Newton (xfx)

            }else {
                n = Newton()
            }
            coef = n.coef
        }
    }
    
//у меня не красивый код (

}


