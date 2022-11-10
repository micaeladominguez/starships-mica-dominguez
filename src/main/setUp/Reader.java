package main.setUp;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Reader {
    public static void main1(String[] args) {
        try {
            FileReader reader = new FileReader("src/main/setUp/MyFile.txt");
            int character;

            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main2(String[] args) {
        try {
            FileReader reader = new FileReader("src/main/setUp/MyFile.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main3(String[] args) {
        try {
            FileWriter writer = new FileWriter("src/main/setUp/MyFile.txt", true);
            writer.write("\r\n");   // write new line
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("src/main/setUp/MyFile.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            resetFile();
            bufferedWriter.newLine();
            bufferedWriter.write("Hello World");
            bufferedWriter.newLine();
            bufferedWriter.write("See You Again!");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void resetFile() throws IOException {
        File temp = new File("src/main/setUp/MyFile.txt");
        if (temp.exists()) {
            RandomAccessFile raf = new RandomAccessFile(temp, "rw");
            raf.setLength(0);
        }
    }
}
