import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Theme {

    private static Theme single_instance = null;
    private Dictionary colors;
    private Dictionary fonts;

    private final String userDirectory = System.getProperty("user.dir");
    private final String projectDirectory = userDirectory + "/src/main/java/";

    public static Theme getInstance() {
        if (single_instance == null)
            single_instance = new Theme();

        return single_instance;
    }

    private Theme() {
        colors = new Hashtable();
        colors.put("topPanelColor", new Color(61, 90, 128));
        colors.put("middlePanelColor", new Color(224, 251, 252));
        colors.put("questionPanelColor", new Color(224, 251, 252));
        colors.put("choicesPanelColor", new Color(152, 193, 217));
        colors.put("bottomPanelColor", new Color(61, 90, 128));

        colors.put("mainColor", new Color(238, 108, 77));
        colors.put("normalColor", new Color(61, 90, 128));
        colors.put("h1Color", new Color(238, 108, 77));
        colors.put("fontQuestionColor", new Color(238, 108, 77));
        colors.put("fontStatusColor", new Color(224, 251, 252));

        colors.put("fontBtnColor", new Color(11, 37, 69));
        colors.put("btnColor", new Color(152, 193, 217));

        fonts = new Hashtable();
        fonts.put("h1", new Font("sansserif",0,30));
        fonts.put("choicesFont", new Font("sansserif",0,16));
        fonts.put("normalFont", new Font("sansserif",0,16));
    }

    public Color getColor(String name) {
        Color value = (Color) colors.get(name);

        if (value == null) return new Color(0, 0, 0);

        return value;
    }

    public Font getFont(String name) {
        Font value = (Font) fonts.get(name);

        if (value == null) return (Font) fonts.get("normalFont");

        return value;
    }

    public String getDataDir() {
        return projectDirectory + "data/";
    }

    public String getImgDir() {
        return projectDirectory + "img/";
    }
}
