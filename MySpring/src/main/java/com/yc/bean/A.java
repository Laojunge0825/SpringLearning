package com.yc.bean;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-05 20:20
 */
public class A {

        public static void main(String[] args) {
            int nDisks = 3;
            doTowers(nDisks, 'A', 'B', 'C');
        }
        public static void doTowers(int topN, char from, char inter, char to) {
            if (topN == 1){
                System.out.println("Disk 1 from "
                        + from + " to " + to);
            }else {
                doTowers(topN - 1, from, to, inter);
                System.out.println("Disk "
                        + topN + " from " + from + " to " + to);
                doTowers(topN - 1, inter, from, to);
            }
        }

}
