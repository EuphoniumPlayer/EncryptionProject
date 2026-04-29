public class temptestsystem {
    public static void main(String[] args) {
        FileIO file = new FileIO();
        try {
            file.writeFile(2, 3, 4);
            for (String f : file.readFile()) {
                System.out.println(f);
            }
        } catch (FileException fileException) {

        }
    }
}