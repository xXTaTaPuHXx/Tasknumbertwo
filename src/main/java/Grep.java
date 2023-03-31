import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private final boolean invert;
    private final boolean ignore;
    private final boolean regex;
    private final String word;
    private final String fileName;
    public Grep(boolean ignore, boolean invert, boolean regex, String word, String fileName) {
        this.invert = invert;
        this.ignore = ignore;
        this.regex = regex;
        if (this.ignore)
            this.word = word.toLowerCase();
        else
            this.word = word;
        this.fileName = fileName;
    }
    public String findLines() {
        StringBuilder out = new StringBuilder();
        try (FileReader fin = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(fin)) {
            String str;
            while ((str = buffer.readLine()) != null) {
                String iStr = ignore ? str.toLowerCase() : str;
                if (regex) {
                    Pattern pattern = Pattern.compile(word);
                    Matcher matcher = pattern.matcher(iStr);
                    if (!invert && matcher.find() || invert && !matcher.find()) {
                        out.append(str);
                        out.append('\n');
                    }
                } else {
                    if (!invert && iStr.contains(word) || invert && !iStr.contains(word)) {
                        out.append(str);
                        out.append('\n');
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода");
        }
        return out.toString();
    }
}
