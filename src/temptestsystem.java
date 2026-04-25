import java.util.Scanner;

public class temptestsystem {
    static Scanner scanner = new Scanner(System.in);
    static FileIO io = new FileIO(".");
    public static void main(String[] args) {
        System.out.println(io.getFiles());
        System.out.println(io.getErrorLog());
    }
}
