//package com.example.customeranimor
//
//import android.content.Context
//import android.graphics.*
//import android.util.AttributeSet
//import android.view.MotionEvent
//import android.view.View
//
//class StateButton:View{
//    constructor(context: Context?) : super(context)
//    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
//    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
//
//
//    val p=Paint(Paint.ANTI_ALIAS_FLAG).apply {
//        style=Paint.Style.STROKE
//        strokeWidth=3f
//        color=Color.GRAY
//    }
//    val pMove=Paint().apply {
//        style=Paint.Style.STROKE
//        strokeWidth=3f
//        color=Color.RED
//    }
//    var radius=0f
//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
//        if(changed){
//            radius=Math.min(width,height)/2f-10
//            path.apply {
//                addCircle(0f,0f,radius,Path.Direction.CW)
//
//                addPath(Path().apply {
//                    moveTo(-radius+1,0f)
//                    quadTo(-radius/2f,-radius,0f,0f)
//                    quadTo(radius/2,radius,radius-1,0f)
//                })
//            }
//        }
//    }
//    var path=Path()
//    var move=100f
//    var pathMeasure=PathMeasure()
//    val pos= FloatArray(2)
//    val tan= FloatArray(2)
//    val pathPart=Path()
//    val pm=PathMeasure()
//    val pathTemp=Path()
//    private fun getDegree(x:Float,y:Float):Double{
//        return Math.toDegrees(Math.atan2(y.toDouble(), x.toDouble()))
//    }
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//
//        if(radius==0f){
//            return
//        }
//        if(pathMeasure.length==0f){
//            pathMeasure.setPath(path,false)
//        }
//
//        canvas.save()
//        canvas.translate(width/2f,height/2f)
//
//        p.color=Color.GRAY
//        canvas.drawPath(path,p)
//        p.color=Color.BLUE
//        pathPart.rewind()
//
//        pathMeasure.getSegment(move-100f,move,pathPart,false)
//        canvas.drawPath(pathPart,p)
//
//        pm.setPath(pathPart,false)
//        println("pathPart====${pm.length}========${move}")
//        p.color=Color.parseColor("#215980")
//
//        //起始点
//        pMove.color=Color.RED
//        pathMeasure.getPosTan(move-100,pos,tan)
//        canvas.drawCircle(pos[0],pos[1],3f,pMove)
//        val temp= floatArrayOf(pos[0],pos[1])
//        val tan2= floatArrayOf(tan[0],tan[1])
//        //画切线
//        canvas.save()
//        val degree=Math.toDegrees(Math.atan2(tan[1].toDouble(), tan[0].toDouble())).toFloat()
//        canvas.rotate(degree,pos[0],pos[1])
//        canvas.drawLine(pos[0]-100,pos[1],pos[0]+100,pos[1],p)
//        canvas.restore()
//
//        //结束点
//        pMove.color=Color.BLUE
//        pathMeasure.getPosTan(move,pos,tan)
//        canvas.drawCircle(pos[0],pos[1],3f,pMove)
//
//        //画切线
//        canvas.save()
//        val degree2=Math.toDegrees(Math.atan2(tan[1].toDouble(), tan[0].toDouble())).toFloat()
//        canvas.rotate(degree2,pos[0],pos[1])
//        canvas.drawLine(pos[0]-100,pos[1],pos[0]+100,pos[1],p)
//        canvas.restore()
//
//        //求2条切线的交点
//        var d1=getDegree(temp[0],temp[1])
//        var d2=getDegree(pos[0],pos[1])
//        val halfDegree=(d2-d1)/2.00
//        val rBig=radius/Math.cos(Math.toRadians(halfDegree))
//        println("degree=========$d1/$d2=======r:$rBig")
//        val bigDegree=d1+halfDegree
//        val rx=rBig*Math.cos(Math.toRadians(bigDegree))
//        val ry=rBig*Math.sin(Math.toRadians(bigDegree))
//
//        //切线的交点
//        pMove.color=Color.YELLOW
//        canvas.drawCircle(rx.toFloat(),ry.toFloat(),3f,pMove)
//
//        //画模拟的二阶贝塞尔曲线
//        pathTemp.apply {
//            reset()
//            moveTo(0f,0f)
//            quadTo(rx.toFloat(),ry.toFloat(),pos[0],pos[1])
//        }
//        canvas.drawPath(pathTemp,pMove)
//
//
//        canvas.restore()
//        move+=150f
//        if(move>pathMeasure.length){
//
//            if(!pathMeasure.nextContour()){
//                pathMeasure.setPath(path,false)
//                pathPart.reset()
//                pathPart.moveTo(radius,0f)
//            }
//            move=0f
//        }
//    }
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//
//        when(event.actionMasked){
//
//            MotionEvent.ACTION_DOWN->{
//                postInvalidateDelayed(50)
//            }
//        }
//        return super.onTouchEvent(event)
//    }
//}