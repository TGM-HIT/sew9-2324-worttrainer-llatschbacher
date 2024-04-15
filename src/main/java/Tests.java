import at.ac.tgm.llatschbacher.WortPaar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        @ @ -11, 4 + 22, 36 @@public void testWortURLCheck () {
            Assertions.assertFalse(wortPaar.check("Katze", "htps://jkfsdkjfksdkjfjsdjfjsdfsd.de"));
            Assertions.assertFalse(wortPaar.check("", "htps://jkfsdkjfksdkjfjsdjfjsdfsd.de"));
        }
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


}

