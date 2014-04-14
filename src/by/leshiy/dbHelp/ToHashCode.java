package by.leshiy.dbHelp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class ToHashCode {

    public static String md5(String string) {
	MessageDigest digest;
	try {
	    digest = MessageDigest.getInstance("md5");
	    digest.reset();
	    digest.update(string.getBytes());
	    byte hash[] = digest.digest();
	    @SuppressWarnings("resource")
	    Formatter formatter = new Formatter();
	    for (int i = 0; i < hash.length; i++) {
		formatter.format("%02X", hash[i]);
	    }
	    return formatter.toString();
	} catch (NoSuchAlgorithmException e) {
	    return null;
	}
    }
}
