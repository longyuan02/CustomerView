动画布局:CanvasLightLine

MainActivity -->
//父布局
        ConstraintLayout cl = findViewById(R.id.cl);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dipToPx(this, 100), dipToPx(this, 50));
        CanvasLightLine canvasLightLine = CanvasLightLine.create(this).setInputLayoutParams(params).setAction(true);
        cl.addView(canvasLightLine);


values--> attrs.xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="CanvasLightLine">
        <attr name="sideColor" format="color" />
        <attr name="setRadius" format="integer" />
        <attr name="setSpeed" format="integer" />
        <attr name="setSidePadding" format="integer" />
    </declare-styleable>
</resources>


# CustomerView
# git@github.com:longyuan02/CustomerView.git

#### 图片裁剪 旋转
   * MatrixView.class
     ** 使用api PathMeasure

