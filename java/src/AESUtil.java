import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public enum AESUtil {
	;
//	private static final String ENCRYPTION_KEY = "RwcmlVpg";	// key length has to be 8 bytes
	private static final String ENCRYPTION_KEY = "thisIsASecretKey";	// key length has to be 8 bytes

	private static final String ENCRYPTION_IV = "4e5Wa71fYoT7MFEX";
	private static final String ENC_ALG = "AES/CBC/PKCS5Padding";

	/**
	 * Join elements with "|", then return the encrypted string;
	 * 
	 * @param elements
	 * @return
	 */
	public static String getHash(Object... elements) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < elements.length; i++) {
			sb.append(elements[i]);
			if (i < elements.length - 1)
				sb.append("|");
		}
		return encrypt(sb.toString());
	} // getHash()
	
	/**
	 * 解析hash
	 * 
	 * @param hash
	 * @return
	 */
	public static String[] extractHash(String hash) {
		if (hash == null || hash.length() == 0)
			return null;
		return decrypt(hash).split("\\|");
	}
	
	public static String encrypt(String src) {
		try {
			Cipher cipher = Cipher.getInstance(ENC_ALG);
			cipher.init(Cipher.ENCRYPT_MODE, makeKey(), makeIv());
			return Base64.encodeBytes(cipher.doFinal(src.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String decrypt(String src) {
		String decrypted = "";
		try {
			Cipher cipher = Cipher.getInstance(ENC_ALG);
			cipher.init(Cipher.DECRYPT_MODE, makeKey(), makeIv());
			decrypted = new String(cipher.doFinal(Base64.decode(src)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return decrypted;
	}
	
	static AlgorithmParameterSpec makeIv() {
		try {
			return new IvParameterSpec(ENCRYPTION_IV.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	static Key makeKey() {
		try {
//			MessageDigest md = MessageDigest.getInstance("SHA-256");
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] key = md.digest(ENCRYPTION_KEY.getBytes("UTF-8"));
			return new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	static Key makeKey(){
		return new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
	}
}
