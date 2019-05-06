package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream out){

        if (out != null){
            this.out = out;
        }
    }


    @Override
    public void write(int b) throws IOException {
        try {
            if (b < 256) {
                out.write(0);
                out.write(b);
                return;
            }

            out.write(b/256);
            out.write(b%256);
        }
        catch(IOException e)
        {
            System.out.println("I/O error! (Maybe output string is closed)");
            e.printStackTrace();
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        HashMap<String, Integer> compressDict = new HashMap<>();

        for(int i=0; i<256; i++)
        {
            compressDict.put(new Integer(i).toString(), new Integer(i));
        }

        String current = "" + b[0], next;
        int i=0, counter = 256;

        while(i<b.length)
        {
            if(i == b.length -1)
                next = "";
            else
                next = "" + b[i+1];
            String checkExists = current + " " + next;
            if(compressDict.containsKey(checkExists))
                current = checkExists;
            else
            {
                write(compressDict.get(current));
                compressDict.put(checkExists, new Integer(counter));
                counter++;
                current = checkExists;
            }
        }
        write(compressDict.get(current).byteValue());
    }

}
