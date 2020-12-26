package classes;

import interfaces.IArchiveCreator;
import interfaces.ISender;

import java.io.*;
import java.nio.file.Paths;

/**
 * Класс Reporter формирует ZIP архив и отправляет его по электронной почте Gmail
 */
public class Reporter {
    public static void main(String[] args) {
        IArchiveCreator archiveCreator = new ZIPCreator();

        ISender sender = new GmailSender();

        StringBuilder stringBuilder = new StringBuilder();

        File fileOutput = new File(System.getProperty("user.dir")
                + File.separator + "output");

        if (!fileOutput.exists()) fileOutput.mkdir();

        File fileZIP = new File(fileOutput + File.separator + "report.zip");

        stringBuilder.append("ZIP (to: '").append(fileZIP.getName()).append("') -> ");

        if (archiveCreator.create(Paths.get(
                System.getProperty("user.dir") + File.separator
                        + "target" + File.separator + "site"), fileZIP)) {
            stringBuilder.append("Ok");

            File fileEmail = new File(System.getProperty("user.dir")
                    + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources" + File.separator
                    + "mails" + File.separator + "email.csv");

            try {
                BufferedReader bufferedReader =
                        new BufferedReader(new FileReader(fileEmail));

                String line;
                String[] values;

                while ((line = bufferedReader.readLine()) != null) {
                    values = line
                            .replaceAll("\\s+|'", "")
                            .replaceAll("[\\w]+:", "").split(",");

                    stringBuilder.append("\nEmail (to: '")
                            .append(values[2]).append("') -> ");

                    if (sender.send(values[0], values[1],
                            values[2], values[3], values[4], fileZIP)) {
                        stringBuilder.append("Ok");
                    } else {
                        stringBuilder.append("Error");
                    }
                }
            } catch (IOException e) {
                stringBuilder.append("\nFile '").append(fileEmail.getAbsolutePath())
                        .append("' not found...");
            }
        } else {
            stringBuilder.append("Error");
        }

        System.out.println(stringBuilder.toString());
    }
}
