package com.unlam.tp_integrador.strategy.process;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.tools.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class HasherStrategy implements ProcesamientoStrategy {

    private final String MAP_KEY = "text";

    @Override
    public String process(TareaDTO tareaDTO) {
        String password = String.valueOf(tareaDTO.getDetalleTarea().get(MAP_KEY));
        return hash(password);
    }

    private String hash(String password) {
        try {
            // Crear instancia del algoritmo de hashing
            MessageDigest digest = MessageDigest.getInstance(Utils.SHA_256);

            // Convertir la contraseña en bytes
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            // Realizar el hash de la contraseña
            byte[] hashedBytes = digest.digest(passwordBytes);

            // Codificar el hash en Base64 para su almacenamiento
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    *     public static boolean verifyPassword(String password, String hashedPassword) {
        String hashedInput = hashPassword(password);
        return hashedInput != null && hashedInput.equals(hashedPassword);
    }*/

}
