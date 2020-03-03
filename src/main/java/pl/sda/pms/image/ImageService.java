package pl.sda.pms.image;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Service
public class ImageService {
	private static Config config = ConfigFactory.load();

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

	public Map<String, Object> saveLocal(String imageBase64) {
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

	public FTPFile[] readFtp() {
		
		FTPFile[] result = null;
		String server = config.getString("ftpServer");
		int port = config.getInt("ftpPort");
		String user = config.getString("ftpUser");
		String pass = config.getString("ftpPass");


		FTPClient ftpClient = new FTPClient();

		try {

			ftpClient.connect(server, port);
			showServerReply(ftpClient);

			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Connect failed");

			}

			ftpClient.enterLocalPassiveMode();
			boolean success = ftpClient.login(user, pass);

			if (!success) {
				System.out.println("Could not login to the server");
			} else {
				System.out.print("Loggin succesfull");
			}

			result = ftpClient.listFiles("/imagesLd/");
			printFileDetails(result);


			String[] files2 = ftpClient.listNames();
			printNames(files2);

		} catch (IOException ex) {
			System.out.println("Oops! Something wrong happened");
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
	}

	public String saveToFtp(String imageBase64, String featureName) {
		String server = config.getString("ftpServer");
		int port = config.getInt("ftpPort");
		String user = config.getString("ftpUser");
		String pass = config.getString("ftpPass");


		FTPClient ftpClient = new FTPClient();

		try {

			ftpClient.connect(server, port);
			showServerReply(ftpClient);

			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Connect failed");
				throw new RuntimeException("Connect failed");
			}

			ftpClient.enterLocalPassiveMode();
			boolean success = ftpClient.login(user, pass);

			if (!success) {
				System.out.println("Could not login to the server");
				throw new RuntimeException("Could not login to the server");
			} else {
				System.out.print("Loggin succesfull ");
			}


			FTPFile[] files1 = ftpClient.listFiles("/imagesLd");
			//printFileDetails(files1);


			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);


			byte[] decodedBytes = DatatypeConverter
					.parseBase64Binary(imageBase64.replaceAll("data:image/.+;base64,", ""));
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			String fileWithSpaces=featureName+"_"+(new Date().getTime()) + ".png";
			String fileName=fileWithSpaces.replace(" ", "_");
			String firstRemoteFile = "/imagesLd/" + fileName;

			System.out.println("Start uploading first file");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			 ImageIO.write(bufferedImage, "png", outputStream);
			 InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
	            inputStream.close();
			bufferedImage.flush();
			if (done) {
				System.out.println("The first file is uploaded successfully.");
				return fileName;
			}


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
		return null;
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
			for (String aFile : files) {
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
