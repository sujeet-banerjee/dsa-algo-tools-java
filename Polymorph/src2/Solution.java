import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

abstract class Initiator {
}

class MyInitiator extends Initiator {

}

class YourInitiator extends MyInitiator {

}

abstract class BinaryOperator {
    public static final Map<String, BinaryOperator> 
         OP_TYPES = new HashMap<>();

    static {
      new Adder();
      new Subtractor();
      OP_TYPES.put("multiply", new Multiplier());
    }

    private final String name;
    public BinaryOperator(final String name) {
        this.name = name;
        OP_TYPES.put(name, this);
        System.out.println(String.format(
          ">> Registered %s as class: %s",
            name, this.getClass().getName()
          ));
    }

    public abstract float act(float op1, float op2);

    public static final BinaryOperator get(final String opType) {
        if(!OP_TYPES.containsKey(opType)) {
          throw new IllegalArgumentException(
            "Invalid Operator: " + opType);
        }
        return OP_TYPES.get(opType);
    }
}

final class Adder extends BinaryOperator {
    protected Adder() {
      super("add");
    }

    @Override
    public float act(float op1, float op2) {
        return op1 + op2;
    }
}


final class Subtractor extends BinaryOperator {
    Subtractor() {
      super("minus");
    }

    @Override
    public float act(float op1, float op2) {
        return op1 - op2;
    }
}

class Multiplier extends BinaryOperator {
    public Multiplier() {
      super("times");
    }

    @Override
    public float act(float op1, float op2) {
        return op1 * op2;
    }
}

final class Modulo extends Multiplier {
    
    @Override
    public float act(float op1, float op2) {
        return op1 * op2;
    }
}

public final class Solution {
   public static final String [] calculations = new String[] {
    "add 2.3 3.2",
    "minus 2 3",
    "multiply 10 10.0",
    "times 3.0 10",
    "power 2 3",
    "add 10, 20"
    // Add more below...
   };

   public static void main(String[] args) {
      // Hack
      BinaryOperator.OP_TYPES = new HashMap<>();

//      Multiplier.act(3.0f, 2.2f);

      new Multiplier().OP_TYPES.put("yyy", null);

      for(final String calculate: calculations) {
        String[] operation = calculate.split(" ");
        System.out.println(
          "\n--> DO " + 
          operation[1] + 
          " " +
          operation[0] + 
          " " +
          operation[2] + 
          " " +
          " ==> Result = "+
          BinaryOperator.get(operation[0]).act(
              Float.valueOf(operation[1]), 
              Float.valueOf(operation[2]) ) + 
          "\n"
        );
      }
      
   }
}
