package main.java;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        try {
            String folderPath = "E:\\Pic\\Images_OpenGameArt";
            long sizeLimit = 10 * 1024 * 1024;
            Node.setLimitSize(sizeLimit);

            File file = new File(folderPath);
            Node root = new Node(file);

            long start = System.currentTimeMillis();

            FolderSizeCalculator calculator =
                    new FolderSizeCalculator(root);
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(calculator);

            System.out.println(root);

            long duration = (System.currentTimeMillis() - start);
            System.out.println(duration + " ms");

        } catch (IllegalArgumentException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
