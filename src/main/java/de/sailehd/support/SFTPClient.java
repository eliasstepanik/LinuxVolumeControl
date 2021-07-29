package de.sailehd.support;

import com.jcraft.jsch.*;

import java.util.ArrayList;
import java.util.Vector;

public class SFTPClient {

    private Session session = null;

    private String username = "";
    private String password = "";
    private String host = "";
    private String knownHostsPath = "";

    private Integer port = 22;

    public SFTPClient(String knownHostsPath, String host, String username, String password, Integer port){
        this.knownHostsPath = knownHostsPath;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void connect() throws JSchException {
        JSch jsch = new JSch();

        // Uncomment the line below if the FTP server requires certificate
        jsch.setKnownHosts(knownHostsPath);
        // Uncomment the two lines below if the FTP server requires password
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        Debug.log("Connected to Server");
    }

    public void upload(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.put(source, destination);
        sftpChannel.exit();
    }

    public void download(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.get(source, destination);
        sftpChannel.exit();
    }

    public void rename(String oldPath, String newPath) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.rename(oldPath, newPath);
        sftpChannel.exit();
    }

    public void remove(String path) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.rm(path);
        sftpChannel.exit();
    }

    public void mkdir(String path) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.mkdir(path);
        sftpChannel.exit();
    }

    public void chmod(Integer permission ,String path) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.chmod(permission, path);
        sftpChannel.exit();
    }

    public ArrayList<String> ls(String path) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        Vector filelist = sftpChannel.ls(path);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < filelist.size(); i++) {
            list.add(filelist.get(i).toString());
        }
        ArrayList<String> list2 = new ArrayList<String>();
        for (String files : list) {
            String[] filesParts = files.split(" ");
            for (String i: filesParts) {
                if(i == filesParts[filesParts.length - 1] && !(i.contains("..")) && !(i.equals("."))){
                    list2.add(i);
                }
            }

        }

        sftpChannel.exit();
        return list2;
    }

    public ArrayList<String> lsWithPath(String path) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        Vector filelist = sftpChannel.ls(path);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < filelist.size(); i++) {
            list.add(filelist.get(i).toString());
        }
        ArrayList<String> list2 = new ArrayList<String>();
        for (String files : list) {
            String[] filesParts = files.split(" ");
            for (String i: filesParts) {
                if(i == filesParts[filesParts.length - 1] && !(i.contains("..")) && !(i.equals("."))){
                    list2.add(path +  i);
                }
            }

        }

        sftpChannel.exit();
        return list2;
    }

    public void copy(Integer permission ,String path) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.exit();
    }


    public void disconnect() {
        if (session != null) {
            session.disconnect();
        }
    }
}
