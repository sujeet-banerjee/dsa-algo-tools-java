import sujeet.classes.learn.poly.Life;
import sujeet.classes.learn.poly.Animal;
import sujeet.classes.learn.poly.Lion;
import sujeet.classes.learn.poly.Dog;

// Main class
public class Execution {
  public static void main(String [] args) {
       
        // Animal
//        System.out.println("\nCreate an Abstract Animal instance ...!");
//        Animal randomAnimal = new Animal();
//        randomAnimal.speak();
       
        // Create Lion object
        System.out.println("\nCreate a Lion instance ...!");
        Animal lion= new Lion();
        lion.grow();
        System.out.println("\nLet the object speak...!");
        //lion.speak();
        System.out.println("\nGrow the object...!");
        lion.grow();
        lion.speak();
        
        // Malicious code, breaks encapsulation!!
        //        lion.age = 10000;
        // lion.speak();
        
        // Polymorphism: method executed on the object, not on the reference.
        // Create Dog object (Aritro)
        System.out.println("\nCreate a Dog ...!");
        Animal dog = new Dog();
        dog.grow();
        ((Animal)dog).speak();
        
    }
    
}




