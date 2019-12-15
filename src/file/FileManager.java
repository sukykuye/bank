package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static String delimeter = ",";

    //remove an account to the accounts.txt file
    public void removeLineFromFile(String fileName, String lineToRemove) {
        try {
            File file = new File(fileName);

            if (!file.isFile()) {
                System.out.println("Invalid file.");
                return;
            }

            File tempFile = new File(fileName + ".tmp");
            String line;

            FileReader fileR = new FileReader(file);
            FileWriter fileW = new FileWriter(tempFile, false);
            BufferedReader buffR = new BufferedReader(fileR);
            BufferedWriter buffW = new BufferedWriter(fileW);

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = buffR.readLine()) != null) {
                if (!line.trim().equals(lineToRemove)) {
                    buffW.write(line);
                    buffW.newLine();
                    buffW.flush();
                    ;
                }
            }
            fileR.close();
            fileW.close();
            buffR.close();
            buffW.close();

            //Delete the original file
            if (!file.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(file)) System.out.println("Could not rename file");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String[] parseLine(BufferedReader buffR) {
        String line;
        String splitLine[] = null;

        //keep reading each line until the line is null (EOF)
        try {
            //BufferedReader buffR = new BufferedReader(new FileReader(fileName));

            if ((line = buffR.readLine()) != null) {
                //skip empty lines
                if (line.length() != 0) {
                    //split each line based off a "," delimeter
                    splitLine = line.split(delimeter);

                    return splitLine;
                }
            }

            return null;
            //buffR.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[][] parseFile(String fileName) {
        String line;
        String splitLine[] = null;
        String splitFile[][] = null;
        int count = 0;

        //keep reading each line until the line is null (EOF)
        try {
            BufferedReader buffR = new BufferedReader(new FileReader(fileName));

            while ((line = buffR.readLine()) != null) {
                //skip empty lines
                if (line.length() == 0) continue;

                //split each line into 4 parts
                //Line Layout: Name, SSN, Type of Account, Initial Deposit, Initial Pin
                splitLine = line.split(delimeter);
                splitFile[count] = splitLine;
                count++;
            }

            buffR.close();
            return splitFile;
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
