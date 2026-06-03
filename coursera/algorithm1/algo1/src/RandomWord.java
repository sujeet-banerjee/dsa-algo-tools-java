import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int count = 0;
        String champ = "No Pick!";
        while (!StdIn.isEmpty()) {
            count++;
            String next = StdIn.readString();
            double p = 1.0 / count;
            boolean isNewChamp = StdRandom.bernoulli(p);
            // System.out.println(String.format("Word=%s; p=%f; dist=%b", next, p,
            // isNewChamp));
            champ = isNewChamp ? next : champ;
        }
        System.out.println(champ);
    }
}