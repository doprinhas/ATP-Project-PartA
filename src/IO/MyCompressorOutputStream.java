package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    /**
     * constructor
     * @param out
     */
    public MyCompressorOutputStream(OutputStream out){

        if (out != null){
            this.out = out;
        }
    }


    @Override
    public void write(int b) {
        try {
            out.write(b);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This function writes a compressed byte array to the given file
     * @param b
     */
    @Override
    public void write(byte[] b) {
        try{
            out.write(compress(b));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * compress the given byte file
     * @param in
     * @return
     */
    public static byte[] compress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream defl = new DeflaterOutputStream(out);
            defl.write(in);
            defl.flush();
            defl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
