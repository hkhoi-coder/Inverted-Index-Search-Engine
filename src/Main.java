
import java.io.IOException;

/**
 *
 * @author hkhoi
 */
public class Main {

    public static void main(String[] args) throws IOException {
        SearchEngine engine = new SearchEngine();
        engine.getTermsFromDir();
        engine.exportData();
        engine.testInvertedIndex("you");
        engine.query("car or shit and yolo");
    }
}
