package src;

import java.io.FileWriter;
import java.io.IOException;

class FileWriterHelper {
    public synchronized void writeFile(String message) {
        try (FileWriter myWriter = new FileWriter("output.txt", true)) {
            myWriter.write(message + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}

