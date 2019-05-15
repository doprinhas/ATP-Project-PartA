package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

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
    public int read() {
        try {
            return in.read();
        }
        catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int read(byte[] b) {
        try {
            int to_return = in.read(b);
            decompress(b);
            return to_return;
        }
        catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void decompress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out /*, new Inflater(true)*/);
            infl.write(in);
            infl.flush();
            infl.close();

            byte[] decompress = out.toByteArray();
            for (int i = 0 ; i < decompress.length ; i++)
                in[i] = decompress[i];

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
