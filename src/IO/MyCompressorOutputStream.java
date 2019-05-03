package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream out){

        if (out != null)
            this.out = out;

    }


    @Override
    public void write(int b) throws IOException {
        out.flush();
        out.write(b);
    }

    @Override
    public void write(byte[] ba) throws IOException {
        out.flush();
        out.write(ba);
    }

}
