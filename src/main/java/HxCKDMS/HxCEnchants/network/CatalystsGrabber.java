package HxCKDMS.HxCEnchants.network;

import java.io.BufferedReader;

public class CatalystsGrabber {
    //TODO Catalyst System
    /*
    If Catalyst System Enabled in Config create file, if disabled delete file
        If File Exists read Version first
            If Version is Outdated download if updating enabled
        If File Doesn't exist Download Current
    */
    public static void load(BufferedReader reader) {
        if (reader != null) {
            String e;
            try {
                while ((e = reader.readLine()) != null) {
                    if (e.startsWith("VERSION")) {
                        int ver = Integer.parseInt(e.split("=")[1].replaceAll(".", ""));

                    }
                }
            } catch (Exception var4) {}
        }
    }
}
