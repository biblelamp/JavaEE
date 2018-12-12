package cz.tendersystems;

import org.bouncycastle.cms.*;
import org.bouncycastle.jce.provider.*;

import java.io.*;
import java.security.*;
import java.security.cert.*;
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

	private static byte[] decrypt(byte[] content, KeyStore.PrivateKeyEntry pkEntry) throws Exception {
		return decrypt(content, (X509Certificate) pkEntry.getCertificate(), pkEntry.getPrivateKey());
	}

	private static byte[] decrypt(byte[] content, X509Certificate cert, PrivateKey key) throws Exception {
		CMSEnvelopedData enveloped = new CMSEnvelopedData(content);

		RecipientId recId = new RecipientId();

		recId.setSerialNumber(cert.getSerialNumber());
		recId.setIssuer(cert.getIssuerX500Principal().getEncoded());

		RecipientInformationStore recipients = enveloped.getRecipientInfos();
		RecipientInformation recipient = recipients.get(recId);

		if (recipient == null)
			throw new Exception("Recipient not found");

		return recipient.getContent(key, "BC");
	}

	public static void decryptFile(byte[] buffer, String fileName) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		try {
			FileInputStream fileInputStream =
					 new FileInputStream(PATH + CERT_FILE_NAME);
			keyStore.load(fileInputStream, PASSW.toCharArray());
			fileInputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		KeyStore.PrivateKeyEntry privateKeyEntry =
				(KeyStore.PrivateKeyEntry) keyStore.getEntry("tender systems",
						new KeyStore.PasswordProtection(PASSW.toCharArray()));

		try {
			byte[] decryptedMessage = decrypt(buffer, privateKeyEntry);

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

	public static void main(String[] args) throws Exception {

		byte[] buffer = readFile("/home/lamp/Downloads/Ahoj jak se máš.encrypt");

		System.out.println(Arrays.toString(buffer));

		decryptFile(buffer, "/home/lamp/Downloads/Ahoj jak se máš.txt");
	}
}
