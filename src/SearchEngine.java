
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author hkhoi
 */
public class SearchEngine {

    private static final String DELIMITERS = " ,./?:;[]{}+=_-()*&^%$#@!0123456789�“”’—–";
    private static final String DIR_NAME = "docs";
    private static final String EXPORT_NAME = "dictionary.txt";
    private static File[] files;
    private static final String OR = "or";
    private static final String AND = "and";

    static {
        File dir = new File(DIR_NAME);
        files = dir.listFiles();
    }

    private TreeMap<String, BitSet> mTreeMap = new TreeMap<>();

    public void getTermsFromFile(int fileId) throws FileNotFoundException, IOException {
        QuickScanner scan = new QuickScanner(new FileInputStream(files[fileId]), DELIMITERS);
        String word = scan.next();
        while (word != null) {
            word = word.toLowerCase();
            if (!mTreeMap.containsKey(word)) {
                mTreeMap.put(word, new BitSet());
            }
            mTreeMap.get(word).set(fileId);
            word = scan.next();
        }
    }

    public void getTermsFromDir() throws IOException {
        File dir = new File(DIR_NAME);
        for (int i = 0; i < files.length; ++i) {
            getTermsFromFile(i);
        }
    }

    public void exportData() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(EXPORT_NAME);
        for (Map.Entry<String, BitSet> it : mTreeMap.entrySet()) {
            out.println(it.getKey() + " -> " + it.getValue());
        }
        out.close();
    }

    public BitSet getBitSet(String term) {
        if (mTreeMap.containsKey(term)) {
            BitSet result = mTreeMap.get(term);
            return result;
        }

        return new BitSet();
    }

    public void testInvertedIndex(String term) {
        BitSet bitSet = getBitSet(term);
        if (bitSet.isEmpty()) {
            System.out.println("Nothing found!");
        } else {
            System.out.println("Documents found:");
            for (int i = 0; i < bitSet.length(); ++i) {
                if (bitSet.get(i)) {
                    System.out.println("\t" + files[i]);
                }
            }
        }
    }

    public void query(String query) {
       if (query.isEmpty()) {
           System.out.println("Nothing for input");
           return;
       }
        
       StringTokenizer token = new StringTokenizer(query);
       String term0, term1, operator;
       term0 = token.nextToken();
       BitSet bitSet = getBitSet(term0);
       while (token.hasMoreTokens()) {      // Get term0
           // Get term0 input
           // Get operator
           operator = token.nextToken();
           if (operator.equals(OR)) {
               
           } else if (operator.equals(AND)) {
               
           } else {
               
           }
           // Input term0
           if (token.hasMoreTokens()) {
               term0 = token.nextToken();
           }
       }
    }
}
