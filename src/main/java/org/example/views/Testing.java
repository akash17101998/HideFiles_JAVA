package org.example.views;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Testing {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int i=5;
            @Override
            public void run() {
                if (i>0){
                    System.out.println(i);
                    i--;
                }else {
                    System.out.println("Wish You Very Happy Birthday!!");
                    timer.cancel();
                }
            }
        };
        Calendar date = Calendar.getInstance();
//        date.getTime();
        timer.scheduleAtFixedRate(task,date.getTime(),1000);


    }
}
