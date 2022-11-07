import org.apache.derby.tools.ij;

import java.io.IOException;

public class RunDbConsole {
    public static void main(String[] args) {
        // connect 'jdbc:derby:ChatClient;create=true';
        try {
            ij.main(args);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
