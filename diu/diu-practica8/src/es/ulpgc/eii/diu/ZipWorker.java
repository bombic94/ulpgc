/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.eii.diu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 * ZipWorker runs on different thread than GUI and informs about progress
 * during executed task
 */
public class ZipWorker extends SwingWorker<Integer, String> {

    private final File inputFile;
    private final File outputFile;
    private final List<String> fileList; 
    private final int BUFFER_SIZE = 1024;
    private final JTextArea area;

    /**
     * Constructor
     * @param inputFile Folder that will be compressed to zip file
     * @param outputFile Direction where the zip file will be saved
     * @param area TextArea informing about progress
     */
    public ZipWorker(final File inputFile, final File outputFile, final JTextArea area) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.area = area;
        fileList = new ArrayList<>();
    }

    /**
     * Throw interrupted exception when user presses cancel button.
     * @throws InterruptedException Cancel button pressed
     */
    private static void failIfInterrupted() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Interrupted while searching files");
        }
    }

    /**
     * Run task on another thread.
     * In this task, the files in specified folder will be compressed to zip file.
     * @return Integer with return value
     * @throws Exception 
     */
    @Override
    protected Integer doInBackground() throws Exception {
        ZipWorker.failIfInterrupted();
        
        publish("Obtaining files to compress");
        getAllFiles(inputFile);
        
        publish("_________________________________\nStarting to compress:");
        BufferedInputStream origin = null;
        FileOutputStream dest = new FileOutputStream(outputFile + "/folder.zip");
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
        byte[] data = new byte[BUFFER_SIZE];
        for (int i = 0; i < fileList.size(); i++) {
            ZipWorker.failIfInterrupted();
            String filename = fileList.get(i);
            publish("Compressing file: " + filename);
            FileInputStream fi = new FileInputStream(filename);
            origin = new BufferedInputStream(fi, BUFFER_SIZE);

            ZipEntry entry = new ZipEntry( filename.substring(inputFile.getPath().length()+1));
            out.putNextEntry( entry );
            int count;
            while((count = origin.read(data, 0, BUFFER_SIZE)) != -1)
            {
                out.write(data, 0, count);
            }
            origin.close();
            setProgress((i + 1) * 100 / fileList.size());
        }
        out.close();
        
        publish("_________________________________\nDone");
        return 1;
    }

    /**
     * Processing the task and informing GUI
     * @param chunks 
     */
    @Override
    protected void process(List< String> chunks) {
        for (final String string : chunks) {
            area.append(string);
            area.append("\n");
        }
    }

    /**
     * Recursive function to obtain all files in a folder and subfolders.
     * @param dir Folder in which to look for files
     * @throws java.io.IOException 
     */
    public void getAllFiles(File dir) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {

            if (file.isDirectory()) {
                    publish("directory:" + file.getCanonicalPath());
                    getAllFiles(file);
            } else {
                    publish("     file:" + file.getCanonicalPath());
                    fileList.add(file.getAbsolutePath());
            }
        }
    }
} 
