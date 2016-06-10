package bean;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuheng.lv on 2016/6/10.
 */
public class AnimaotionView extends View {
    private Paint paint;
    private final int UP=0;
    private final int RIGHT=1;
    private final int DOWN=2;
    private int flag = 1;
    private List<Circle> list;
    private Circle circle ;
    private int born =1;

    public AnimaotionView(Context context, AttributeSet attrs) {
        super(context,attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        list = new ArrayList<Circle>();
        initData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(list.get(0).getY()<0){
                        born =0;
                    }
                    if(list.get(list.size()-1).getX()>=135 && born ==1) {
                        initData();
                    }
                    for (int i =0;i<list.size();i++){
                        loopDraw(i);
                        runDraw(i);
                    }
                    System.out.println(list.size());
                }
            }
        }).start();
    }

    public void initData(){
        circle = new Circle();
        circle.setX(120);
        circle.setY(300);
        circle.setFlag(RIGHT);
        list.add(circle);
    }
    public void loopDraw(int i){
        switch (list.get(i).getX()){
            case 135:
                if(list.get(i).getY()>255){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 185:
                if(list.get(i).getY()>255){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
        }



    }

    public void runDraw(int i){
        switch(list.get(i).getFlag()){
            case UP:
                if(list.get(i).getY()<0){
                    list.get(i).setFlag(RIGHT);
                    list.get(i).setX(120);
                    list.get(i).setY(300);
                    break;
                }
                list.get(i).setY(list.get(i).getY()-1);
                postInvalidate();
                break;
            case DOWN:
                list.get(i).setY(list.get(i).getY()+1);
                postInvalidate();
                break;
            case RIGHT:
                list.get(i).setX(list.get(i).getX()+1);
                postInvalidate();
                break;
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<list.size();i++){
            canvas.drawCircle(list.get(i).getX(),list.get(i).getY(),4,paint);
        }

    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }
}
