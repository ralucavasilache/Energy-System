package output;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Paths;
/**
 * Clasa care realizeaza scrierea in fisierul de iesire,
 * in format JSON
 */
public final class OutputLoader {
    /**
     Fisierul de iesire
     */
    private final String outFile;
    /**
     Obiectul care contine datele ce vor fi scrise
     */
    private final OutputData outputData;

    public OutputLoader(final String outFile, final OutputData outputData) {

        this.outFile = outFile;
        this.outputData = outputData;
    }
    /**
     * Metoda care se ocupa de scrierea datelor in fisier
     */
    public void putData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get(outFile).toFile(), outputData);
    }
}
