import java.math.BigInteger;

public class temptestsystem {
    public static void main(String[] args) {
        Tools tool = new Tools();
        BigInteger primenum = tool.randomPrime(1024);
        System.out.println(primenum);
        if (tool.isprime(primenum)) System.out.println("prime"); else System.out.println("not prime");
        System.out.println("Done");
    }
}