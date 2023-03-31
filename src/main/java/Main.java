import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import java.util.List;

public class Main {

    @Option(name = "-v", usage = "inverts filter condition")
    private boolean invert;

    @Option(name = "-i", usage = "ignores register")
    private boolean ignore;

    @Option(name = "-r", usage = "uses regex instead of words")
    private boolean regex;

    @Argument(handler = StringArrayOptionHandler.class, required = true)
    private List<String> arguments;

    public static void main(String[] args) {
        new Main().doMain(args);
    }

    public void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        String word;
        String fileName;
        try {
            parser.parseArgument(args);
            word = arguments.get(0);
            fileName = arguments.get(1);
        }
        catch( CmdLineException e ) {
            // взято с https://github.com/kohsuke/args4j/blob/master/args4j/examples/SampleMain.java
            System.err.println(e.getMessage());
            System.err.println("java Main [-r] [-v] [-i] word fileName");
            parser.printUsage(System.err);
            return;
        }
        System.out.println(new Grep(ignore, invert, regex, word, fileName).findLines());
    }
}