import java.io.IOException;

public class NotVaildAutosarFileException extends IOException {
    NotVaildAutosarFileException()
    {
        super("invalid extension");
    }
}
