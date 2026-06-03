package sujeet.classes.learn.poly;

// Sujeet
public abstract class Animal extends Life {
    
    // Datamembers
    
    // Methods
    
    // inherited methods - grow(), ...
    // inheritance --> code-reuse
    
    public void speak() {
        System.out.println("Animals cannot speak! My age is: " + this.getAge());
    }
    
} 