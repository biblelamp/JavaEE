package cz.tendersystems;

import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.*;
import org.bouncycastle.jce.provider.*;

import java.io.*;
import java.security.*;
import java.util.*;
import java.util.logging.*;

public class Decrypt {
	static final String CERT_FILE_NAME = "ts-selfsigned-podpisovy-cert.pfx";
	static final String PASSW = "pass1234";
	static final String PATH = "/home/lamp/Public/";
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	private static final Logger LOG = Logger.getLogger(Decrypt.class.getName());

	private static byte[] decrypt(byte[] encryptedData, PrivateKey decryptionKey) throws CMSException {
		byte[] decryptedData = null;

		if (null != encryptedData && null != decryptionKey) {
			CMSEnvelopedData envelopedData = new CMSEnvelopedData(encryptedData);

			Collection<RecipientInformation> recipients
					= envelopedData.getRecipientInfos().getRecipients();
			KeyTransRecipientInformation recipientInfo
					= (KeyTransRecipientInformation) recipients.iterator().next();
			JceKeyTransRecipient recipient
					= new JceKeyTransEnvelopedRecipient(decryptionKey);

			return recipientInfo.getContent(recipient);
		}
		return decryptedData;
	}

	public static void decryptFile(byte[] buffer, String fileName) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");

		keyStore.load(new FileInputStream(PATH + CERT_FILE_NAME), PASSW.toCharArray());
		PrivateKey privateKey =
				(PrivateKey) keyStore.getKey("tender systems", PASSW.toCharArray());

		try {
			byte[] decryptedMessage = decrypt(buffer, privateKey);

			BufferedOutputStream bos =
					new BufferedOutputStream(
							new FileOutputStream(fileName));
			bos.write(decryptedMessage);
			bos.close();

			LOG.log(Level.INFO, fileName + " saved.");

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static byte[] readFile(String fileName) {
		File file = new File(fileName);
		byte[] buffer = new byte[(int) file.length()];

		try {
			BufferedInputStream bis =
				new BufferedInputStream(new FileInputStream(file));
			bis.read(buffer);
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return buffer;
	}

	public static String hexDecode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i +=4) {
			result += (char) Integer.parseInt(str.substring(i, i + 4), 16);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {

		System.out.println(hexDecode("00410068006f006a0020006a0061006b0020007300650020006d00e10161"));

		//byte[] buffer = readFile("/home/lamp/Downloads/Ahoj jak se máš.encrypt");
		//System.out.println(Arrays.toString(buffer));
		//decryptFile(buffer, "/home/lamp/Public/Ahoj jak se máš.txt");
	}
}
