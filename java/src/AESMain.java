

public class AESMain {
	
	public static void t1() {
		String src = "Hello,CryptWorld";
		String encrypted = AESUtil.getHash(src, "ok", "1934");
		String[] decrypted = AESUtil.extractHash(encrypted);

		System.out.println("src: " + src);
		System.out.println("encrypted: " + encrypted);
		System.out.println("decrypted: ");
		for (String x : decrypted) {
			System.out.println(x);
		}
	}
	
	public static void t2() {
		String t1 = "abc|ede|kko0|";
        String[] x1 = t1.split("\\|");
		for (String x : x1) {
			System.out.println(x);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		t1();
	}

}
