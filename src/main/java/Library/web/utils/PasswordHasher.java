package Library.web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    public static String hashPassword(String password) {
        try {
            // Создаем соль
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Создаем хеш с солью
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Объединяем соль и хеш пароля для хранения
            byte[] combined = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedPassword) {
        try {
            // Декодируем сохраненный пароль
            byte[] combined = Base64.getDecoder().decode(storedPassword);

            // Извлекаем соль (первые 16 байт)
            byte[] salt = new byte[16];
            System.arraycopy(combined, 0, salt, 0, salt.length);

            // Хешируем введенный пароль с той же солью
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedInputPassword = md.digest(inputPassword.getBytes());

            // Сравниваем хеши
            for (int i = 0; i < hashedInputPassword.length; i++) {
                if (hashedInputPassword[i] != combined[i + salt.length]) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }
}