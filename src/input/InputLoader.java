package input;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
/**
 * Clasa care realizeaza parsarea datelor
 */
public final class InputLoader {
    /**
     Fisierul de intrare
     */
    private final String inFile;

    public InputLoader(final String inFile) {
        this.inFile = inFile;
    }
    /**
     * Parseaza datele din fisierul de intrare
     * @return InputData, un obiect care contine datele parsate
     */
    public InputData getData() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Paths.get(inFile).toFile(), InputData.class);
    }

}
