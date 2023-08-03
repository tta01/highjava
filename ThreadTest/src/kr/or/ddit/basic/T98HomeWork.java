package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T98HomeWork {

    // 리스트 생성
    static List<Horse> horseList = new ArrayList<>();
    // flag 변수 생성
    public static boolean isStopped;

    public static void main(String[] args) {

        // 객체 10개 생성
        for (int i = 1; i <= 10; i++) {
            horseList.add(new Horse(i + "번말"));
        }

        // 스레드 생성
        for (Horse horse : horseList) {
            horse.start();
        }

        Thread race = new Race();
        race.start();

        // endTime 값이 있을때 카운트
        // while (!isStopped) {
        //     int cnt = 0;
        //     for (Horse horse : horseList) {
        //         if (horse.endTime != 0) {
        //             cnt++;
        //         }
        //         if (cnt == 10) {
        //             isStopped = true;
        //         }
        //     }
        // }
        
        // isAlive() 사용
        while (!isStopped) {
            int cnt = 0;
            for (Horse horse : horseList) {
                if (!horse.isAlive()) {
                    cnt++;
                }
                if (cnt == 10) {
                    isStopped = true;
                }
            }
        }
    }

}

class Horse extends Thread implements Comparable<Horse> {

    private int rank;

    public String race = ">-------------------------------------------------";

    public long endTime = 0; 

    public Horse(String name) {
        super(name);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    public long getEndTime() {
        return this.endTime;
    }

    @Override
    public int compareTo(Horse horse) {
        return (int) (this.getEndTime() - horse.getEndTime());
    }

    @Override
    public void run() {
        char[] arr = race.toCharArray();
        for (int i = 1; i < 50; i++) {
            arr[i] = '>';
            arr[i - 1] = '-';

            try {
                Thread.sleep((int) (Math.random() * 801) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            race = new String(arr);
        }
        endTime = System.currentTimeMillis();
    }

}

class Race extends Thread {

    @Override
    public void run() {
        while (!T98HomeWork.isStopped) {
            for (int i = 0; i < T98HomeWork.horseList.size(); i++) {
                System.out.printf("%5s%55s", T98HomeWork.horseList.get(i).getName(),
                        T98HomeWork.horseList.get(i).race + "\n");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(T98HomeWork.horseList);
        for (int i = 0; i < T98HomeWork.horseList.size(); i++) {
            T98HomeWork.horseList.get(i).setRank(i + 1);
        }
        for (Horse horse : T98HomeWork.horseList) {
            System.out.printf("%5s%6s%2d", horse.getName(), " 등수 : ", horse.getRank());
            System.out.println("등");
        }

    }

}
