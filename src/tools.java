import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class tools {
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
        List<BigInteger> preoutlist = new ArrayList<>();
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
//    public String process(String message, BigInteger key, BigInteger modulus) {
//        //TODO: fix
//        if (message == null || message.isEmpty()) {
//            return message;
//        }
//
//        StringBuilder result = new StringBuilder(message.length());
//
//        for (int i=0;i<message.length();i++) {
//            char c = message.charAt(i);
//            int code = c & 0xFFFF;
//
//            BigInteger value = BigInteger.valueOf(code);
//            BigInteger encoded = value.modPow(key, modulus);
//
//            int newCode = encoded.intValue();
//
//            result.append((char) newCode);
//        }
//        return result.toString();
//    }
}