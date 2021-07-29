package de.sailehd.support;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static boolean moveFileToDirectory(File sourceFile, String targetPath) {
        File tDir = new File(targetPath);
        if (tDir.exists()) {
            String newFilePath = targetPath+File.separator+sourceFile.getName();
            File movedFile = new File(newFilePath);
            if (movedFile.exists())
                movedFile.delete();
            return sourceFile.renameTo(new File(newFilePath));
        } else {
            Debug.log("unable to move file "+sourceFile.getName()+" to directory "+targetPath+" -> target directory does not exist");
            return false;
        }
    }

    public static ArrayList<File> GetFiles(File directory, String[] types){
        ArrayList<File> tempFiles = new ArrayList<File>();

        for(String type : types){
            for (int i = 0; i < directory.listFiles().length; i++) {
                if (directory.listFiles()[i].isDirectory()) {
                    for (File file: GetFiles(directory.listFiles()[i], types)) {
                        if(file.getName().endsWith("." + type)){
                            tempFiles.add(file);
                        }
                    }
                }
                else if(directory.listFiles()[i].isFile()){
                    if(directory.listFiles()[i].getName().endsWith("." + type)){
                        tempFiles.add(directory.listFiles()[i]);
                    }
                }

            }
        }
        return tempFiles;
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static void combineAudioFiles(String audioFilesFolder, String outputFile, String[] fileformats) throws IOException {
        File folder = new File(audioFilesFolder);
        ArrayList<File> files = new ArrayList<File>(GetFiles(folder,fileformats));
        Debug.log(TextColor.YELLOW + "File Merge Start" + TextColor.RESET);

        FileInputStream tempstream = null;
        SequenceInputStream sistream = null;

        try {
            FileWriter myWriter = new FileWriter("tracks.txt");
            for (File file : files) {
                String tempfile = file.getAbsolutePath();
                tempstream = new FileInputStream(tempfile);
                sistream = new SequenceInputStream(tempstream, sistream);
                Debug.log(TextColor.BLACK_BOLD + "File = " + TextColor.RESET + TextColor.BLUE_BOLD_BRIGHT + file.getName() + TextColor.RESET);
                myWriter.write(file.getName() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the trackFile.");
        }
        catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        FileOutputStream fostream = new FileOutputStream(outputFile);//destinationfile
        //Debug.log("Stream done");
        try {
            Debug.log(TextColor.YELLOW + "Writing" + TextColor.RESET);
            int temp;
            while( ( temp = sistream.read() ) != -1)
            {
                fostream.write(temp);
            }
        }
        catch (Exception e){

        }

        //Debug.log("Closing Streams");
        fostream.close();
        sistream.close();
        tempstream.close();
        // Debug.log("Streams Closed");
        Debug.log(TextColor.GREEN + "Done" + TextColor.RESET);
    }
}
