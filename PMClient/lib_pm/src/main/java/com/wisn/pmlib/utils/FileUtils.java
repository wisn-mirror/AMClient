package com.wisn.pmlib.utils;

import android.content.Context;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by wisn on 2017/9/8.
 */

public class FileUtils {

    /**
     * 拷贝单个文件，使用路径
     *
     * @param fromFilePath
     * @param toFilePath
     *
     * @return
     */
    public static boolean copyFile(String fromFilePath, String toFilePath) {
        if (fromFilePath == null || toFilePath == null) return false;
        return copyFile(new File(fromFilePath), new File(toFilePath));
    }

    /**
     * 拷贝单个文件到指定文件
     *
     * @param fromFile
     * @param toFile
     *
     * @return
     */
    public static boolean copyFile(File fromFile, File toFile) {
        if (fromFile == null || toFile == null) return false;
        try {
            if (!toFile.exists()) toFile.createNewFile();
            return copyFileStream(new FileInputStream(fromFile), new FileOutputStream(toFile));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 拷贝Assets文件到指定文件
     *
     * @param context
     * @param assetsFile
     * @param toDirPath
     * @param toFileName
     *
     * @return
     */
    public static boolean copyAssetsFileToFile(Context context,
                                               String assetsFile,
                                               String toDirPath,
                                               String toFileName) {
        if (assetsFile == null || context == null || toDirPath == null || toFileName == null) return false;
        try {
            File toFile = new File(toDirPath);
            if (!toFile.exists()) toFile.mkdirs();
            return copyFileStream(context.getAssets().open(assetsFile),
                                  new FileOutputStream(new File(toFile, toFileName)));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 拷贝Assets文件夹到指定文件夹
     *
     * @param context
     * @param assetsDir
     * @param toDirPath
     *
     * @return
     */
    public static boolean copyAssetsDirToDir(Context context, String assetsDir, String toDirPath) {
        if (assetsDir == null || context == null || toDirPath == null) return false;
        try {
            String[] assetsFiles = context.getAssets().list(assetsDir);
            if (assetsFiles == null || assetsFiles.length == 0) return false;
            for (String fileName : assetsFiles) {
                File toFile = new File(toDirPath);
                if (!toFile.exists()) {
                    toFile.mkdirs();
                }
                copyAssetsFileToFile(context, assetsDir + File.separator + fileName, toDirPath, fileName);

            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 拷贝文件夹
     *
     * @param fromDirPath
     * @param toDirPath
     *
     * @return
     */
    public static boolean copyDir(String fromDirPath, String toDirPath) {
        if (fromDirPath == null || toDirPath == null) return false;
        try {
            copyDir(new File(fromDirPath), new File(toDirPath), true, null, null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 拷贝文件夹不包含子目录
     *
     * @param fromDirPath
     * @param toDirPath
     *
     * @return
     */
    public static boolean copyDirUnIncludeSubDirs(String fromDirPath, String toDirPath) {
        if (fromDirPath == null || toDirPath == null) return false;
        try {
            copyDir(new File(fromDirPath), new File(toDirPath), false, null, null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 拷贝文件夹添加忽略文件
     *
     * @param fromDirPath
     * @param toDirPath
     * @param excludedList fileName
     *
     * @return
     */
    public static boolean copyDir(String fromDirPath, String toDirPath, List<String> excludedList) {
        if (fromDirPath == null || toDirPath == null) return false;
        try {
            copyDir(new File(fromDirPath), new File(toDirPath), true, excludedList, null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 拷贝文件夹中指定类型的文件
     *
     * @param fromDirPath
     * @param toDirPath
     * @param copyFileType eg: .apk .txt
     *
     * @return
     */
    public static boolean copyDirByFileType(String fromDirPath, String toDirPath, String copyFileType) {
        if (fromDirPath == null || toDirPath == null) return false;
        try {
            copyDir(new File(fromDirPath), new File(toDirPath), true, null, copyFileType);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 复制文件夹
     *
     * @param fromFile
     * @param toFile
     * @param includeSubdirs 是否包含子目录
     * @param excludedList   过滤文件夹
     *
     * @throws IOException
     */
    private static void copyDir(File fromFile,
                                File toFile,
                                boolean includeSubdirs,
                                List<String> excludedList, String copyFileType) throws IOException {
        if (!toFile.exists()) {
            toFile.mkdirs();
        }
        if (fromFile == null || !fromFile.isDirectory() || !toFile.isDirectory())
            return;
        File fs[] = fromFile.listFiles();
        if (fs == null)
            return;
        for (int i = 0; i < fs.length; i++) {
            String n = fs[i].getName();
            File c = new File(toFile, n);
            if (fs[i].isDirectory()) {
                if (!includeSubdirs || (excludedList != null && excludedList.contains(fs[i].getName()))) {
                    continue;
                }
                c.mkdirs();
                copyDir(fs[i], c, includeSubdirs, excludedList, copyFileType);
                continue;
            } else {
                if (copyFileType == null) {
                    copyFile(fs[i], c);
                } else {
                    if (n.endsWith(copyFileType)) {
                        copyFile(fs[i], c);
                    }
                }
            }
        }
    }


    /**
     * 拷贝流
     *
     * @param inputStream
     * @param outputStream
     *
     * @return
     */
    public static boolean copyFileStream(InputStream inputStream, OutputStream outputStream) {
        try {
            int index = 0;
            byte[] bytes = new byte[1024];
            while ((index = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, index);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null)
                        outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * @param file
     * @param contents
     * @param isAppend
     *
     * @throws IOException
     */
    public static void writeByteToFile(File file, byte[] contents, boolean isAppend) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!file.isFile()) {
            throw new IOException("File to be written not exist, file path : " + file.getAbsolutePath());
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file, isAppend);
            BufferedOutputStream bOutput = new BufferedOutputStream(fileOut);
            bOutput.write(contents);
            bOutput.flush();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param file
     * @param content
     * @param isAppend
     *
     * @throws IOException
     */
    public static void writeStringAsFileContent(File file, String content, boolean isAppend) throws
                                                                                             IOException {
        writeByteToFile(file, content.getBytes(), isAppend);
    }

    /**
     * @param file
     *
     * @return
     *
     * @throws IOException
     */
    public static String readFileContentAsString(File file) throws IOException {
        byte[] content = readFileContent(file);
        return new String(content);
    }

    /**
     * @param file
     *
     * @return
     *
     * @throws IOException
     */
    public static byte[] readFileContent(File file) throws IOException {
        if (!file.exists() || !file.isFile()) {
            throw new IOException("File to be readed not exist, file path : " + file.getAbsolutePath());
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] contents = null;
        try {
            copyFileStream(in, out);
            contents = out.toByteArray();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return contents;
    }

}
