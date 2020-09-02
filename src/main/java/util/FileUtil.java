package util;

import java.io.File;
import java.io.IOException;

public class FileUtil {


    public static String getFileName(File file) {
        String fullName = file.getName();
        int ind = fullName.lastIndexOf('.');
        return ind == -1 ? fullName : fullName.substring(0, ind);
    }


    /**
     * Get the canonical path to the parent directory of the destination file. If the directory doesn't
     * exist, create one or throw IOException if it cannot be created
     *
     * @param outputDir the parent directory of the destination file where the image will be written to
     * @return the canonical path to the parent directory of the destination file
     * @throws IOException thrown if the output directory is invalid,
     *                     or if the parent directory does not exist and cannot be created
     */
    public static String getCanonicalDir(String outputDir) throws IOException {

        File canonicalDir = new File(outputDir).getCanonicalFile();
        String canonicalPath = canonicalDir.getCanonicalPath();

        if (canonicalDir.exists()) {
            if (canonicalDir.isFile())
                throw new IOException("cannot write image into directory: " + canonicalPath);
        } else {
            if (!canonicalDir.mkdirs())
                throw new IOException("cannot create folder at: " + canonicalPath);
        }

        return canonicalPath;
    }



    private FileUtil() {}
}
