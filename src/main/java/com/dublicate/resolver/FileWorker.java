package com.dublicate.resolver;

import javax.swing.*;
import java.io.*;
import java.util.HashSet;
import java.util.stream.Collectors;

public class FileWorker {
    final static HashSet<Long> hashSet = new HashSet();

    public static void readFromFile(File file, String regex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
        String currentLine = "";
        StringBuilder sb = new StringBuilder();
        while ((currentLine = br.readLine()) != null) {
            sb.append(currentLine);
        }
        br.close();
        String[] split = sb.toString().split(regex);

        try {
            for (String s : split) {
                hashSet.add(Long.valueOf(s));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You typed wrong regex!");
            System.exit(-1);
        }
    }


    public static void writeToFile(File file, String regex) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file.getPath()));
        String sb = hashSet.stream().map(currentRecord -> currentRecord + regex).collect(Collectors.joining());
        bw.write(org.apache.commons.lang3.StringUtils.removeEnd(sb, ","));
        bw.flush();
        bw.close();
        JOptionPane.showMessageDialog(null, "Was found [" + hashSet.size() + "] unique records!");
    }
}
