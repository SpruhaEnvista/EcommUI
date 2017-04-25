package com.envista.msi.api.domain.util;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Created by user on 4/18/2017.
 */
public class StringEncrypter
{

        private Cipher ecipher;
        private Cipher dcipher;

        private static StringEncrypter singleton_instance = null;

        private StringEncrypter() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
            this("myShipINFOversion3");
        }

        private StringEncrypter(String passPhrase) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

            // 8-bytes Salt
            byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03 };

            // Iteration count
            int iterationCount = 19;

            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameters to the cipthers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        }

        public static StringEncrypter getInstance() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
            synchronized (StringEncrypter.class) {
                if (singleton_instance == null)
                    singleton_instance = new StringEncrypter();
            }
            return singleton_instance;
        }

        public String encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        }

        public String decrypt(String str) throws IOException, IllegalBlockSizeException, BadPaddingException {

            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        }

          public static void testUsingPassPhrase() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException,
                IllegalBlockSizeException, BadPaddingException, IOException {

            String secretString = "Jay.Speaks@gmail.com;948;448";

            // Create encrypter/decrypter class
            StringEncrypter desEncrypter = StringEncrypter.getInstance();

            // Encrypt the string
            String desEncrypted = desEncrypter.encrypt(secretString);

            // Decrypt the string
            String desDecrypted = desEncrypter.decrypt("oJwn4NRUUGqTi3r7URe70yY774J7pTOpuR+ebUPbF64=");

        }

}
