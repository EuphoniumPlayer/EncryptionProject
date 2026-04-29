public class FileException extends Exception {
    public static final int FILE_NOT_FOUND = 1;
    public static final int DIRECTORY_NOT_FOUND = 2;
    public static final int FILE_EXPLORER_ERROR = 3;

    private String errorMessage;

    public FileException(String errorMessage) {
        super(errorMessage);
    }

    public FileException(Exception e) {
        super(e);
    }

    public FileException(String errorMessage, boolean state) {
        if (state) {
            this.errorMessage = "Unexpected error: " + errorMessage;
        }
    }

    public FileException(int code) {
        if (code == 1) { //File not found
            this.errorMessage = "File not found.";
        }
        if (code == 2) { //Directory not found
            this.errorMessage = "Directory not found.";
        }
        if (code == 3) {
            this.errorMessage = "File Explorer error. Please try again.";
        }
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}