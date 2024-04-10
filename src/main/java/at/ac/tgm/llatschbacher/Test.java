package at.ac.tgm.llatschbacher;
import at.ac.tgm.llatschbacher.WortPaar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test {
    //@Test
    public void testURLCheck() {
        WortPaar wortPaar = new WortPaar();
        Assertions.assertTrue(wortPaar.check("Katze", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.kindernetz.de%2Fwissen%2Ftierlexikon%2F1655279778114%2Csteckbrief-katze-102~_v-16x9%402dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg&tbnid=zgqcZxFcu2f5VM&vet=12ahUKEwjhiJz10MaBAxWgpycCHS51DwUQMygBegQIARB2..i&imgrefurl=https%3A%2F%2Fwww.kindernetz.de%2Fwissen%2Ftierlexikon%2Fsteckbrief-katze-100.html&docid=hkpyeymIgQBduM&w=1440&h=810&q=katze&ved=2ahUKEwjhiJz10MaBAxWgpycCHS51DwUQMygBegQIARB2"));
        Assertions.assertFalse(wortPaar.check("", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.kindernetz.de%2Fwissen%2Ftierlexikon%2F1655279778114%2Csteckbrief-katze-102~_v-16x9%402dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg&tbnid=zgqcZxFcu2f5VM&vet=12ahUKEwjhiJz10MaBAxWgpycCHS51DwUQMygBegQIARB2..i&imgrefurl=https%3A%2F%2Fwww.kindernetz.de%2Fwissen%2Ftierlexikon%2Fsteckbrief-katze-100.html&docid=hkpyeymIgQBduM&w=1440&h=810&q=katze&ved=2ahUKEwjhiJz10MaBAxWgpycCHS51DwUQMygBegQIARB2"));
        Assertions.assertFalse(wortPaar.check("Katze", "htps://jkfsdkjfksdkjfjsdjfjsdfsd.de"));
        Assertions.assertFalse(wortPaar.check("", "https://jkfsdkjfksdkjfjsdjfjsdfsd.de"));
    }
}