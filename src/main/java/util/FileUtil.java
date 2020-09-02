package util;

import java.io.File;

public class FileUtil {


    public static String getFileName(File file) {
        String fullName = file.getName();
        int ind = fullName.lastIndexOf('.');
        return ind == -1 ? fullName : fullName.substring(0, ind);
    }



    private FileUtil() {}
}
