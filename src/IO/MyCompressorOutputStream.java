package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }
}
