package sujeet.classes.learn.poly;

public class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("The Dog BARKED!!! MY AGE IS : " + this.getAge());
    }
}