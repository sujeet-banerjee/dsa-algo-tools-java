package sujeet.classes.learn.poly;

// Aritro
public abstract class Life {
    // Data members (protected, public, default can be inheritec)
    protected int age;
    
    // Methods
    // <modified> 
    public void grow() {
        incrementAge();
    }
    
    // Getter / setters
    public int getAge() {
       return age; 
    }
    
    private void incrementAge() {
        this.age++;
    }
    
}