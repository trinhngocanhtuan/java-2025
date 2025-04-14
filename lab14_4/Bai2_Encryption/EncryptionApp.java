import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionApp extends JFrame {
    private JTextArea inputText;
    private JTextArea outputText;
    private JComboBox<String> algorithmBox;
    private Encryptable encryptor;

    public EncryptionApp() {
        setTitle("Encryptor - AES & RSA");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Input
        JPanel topPanel = new JPanel(new BorderLayout());
        inputText = new JTextArea(5, 40);
        topPanel.add(new JScrollPane(inputText), BorderLayout.CENTER);

        algorithmBox = new JComboBox<>(new String[]{"AES", "RSA"});
        topPanel.add(algorithmBox, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Buttons
        JPanel midPanel = new JPanel();
        JButton encryptBtn = new JButton("Encrypt");
        JButton decryptBtn = new JButton("Decrypt");
        encryptBtn.addActionListener(e -> handleEncrypt());
        decryptBtn.addActionListener(e -> handleDecrypt());
        midPanel.add(encryptBtn);
        midPanel.add(decryptBtn);
        add(midPanel, BorderLayout.CENTER);

        // Output
        outputText = new JTextArea(5, 40);
        outputText.setEditable(false);
        add(new JScrollPane(outputText), BorderLayout.SOUTH);
    }

    private void handleEncrypt() {
        try {
            String algorithm = (String) algorithmBox.getSelectedItem();
            String input = inputText.getText();
            encryptor = algorithm.equals("AES") ? new AESEncryption() : new RSAEncryption();
            String encrypted = encryptor.encrypt(input);
            outputText.setText(encrypted);
        } catch (Exception ex) {
            outputText.setText("Lỗi mã hóa: " + ex.getMessage());
        }
    }

    private void handleDecrypt() {
        try {
            String algorithm = (String) algorithmBox.getSelectedItem();
            String input = outputText.getText();
            encryptor = algorithm.equals("AES") ? new AESEncryption() : new RSAEncryption();
            String decrypted = encryptor.decrypt(input);
            inputText.setText(decrypted);
        } catch (Exception ex) {
            inputText.setText("Lỗi giải mã: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EncryptionApp().setVisible(true));
    }

    // ===== Interface =====
    interface Encryptable {
        String encrypt(String plainText) throws Exception;
        String decrypt(String cipherText) throws Exception;
    }

    // ===== AES =====
    class AESEncryption implements Encryptable {
        private final String SECRET_KEY = "1234567890123456"; // 16 ký tự

        public String encrypt(String plainText) throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }

        public String decrypt(String cipherText) throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(cipherText);
            return new String(cipher.doFinal(decoded));
        }
    }

    // ===== RSA =====
    class RSAEncryption implements Encryptable {
        private KeyPair keyPair;

        public RSAEncryption() throws Exception {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            keyPair = generator.generateKeyPair();
        }

        public String encrypt(String plainText) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }

        public String decrypt(String cipherText) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] decoded = Base64.getDecoder().decode(cipherText);
            return new String(cipher.doFinal(decoded));
        }
    }
}
