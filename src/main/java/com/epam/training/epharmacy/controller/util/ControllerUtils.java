package com.epam.training.epharmacy.controller.util;

import com.epam.training.epharmacy.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class ControllerUtils {

    private static final Logger LOG = LogManager.getLogger(ControllerUtils.class);

    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;
    private static final String PBKDF2WITHHMACSHA1_ALGORITHM = "PBKDF2WithHmacSHA1";

    private ControllerUtils() {
    }

    public static User getUserFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session != null) {
           return (User) session.getAttribute("user");
        }
        return null;
    }

    public static byte[] generateHash(final String string) {
        try {
            final byte[] salt = getSalt();
            final KeySpec spec = new PBEKeySpec(string.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
            final SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2WITHHMACSHA1_ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOG.error("Error during generating hash");
            throw new RuntimeException(e);
        }

    }

    private static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }


}
