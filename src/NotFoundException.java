public class NotFoundException extends Exception {
    public static final int FILE_NOT_FOUND = 1;
    public static final int DIRECTORY_NOT_FOUND = 2;

    private String errorMessage;

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundException(int code) {
        if (code == 1) { //File not found
            this.errorMessage = "Error: File not found";
        }
        if (code == 2) { //Directory not found
            this.errorMessage = "Error: Directory not found";
        }
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}