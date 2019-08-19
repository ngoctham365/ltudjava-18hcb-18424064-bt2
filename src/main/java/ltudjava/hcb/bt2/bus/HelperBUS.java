/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jossion
 */
public class HelperBUS {

    private static String encryptSHA1(String input) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(input.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String byteToHex(final byte[] hash) {
        String result;
        try (Formatter formatter = new Formatter()) {
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            result = formatter.toString();
        }
        return result;
    }

    public static String encryptPassword(String input) {
        return encryptMD5(encryptSHA1(input));
    }

    public static String convertASCII(int intAsciiValue) {
        return Character.toString((char) intAsciiValue);
    }

    public static String iconMoveRightHasSpace() {
        return " " + convertASCII(16) + " ";
    }

    public static String concatWithIconMoveRight(String input1, String input2) {
        return input1 + iconMoveRightHasSpace() + input2;
    }

    public static String[] concatWithIconMoveRight(String input) {
        return input.split(iconMoveRightHasSpace());
    }

    public static List<String> openFileToGetContent(JFrame jFrame) {
        List<String> strings = new ArrayList<String>();
        JFileChooser fileChooser = new JFileChooser("./data");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        fileChooser.setAcceptAllFileFilterUsed(true);
        if (fileChooser.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION) {
            strings.add(fileChooser.getSelectedFile().getName().replace(".csv", ""));

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileChooser.getSelectedFile()), "UTF8"));
                String contentFile;
                while ((contentFile = reader.readLine()) != null) {
                    strings.add(contentFile);
                }

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strings;
    }

    public static Float convertFloat(String input) {
        try {
            return Float.parseFloat(input);
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean isFloat(String input) {
        try {
            Float f=Float.parseFloat(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
