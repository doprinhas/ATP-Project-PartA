package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

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
