package c.s.t.bits;

import java.util.ArrayList;
import java.util.List;

/*
 * Key Take-aways!
 * 0. Java's int ==> 4 bytes (32 bits), and is a 2's complement representation.
 *
 * 1. >> operator copies sign bit always, to the right, along with the shifted bits.
 * Thus, 100000...0 >> 3 result into 111100...0
 *
 * 2. To right shift, without sign-bit interference, use >>>
 * Thus, 100000...0 >>> 3 result into 100100...0
 *
 * 3. To left shift, it's always <<, and no worries about the sign bit.
 *
 * 4. The values are not mutated by the binary operators: >>, <<, >>>, %, |, &
 *
 * XOR ==> ^
 * NOT ==> ~ (all bits flipped, including the sign bit)
 *
 */

/**
 *
 * @author sujeet
 *
 */
final class Query {
    public final int type;
    public final int index;

    /**
     *
     *
     *
     * @param type  can be 1 or 2
     * @param index can be >= 1 and <= 32
     */
    public Query(int type, int index) {
        if (type > 2 || type < 1) {
            throw new IllegalArgumentException(
                    type + " --> Invaild Type. Valid types: 1 and 2");
        }

        if (index < 1 || index > 32) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }

        this.type = type;
        this.index = index;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", this.type, this.index);
    }
}

/**
 *
 * TBD
 *
 * @author sujeet
 *
 */
public class BinaryGetSetProblem {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    public BinaryGetSetProblem() {
    }

    public int[] answerQuery(List<Query> queries, int num) {
        List<Integer> ret = new ArrayList<>();
        int bitArray = 0;
        for(Query q : queries) {
            switch(q.type) {
            case 1:
                bitArray = setBitToIndex(bitArray, q.index);
                printBin(bitArray);
                break;
            case 2:
                int fetched = checkBitToTheRightOfIndex(bitArray, q.index);
                ret.add(fetched);
            }
        }

        return ret.stream().mapToInt(a -> a.intValue()).toArray();
    }

    protected int setBitToIndex(int src, int index) {
        if (index < 0 || index > 32) {
            throw new IllegalArgumentException(
                    "index cannot be -ve, or more than 32");
        }
        int mask = 1 << (32 - index);
        src |= mask;
        return src;
    }

    protected int checkBitToTheRightOfIndex(int src, int index) {
        int mask = this.setBitToIndex(0, index);
        while (index <= 32) {
            if ((src & mask) != 0) {
                break;
            }
            mask >>>= 1;
            index++;
        }
        return index > 32 ? -1 : index;
    }

    public static void printBin(int integer) {
        printBin(integer, true);
    }

    public static void printBin(int integer, boolean padZeros) {
        String binaryString = Integer.toBinaryString(integer);
        int numZerosToLead = 32 - binaryString.length();

        StringBuilder sb = new StringBuilder();
        if (padZeros) {
            for (int i = 0; i < numZerosToLead; i++) {
                sb.append('0');
            }
        }
        sb.append(binaryString);

        System.out.println(integer + " -> \n" + sb.toString() + " Bit-size:"
                + binaryString.length());
    }
}
