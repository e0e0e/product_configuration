package pl.sda.pms.image;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	public void importImage() throws Exception {
		System.err.println("usage: java clipimg [filename]");
		String outputfile = "/home/grzes/Documents/pliki/1.png";
		copyTo(outputfile);

	}

	static int copyTo(String filename) throws Exception {
		Transferable content = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		if (content == null) {
			System.err.println("error: nothing found in clipboard");
			return 1;
		}
		if (!content.isDataFlavorSupported(DataFlavor.imageFlavor)) {
			System.err.println("error: no image found in clipbaord");
			return 2;
		}
		BufferedImage img = (BufferedImage) content.getTransferData(DataFlavor.imageFlavor);
		String ext = ext(filename);
		if (ext == null) {
			ext = "png";
			filename += "." + ext;
		}
		File outfile = new File(filename);
		ImageIO.write(img, ext, outfile);
		System.err.println("image copied to: " + outfile.getAbsolutePath());
		return 0;
	}

	static String ext(String filename) {
		int pos = filename.lastIndexOf('.') + 1;
		if (pos == 0 || pos >= filename.length())
			return null;
		return filename.substring(pos);
	}

	public Map<String, Object> saveToFtp(String imageBase64) {
		Map<String, Object> res = new HashMap<String, Object>();

		File imageFile = new File("/home/grzes/Documents/pliki/canvasImage.png");
		try {
			byte[] decodedBytes = DatatypeConverter
					.parseBase64Binary(imageBase64.replaceAll("data:image/.+;base64,", ""));
			BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			ImageIO.write(bfi, "png", imageFile);
			bfi.flush();
			res.put("ret", 0);
		} catch (Exception e) {
			res.put("ret", -1);
			res.put("msg", "Cannot process due to the image processing error.");
			return res;
		}

		return res;

	}
	
	public void readFtp() {
		 String server = "wielon.c0.pl";
	        int port = 21;
	        String user = "wielon";
	        String pass = "pass";
	 
	        FTPClient ftpClient = new FTPClient();
	 
	        try {
	 
	            ftpClient.connect(server, port);
	            showServerReply(ftpClient);
	            System.out.println("rep1");
	            int replyCode = ftpClient.getReplyCode();
	            if (!FTPReply.isPositiveCompletion(replyCode)) {
	                System.out.println("Connect failed");
	                return;
	            }
	 
	            boolean success = ftpClient.login(user, pass);
	            showServerReply(ftpClient);
	            System.out.println("rep2");
	            if (!success) {
	                System.out.println("Could not login to the server");
	                return;
	            }
	 
	            // Lists files and directories
	            FTPFile[] files1 = ftpClient.listFiles("/public_ftp");
	            printFileDetails(files1);
	 
	            // uses simpler methods
	            String[] files2 = ftpClient.listNames();
	            printNames(files2);
	 
	 
	        } catch (IOException ex) {
	            System.out.println("Oops! Something wrong happened");
	            ex.printStackTrace();
	        } finally {
	            // logs out and disconnects from server
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	}
	
    private static void printFileDetails(FTPFile[] files) {
        DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (FTPFile file : files) {
            String details = file.getName();
            if (file.isDirectory()) {
                details = "[" + details + "]";
            }
            details += "\t\t" + file.getSize();
            details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
 
            System.out.println(details);
        }
    }
 
    private static void printNames(String files[]) {
        if (files != null && files.length > 0) {
            for (String aFile: files) {
                System.out.println(aFile);
            }
        }
    }
 
    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

}
