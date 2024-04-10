package at.ac.tgm.llatschbacher;

import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Rechtschreibtrainer {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\jakob\\IdeaProjects\\WortTrainerReloaded\\src\\main\\java\\at\\ac\\tgm\\jertl2\\data.json"));
            JsonNode wortpaareNode = jsonNode.path("Wortpaare");

            // Überprüfen, ob die Liste nicht leer ist
            if (wortpaareNode.isArray() && !wortpaareNode.isEmpty()) {
                // Wähle zufälligen Index
                Random random = new Random();
                int randomIndex = random.nextInt(wortpaareNode.size());

                // Auslesen des zufälligen Wortpaars und der URL
                JsonNode randomWortpaarNode = wortpaareNode.get(randomIndex);
                String wort = randomWortpaarNode.path(randomWortpaarNode.fieldNames().next()).get("Wort").asText();
                String url = randomWortpaarNode.path(randomWortpaarNode.fieldNames().next()).get("URL").asText();

                // Laden des Bilds von der URL und Skalierung auf maximale Größe
                URL imageURL = new URL(url);
                Image originalImage = ImageIO.read(imageURL);
                Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);

                // Text für die Statistik
                String statisticText = "Statistik: XYZ";

                // Eingabefeld
                JTextField inputField = new JTextField();

                // OptionPane anzeigen
                Object[] components = {icon, statisticText, "Eingabe:", inputField};
                int option = JOptionPane.showConfirmDialog(null, components, "Eingabeaufforderung", JOptionPane.OK_CANCEL_OPTION);

                // Überprüfen, ob der OK-Button gedrückt wurde
                if (option == JOptionPane.OK_OPTION) {
                    String userInput = inputField.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "Abbruch.");
                }
            } else {
                System.out.println("Die Liste der Wortpaare ist leer oder ungültig.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
