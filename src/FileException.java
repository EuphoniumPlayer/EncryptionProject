public class FileException extends Exception {
    public static final int FILE_NOT_FOUND = 1;
    public static final int DIRECTORY_NOT_FOUND = 2;
    public static final int FILE_EXPLORER_ERROR = 3;

    private String errorMessage;

    public FileException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public FileException(Exception e) {
        this.errorMessage = e.getMessage();
    }

    public FileException(int code) {
        if (code == 1) { //File not found
            this.errorMessage = "File not found";
        }
        if (code == 2) { //Directory not found
            this.errorMessage = "Directory not found";
        }
        if (code == 3) {
            this.errorMessage = "File Explorer error";
        }
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}