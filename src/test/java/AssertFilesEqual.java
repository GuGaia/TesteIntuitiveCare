package test.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AssertFilesEqual {

    public static boolean assertFilesEqual(File file1, File file2) throws IOException {
        if (file1.length() != file2.length()) {
            return false;
        }
        return Files.mismatch(file1.toPath(), file2.toPath()) == -1;
    }
}
