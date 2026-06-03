package trial.generics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TestGenerics {

    public static abstract class A {

    }

    public static final class B extends A {

    }

    public static final class C extends A {

    }

    public static abstract class AA extends A {

    }

    /*
     * We are mocking the GENERIC INSTANTIATION
     */
    public static final class D<T extends A> {
        private T[] ttt;
        private int size;
        private Class<T> clz;

        public D(int size, Class<T> clz) {
            this.size = size;
            this.clz = clz;
        }

        public void genericInstantiate() {
            // Sadly, we anyways have to rely on a runtime cast!
            ttt = (T[]) Array.newInstance(clz, size);
        }
    }

    public static void main(String[] args) {
        testGenericsInstantiationIssues1();
        System.out.println();
        testGenericsInstantiationIssues1B();
        System.out.println();
        testGenericsInstantiationIssues1D();
        System.out.println();
        testGenericsInstantiationIssues2();
        System.out.println();
        testGenericsInstantiationIssues3();
        System.out.println();
    }

    private static void testGenericsInstantiationIssues1() {
        try {
            List<A> listA = new ArrayList<A>();
            A a1 = new B();
            A a2 = new C();

            D d = new D(30, D.class);
            d.genericInstantiate();
            System.out.println("Worked!");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void testGenericsInstantiationIssues1B() {
        try {
            List<A> listA = new ArrayList<A>();
            A a1 = new B();
            A a2 = new C();

            D d = new D(30, B.class);
            d.genericInstantiate();
            System.out.println("Worked!");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void testGenericsInstantiationIssues1D() {
        try {
            List<A> listA = new ArrayList<A>();
            A a1 = new B();
            A a2 = new C();

            D d = new D(30, D.class);
            d.genericInstantiate();
            System.out.println("Worked!");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void testGenericsInstantiationIssues2() {
        try {
            A[] arrA = new A[10];

            A a1 = new B();
            A a2 = new C();
            // No compilable
            // List<A> listA = new ArrayList<B>();

            arrA[0] = (a2);
            arrA[1] = (a1);

            for (A a : arrA) {
                B b = (B) a;
                System.out.println(b);
            }
            System.out.println("Worked!");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void testGenericsInstantiationIssues3() {
        try {
            A[] arrA = new A[10];

            A a1 = new B();
            A a2 = new C();

            arrA[0] = (a2);
            arrA[1] = (a1);

            for (A a : arrA) {
                B b = (B) a;
                System.out.println(b);
            }
            System.out.println("Worked!");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
