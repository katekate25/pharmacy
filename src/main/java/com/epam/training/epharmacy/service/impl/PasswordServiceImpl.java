package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.SearchCriteria;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.PasswordService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Objects;

public class PasswordServiceImpl implements PasswordService {

    private static final Logger LOG = LogManager.getLogger(PasswordServiceImpl.class);

    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;
    private static final String PBKDF2WITHHMACSHA1_ALGORITHM = "PBKDF2WithHmacSHA1";

    @Override
    public void setPasswordForUser(User user, String password) {
        byte[] salt = user.getSalt() != null ? user.getSalt().getBytes() : getSalt();
        byte[] hashedPassword = generateHash(password, salt);
        user.setSalt(new String(salt));
        user.setPassword(getPassword(salt, hashedPassword));
    }

    @Override
    public boolean isPasswordValid(User user, String password) {
        byte[] salt = user.getSalt() != null ? user.getSalt().getBytes() : StringUtils.EMPTY.getBytes();
        byte[] hashedPassword = generateHash(password, salt);
        return Objects.equals(user.getPassword(), getPassword(salt, hashedPassword));
    }

    private static byte[] generateHash(String string, byte[] salt) {
        try {
            final KeySpec spec = new PBEKeySpec(string.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
            final SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2WITHHMACSHA1_ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOG.error("Error during generating hash");
            throw new RuntimeException(e);
        }
    }

    private String getPassword(byte[] salt, byte[] hashedPassword) {
        return new String(salt) + new String(hashedPassword);
    }

    private static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
