package com.devix.employemanagement;

public class Test {
    public static void main(String[] args) {
        A a = new A();
        MyThread1 t1 = new MyThread1(a);
        MyThread2 t2 = new MyThread2(a);
        t1.start();
        t2.start();
        System.out.println("Try programiz.pro");
    }
}




class MyThread1 extends Thread{

    private A a;
    public MyThread1(A a){
        this.a= a;
    }
    public void run(){


        for(int j = 0; j<10000; j++){
            a.increment("Thread1");
        }
    }
}

class MyThread2 extends Thread{
    private A a;
    public MyThread2(A a){
        this.a= a;
    }
    // initiated run method for Thread
    public void run(){

        for(int j = 0; j<10000; j++){
            a.increment("Thread2");
        }
    }
}

class A{
    public  Integer i = 0;
    public synchronized  void increment(String threadName) {
        System.out.println(threadName + " " + i++);
    }
}