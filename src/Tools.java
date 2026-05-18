import java.awt.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Tools {
    public static final Font mainfont = new Font("Arial",Font.PLAIN,20);
    public boolean isprime(BigInteger in) {
        return in.isProbablePrime(20);
//        if (in.compareTo(BigInteger.ONE) <= 0) return false;
//
//        if (in.equals(BigInteger.TWO)) return true;
//
//        if (in.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return false;
//
//        BigInteger limit = in.sqrt();
//
//        for(BigInteger i = BigInteger.valueOf(3); i.compareTo(limit) <= 0; i = i.add(BigInteger.TWO)) {
//            if (in.mod(i).equals(BigInteger.ZERO)) return false;
//        }
//        return true;
    }
    public boolean isgcd1(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.remainder(b);
            a = temp;
        }//end while
        return a.equals(BigInteger.ONE);
    }

    public String encrypt(BigInteger key, BigInteger mod, String input) {
        List<BigInteger> output = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            BigInteger m = BigInteger.valueOf((int) ch);
            BigInteger c = m.modPow(key, mod);
            output.add(c);
        }
        return output.toString();
    }

    public String decrypt(BigInteger key, BigInteger mod, String input) {
        StringBuilder output = new StringBuilder();
        String[] split = input.split(", ");
        ArrayList<String> strlist = new ArrayList<String>(Arrays.asList(split));
        ArrayList<BigInteger> preoutlist = new ArrayList<>();
        for (String c : strlist) {
            preoutlist.add(BigInteger.valueOf(Integer.parseInt(c)));
        }
        for (BigInteger num : preoutlist) {
            BigInteger decrypt = num.modPow(key, mod);
            int deint = decrypt.intValue();
            output.append((char) deint);
        }
        return output.toString();
    }

    public BigInteger randomPrime() {
        SecureRandom random = new SecureRandom();
        BigInteger prime = BigInteger.probablePrime(512,random);
        return prime;
    }
}