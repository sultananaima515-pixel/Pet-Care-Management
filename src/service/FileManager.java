package service;

import java.io.*;

public class FileManager {

    public static void save(String fileName, String data) {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdir();
            }

            FileWriter fw =
                    new FileWriter(
                            "data/" + fileName
                    );

            BufferedWriter bw =
                    new BufferedWriter(fw);

            bw.write(data);

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving file: "
                            + e.getMessage()
            );
        }
    }

    public static String read(String fileName) {

        StringBuilder data =
                new StringBuilder();

        try {

            File file =
                    new File(
                            "data/" + fileName
                    );

            if (!file.exists()) {
                return "";
            }

            FileReader fr =
                    new FileReader(file);

            BufferedReader br =
                    new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {

                data.append(line);
                data.append("\n");
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error reading file: "
                            + e.getMessage()
            );
        }

        return data.toString();
    }
}