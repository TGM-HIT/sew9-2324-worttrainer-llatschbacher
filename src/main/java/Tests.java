import at.ac.tgm.llatschbacher.WortPaar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Tests {
    @Test
    public void testWortURLCheck() {
        WortPaar wortPaar = new WortPaar();
        Assertions.assertTrue(wortPaar.check("Katze", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.kindernetz.de%2Fwissen%2Ftierlexikon%2F1655279778114%2Csteckbrief-katze-102~_v-16x9%402dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg&tbnid=zgqcZxFcu2f5VM&vet=12ahUKEwjhiJz10MaBAxWgpycCHS51DwUQMygBegQIARB2..i&imgrefurl=https%3A%2F%2Fwww.kindernetz.de%2Fwissen%2Ftierlexikon%2Fsteckbrief-katze-100.html&docid=hkpyeymIgQBduM&w=1440&h=810&q=katze&ved=2ahUKEwjhiJz10MaBAxWgpycCHS51DwUQMygBegQIARB2"));
        Assertions.assertFalse(wortPaar.check("", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.kindernetz.de%2Fwissen%2Ftierlexikon%2F1655279778114%2Csteckbrief-katze-102~_v-16x9%402dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg&tbnid=zgqcZxFcu2f5VM&vet=12ahUKEwjhiJz10MaBAxWgpycCHS51DwUQMygBegQIARB2..i&imgrefurl=https%3A%2F%2Fwww.kindernetz.de%2Fwissen%2Ftierlexikon%2Fsteckbrief-katze-100.html&docid=hkpyeymIgQBduM&w=1440&h=810&q=katze&ved=2ahUKEwjhiJz10MaBAxWgpycCHS51DwUQMygBegQIARB2"));
        Assertions.assertFalse(wortPaar.check("Katze", "htps://jkfsdkjfksdkjfjsdjfjsdfsd.de"));
        Assertions.assertFalse(wortPaar.check("", "htps://jkfsdkjfksdkjfjsdjfjsdfsd.de"));
    }

    @Test
    public void testLoadPaar() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\jakob\\IdeaProjects\\WortTrainerReloaded\\src\\main\\java\\at\\ac\\tgm\\jertl2\\data.json"));
        JsonNode wortpaareNode = jsonNode.path("Wortpaare");
        // Überprüfen, ob die Liste nicht leer ist
        if (wortpaareNode.isArray() && !wortpaareNode.isEmpty()) {
            // Wähle zufälligen Index
            Random random = new Random();
            int randomIndex = random.nextInt(wortpaareNode.size());
            // Auslesen des zufälligen Wortpaars und der URL
            JsonNode randomWortpaarNode = wortpaareNode.get(randomIndex);
            String wort;
            Assertions.assertNotNull(wort = randomWortpaarNode.path(randomWortpaarNode.fieldNames().next()).get("Wort").asText());
            String url;
            Assertions.assertNotNull(url = randomWortpaarNode.path(randomWortpaarNode.fieldNames().next()).get("URL").asText());
        }
    }

    @Test
    public void testURLScaleImage() throws IOException {
        URL imageURL = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1280px-Hauskatze_langhaar.jpg");
        Image originalImage = ImageIO.read(imageURL);
        Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        Assertions.assertEquals(300, icon.getIconWidth());
        Assertions.assertEquals(300, icon.getIconHeight());
    }

    @Test
    public void testLoadStats() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\jakob\\IdeaProjects\\WortTrainerReloaded\\src\\main\\java\\at\\ac\\tgm\\jertl2\\data.json"));
        JsonNode statistik = jsonNode.path("Statistik");
        // Überprüfen, ob die Liste nicht leer ist
        if (statistik.isArray() && !statistik.isEmpty()) {
            // Wähle zufälligen Index
            JsonNode statistikNode = jsonNode.path("Statistik");
            ObjectNode statNode = (ObjectNode) jsonNode.path("Statistik");
            statNode.put("richtig", 10);
            statNode.put("falsch", 10);
            objectMapper.writeValue(new File("C:\\Users\\jakob\\IdeaProjects\\WortTrainerReloaded\\src\\main\\java\\at\\ac\\tgm\\jertl2\\data.json"), jsonNode);
            Assertions.assertEquals(10,statistikNode.get("richtig").asInt());
            Assertions.assertEquals(10, statistikNode.get("falsch").asInt());
        }
    }

}