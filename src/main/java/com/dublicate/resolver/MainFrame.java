package com.dublicate.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    final static Logger loger = LoggerFactory.getLogger(MainFrame.class);
    private static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public MainFrame() throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getRoots()[0]);
        jfc.setPreferredSize(new Dimension(width / 2, height / 2));
        jfc.setLocation((width - getSize().width) / 2, (height - getSize().height) / 2);
        jfc.setDialogTitle("Please, choose your files ");
        jfc.setMultiSelectionEnabled(true);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = jfc.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String regex = JOptionPane.showInputDialog(this, " Please, input here your regex. \n  For example:[ , ] or [ \\n ] ", "Your regex");
            File[] files = jfc.getSelectedFiles();
            loger.info("Were found: " + files.length + " files");

            for (File file : files) {
                FileWorker.readFromFile(file, regex);
            }

            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getRoots()[0]);
            fileChooser.setDialogTitle("Type a file to save");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                loger.info("Save as file: " + fileToSave.getAbsolutePath());
                FileWorker.writeToFile(fileToSave, regex);
            }
        }
        System.exit(0);

    }


}
