package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;

public class MyDecompressorInputStream extends InputStream {

    private Dictionary<Byte, String> decompressDict;
    private InputStream in;

    /**
     * Constructor that gets an InputStream
     * and initialize new Decompressor
     */
    public MyDecompressorInputStream(InputStream stream){
        if (stream != null)
            in = stream;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return in.read(b);
    }
}
