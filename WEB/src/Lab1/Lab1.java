package Lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Lab1 {
    static ArrayList<File> javaFiles = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File coreDirectory = new File("/Users/apple/Desktop/Directory_Lab1_WEB");

        for (File file : coreDirectory.listFiles()) {
            System.out.println(file.getName());
            if (file.getName().endsWith("java")) {
                javaFiles.add(file);
            }
            getAllJavaFiles(file);
        }

        Thread.sleep(1000);
        System.out.println("\nAll java files that we found in directory:");
        for (File f : javaFiles) {
            System.out.println(f);
        }

        File resultFolder = new File("/Users/apple/Desktop/resultFolder");
        resultFolder.mkdirs();
        File resultFile = new File("/Users/apple/Desktop/resultFolder/resultFile.java");
        resultFile.createNewFile();


        FileInputStream input = new FileInputStream(javaFiles.get(0));
        FileOutputStream output = new FileOutputStream("/Users/apple/Desktop/resultFolder/resultFile.java", true);

        while(input.available() > 0) {
            int data = input.read();
            output.write(data);
        }

        input.close();
        output.close();
    }

    public static void getAllJavaFiles(File file) {

        Runnable checkDirectory = () -> {
            for (File f : file.listFiles()) {
                System.out.println("check files in " + f.getParentFile() + " found: ");
                System.out.println(f.getName());
                if (f.getName().endsWith("java")) {
                    javaFiles.add(f);
                }
                getAllJavaFiles(f);
            }
        };

        if (file.isDirectory()) {
            System.out.println("New subdirectory was found: " + file.getName() + " in  " + file.getParent());
            Thread check = new Thread(checkDirectory);
            check.start();
        }
    }
}
