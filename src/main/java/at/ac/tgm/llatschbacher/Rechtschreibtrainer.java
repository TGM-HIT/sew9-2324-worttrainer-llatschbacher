package at.ac.tgm.llatschbacher;

import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Rechtschreibtrainer {
    private ArrayList<WortPaar> wortPaare;
    private int richtigW;
    private int falschW;
    private String currentWord;
    private String currentUrl;

    private boolean falschG;



    public Rechtschreibtrainer() {
        wortPaare = new ArrayList<>();
        richtigW = 0;
        falschW = 0;
        currentWord = null;
        currentUrl = null;
    }

    private void loadWortpaareFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\jakob\\IdeaProjects\\WortTrainerReloaded\\src\\main\\java\\at\\ac\\tgm\\jertl2\\data.json"));
        JsonNode wortpaareNode = jsonNode.path("Wortpaare");

        for (int i = 0; i < wortpaareNode.size(); i++) {
            JsonNode randomWortpaarNode = wortpaareNode.get(i);
            String wort = randomWortpaarNode.path(randomWortpaarNode.fieldNames().next()).get("Wort").asText();
            String url = randomWortpaarNode.path(randomWortpaarNode.fieldNames().next()).get("URL").asText();
            wortPaare.add(new WortPaar(wort, url));
        }
    }

    private void displayOptionPane() throws MalformedURLException, IOException {
        String url = null;
        String wort = null;

        if(!falschG) {
            Random random = new Random();
            int x = random.nextInt(wortPaare.size());
            url = wortPaare.get(x).getUrl();
            wort = wortPaare.get(x).getWort();
            while (wort.equalsIgnoreCase(currentWord)) {
                x = random.nextInt(wortPaare.size());
                url = wortPaare.get(x).getUrl();
                wort = wortPaare.get(x).getWort();
            }
        } else {
            url = currentUrl;
            wort = currentWord;
        }

        // Laden des Bilds von der URL und Skalierung auf maximale Größe
        URL imageURL = new URL(url);
        Image originalImage = ImageIO.read(imageURL);
        Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);

        // Text für die Statistik
        String statisticText = "Richtig: " + richtigW + " Falsch: " + falschW;

        // Eingabefeld
        JTextField inputField = new JTextField();

        // OptionPane anzeigen
        Object[] components = {icon, statisticText, "Eingabe:", inputField};
        int option = JOptionPane.showConfirmDialog(null, components, "Eingabeaufforderung", JOptionPane.OK_CANCEL_OPTION);

        // Überprüfen, ob der OK-Button gedrückt wurde
        if (option == JOptionPane.OK_OPTION) {
            String userInput = inputField.getText();
            if (userInput.equalsIgnoreCase(wort)) {
                JOptionPane.showMessageDialog(null, "Richtig!");
                currentWord = wort;
                currentUrl = url;
                falschG = false;
                richtigW++;
            } else {
                JOptionPane.showMessageDialog(null, "Falsch");
                currentWord = wort;
                currentUrl = url;
                falschG = true;
                falschW++;
            }
        }
    }

    private void updateStatisticsInJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\jakob\\IdeaProjects\\WortTrainerReloaded\\src\\main\\java\\at\\ac\\tgm\\jertl2\\data.json"));
        ObjectNode statNode = (ObjectNode) jsonNode.path("Statistik");
        statNode.put("richtig", richtigW);
        statNode.put("falsch", falschW);
        objectMapper.writeValue(new File("C:\\Users\\jakob\\IdeaProjects\\WortTrainerReloaded\\src\\main\\java\\at\\ac\\tgm\\jertl2\\data.json"), jsonNode);
    }

    public void runTraining() throws IOException {
        boolean finished = false;

        loadWortpaareFromJson();

        do {
            displayOptionPane();
            updateStatisticsInJson();
        } while (!finished);
    }
}