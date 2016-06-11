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
//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        list = new ArrayList<Circle>();
        list.clear();
        circle = new Circle();
        circle.setX(100);
        circle.setY(185);
        circle.setFlag(RIGHT);
        circle.setLINE(true);
        list.add(circle);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(25
                        );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(list.size()>1&&list.get(0).getX()>getWidth() ){
                       list.remove(0);
                    }
                    if(list.get(list.size()-1).getX()>=110 ) {
                        initData();
                    }
                    System.out.println(list.size());
                    for (int i =0;i<list.size();i++){
                        if(list.get(i).getLINE()){
                            loopDraw(i);
                        }else {
                            loopDown(i);
                        }
                        runDraw(i);
                    }
//                    System.out.println(list.size());
                }
            }
        }).start();
    }

    public void initData(){
        if(list.get(list.size()-1).getLINE()){
            circle = new Circle();
            circle.setX(100);
            circle.setY(344);
            circle.setFlag(RIGHT);
            circle.setLINE(false);
            list.add(circle);
        }else {
            circle = new Circle();
            circle.setX(100);
            circle.setY(185);
            circle.setFlag(RIGHT);
            circle.setLINE(true);
            list.add(circle);
        }
    }
    public void loopDraw(int i){
        switch (list.get(i).getX()){
            case 120:
                if(list.get(i).getY()>=132){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 158:
                if(list.get(i).getY()<=174){
                    list.get(i).setFlag(DOWN);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 224:
                if(list.get(i).getY()>=120){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 266:
                if(list.get(i).getY()<=160){
                    list.get(i).setFlag(DOWN);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 340:
                if(list.get(i).getY()>=100){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 392:
                if(list.get(i).getY()<=160){
                    list.get(i).setFlag(DOWN);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 502:
                if(list.get(i).getY()<=220){
                    list.get(i).setFlag(DOWN);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 575:
                if(list.get(i).getY()>=198){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
        }

    }
    public void loopDown(int i){
        switch (list.get(i).getX()){
            case 114:
                if(list.get(i).getY()>=292){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 158:
                if(list.get(i).getY()<=340){
                    list.get(i).setFlag(DOWN);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 222:
                if(list.get(i).getY()>=280){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 266:
                if(list.get(i).getY()<=318){
                    list.get(i).setFlag(DOWN);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 340:
                if(list.get(i).getY()>=260){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 394:
                if(list.get(i).getY()<=320){
                    list.get(i).setFlag(DOWN);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 504:
                if(list.get(i).getY()>=250){
                    list.get(i).setFlag(UP);
                }else {
                    list.get(i).setFlag(RIGHT);
                }
                break;
            case 575:
                if(list.get(i).getY()>=198){
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
                if(list.get(i).getX()>getWidth()){
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
