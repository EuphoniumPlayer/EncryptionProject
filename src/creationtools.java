import java.math.BigInteger;

public class creationtools {
    public boolean isprime(BigInteger in) {
        if (in.compareTo(BigInteger.ONE) <= 0) return false;

        for(BigInteger i = BigInteger.valueOf(2); i.compareTo(in.sqrt()) <= 0; i = i.add(BigInteger.ONE)) {
            if (in.mod(i).equals(BigInteger.ZERO)) return false;
        }
        return true;
    }
    public boolean isgcd1(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.remainder(b);
            a = temp;
        }//end while
        return a.equals(BigInteger.ONE);
    }
}