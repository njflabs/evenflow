package njfbrowser.misc;

import java.io.*;
import java.net.Socket;

public class Request extends Thread {

    Request(String s, Socket socket1) {
        webRoot = "";
        fileRequested = separator;
        mimeType = "text/html";
        if (s.substring(s.length() - 1).equals(separator))
            webRoot = s;
        else
            webRoot = s + separator;
        socket = socket1;
        try {
            out = new DataOutputStream(socket1.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            return;
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }
    }

    private void setMimeType(String s) {
        String s1 = "";
        if (fileRequested.lastIndexOf(".") > -1)
            s1 = s.substring(fileRequested.lastIndexOf(".")).toUpperCase();
        if (s1.equals(".HTM")) {
            mimeType = "text/html";
            return;
        }
        if (s1.equals(".HTML")) {
            mimeType = "text/html";
            return;
        }
        if (s1.equals(".GIF")) {
            mimeType = "image/gif";
            return;
        }
        if (s1.equals(".JPG")) {
            mimeType = "image/jpeg";
            return;
        } else {
            mimeType = "text/plain";
            return;
        }
    }

    private String getMimeType() {
        return mimeType;
    }

    private void write(String s) {
        byte abyte0[] = s.getBytes();
        write(abyte0, 0, s.length());
    }

    private void write(byte abyte0[], int i, int j) {
        try {
            out.write(abyte0, 0, j);
            return;
        } catch (IOException _ex) {
            return;
        }
    }

    private boolean readHeaders()
            throws IOException {
        boolean flag = false;
        String s;
        while (!(s = in.readLine()).equals("")) {
            int i = s.indexOf(" ");
            String s1 = s.substring(0, i);
            if (s1.equals("GET")) {
                String s2 = s.substring(i + 1, s.indexOf(" ", i + 1));
                setFileRequested(s2);
                flag = true;
            }
        }
        return flag;
    }

    private void writeHeaders() {
        writeln("HTTP/1.0 200 OK");
        write("Content-Type: " + getMimeType() + "\n\n");
    }

    public void run() {
        try {
            if (!readHeaders())
                error(501, "Not implemented");
            else if ((new File(getFileRequested())).exists()) {
                writeHeaders();
                FileInputStream fileinputstream = new FileInputStream(getFileRequested());
                byte abyte0[] = new byte[1024];
                int i;
                while ((i = fileinputstream.read(abyte0)) != -1)
                    write(abyte0, 0, i);
            } else {
                error(404, "Object not found");
            }
            socket.close();
            return;
        } catch (Exception exception) {
            error(500, "Internal Server Error: " + exception.getMessage());
        }
    }

    private void setFileRequested(String s) {
        if (separator.equals("\\"))
            fileRequested = s.replace('/', '\\');
        if (fileRequested.substring(fileRequested.length() - 1).equals(separator))
            fileRequested = fileRequested + "index.htm";
        fileRequested = webRoot + fileRequested.substring(1);
        int i = fileRequested.indexOf("?");
        if (i > -1)
            fileRequested = fileRequested.substring(0, i);
        setMimeType(fileRequested);
    }

    private String getFileRequested() {
        return fileRequested;
    }

    private void error(int i, String s) {
        writeln("HTTP/1.0 " + i + " " + s);
        write("Content-Type: text/html\n\n");
        writeln("<body><h1>HTTP/1.0 " + i + " " + s);
        writeln("</h1></body>");
    }

    private void writeln(String s) {
        write(s + "\r\n");
    }

    public static final String separator = System.getProperty("file.separator");
    private String webRoot;
    private Socket socket;
    private transient DataOutputStream out;
    private transient BufferedReader in;
    private String fileRequested;
    private String mimeType;

}
