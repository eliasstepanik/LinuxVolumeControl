package de.sailehd;

import java.io.IOException;
import com.fazecast.jSerialComm.*;
import de.sailehd.support.Debug;
import de.sailehd.support.EasyBase;
import de.sailehd.support.TextColor;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


    private Thread loop;
    private static EasyBase config;


    public static void main(String[] args) throws Exception {
        config = new EasyBase("config");
        //PulseAudio.setDefaultSink(0);

        config.createData("portName", new String("ttyACM0"));
        config.createData("debug", false);

        ArrayList<String> SliderArray = new ArrayList<String>();
        SliderArray.add("System");
        SliderArray.add("Microsoft|Edge");
        SliderArray.add("spotify");
        config.createData("Slider", SliderArray);


        ArrayList<Float> sliderValues = new ArrayList<Float>();
        SerialPort comPort = null;
        Main main = new Main();


        if(SerialPort.getCommPort((String) config.getData("portName")) != null){
            comPort = SerialPort.getCommPort((String) config.getData("portName"));
            Debug.log(TextColor.GREEN + "Port " + comPort + " was found.");
        }
        else{
            Debug.log(TextColor.RED + "Port " + comPort + " not found.");
            System.exit(8);
        }


        try{
            comPort.openPort();
            comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
            Debug.log(TextColor.GREEN + "Connected to Port " + comPort.getSystemPortName() + ".");
        }
        catch (Exception e){
            Debug.log(TextColor.RED + "Could not connect to Port " + comPort.getSystemPortName() + ".");
            System.exit(8);
        }



        InputStream in = comPort.getInputStream();
        try
        {
            Scanner scan = new Scanner(in);;

            int startSkip = 20;
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                if(startSkip <= 0){

                    for (Float value: parseInput(line)) {
                        sliderValues.add(map(value, 0.00f, 5.00f, 0f, 100f));
                    }


                    main.loop(sliderValues, (boolean) config.getData("debug"));

                }
                else{
                    startSkip--;
                }
                sliderValues = new ArrayList<Float>();
            }

        } catch (Exception e) { e.printStackTrace(); }


    }

    public void loop(ArrayList<Float> sliderValues, boolean debug) throws IOException {
        ArrayList<String> slider = (ArrayList<String>) config.getData("Slider");
        if(debug){
            Debug.clear();
            StringBuilder debugRegisterdDivices = new StringBuilder();
            debugRegisterdDivices.append("Registered Devices: ");
            for (String sliderVal : slider) {
                if(slider.indexOf(sliderVal) != slider.size() - 1){
                    debugRegisterdDivices.append(sliderVal + ", ");
                }
                else{
                    debugRegisterdDivices.append(sliderVal);
                }
            }

            Debug.log(debugRegisterdDivices.toString());

            int i = 0;
            for (Float value : sliderValues) {
                try {
                    Debug.log("Slider[" + i + "]" + ": " + value + "% | " + slider.get(i));
                }catch (Exception e){
                    Debug.log("Slider[" + i + "]" + ": " + value + "%");
                }


                i++;
            }


        }



        for (String application : slider) {
            //Debug.log(slider.indexOf(application) + "");
            if(application.equals("System")){
                PulseAudio.setVolumeOutput(PulseAudio.getDefaultSinkIndex(), sliderValues.get(slider.indexOf(application)).intValue());
            }
            else{
                PulseAudio.setVolumeInput(PulseAudio.getInputSinkFormApplicationIndex(application), sliderValues.get(slider.indexOf(application)).intValue());
            }

        }
        //PulseAudio.setVolumeInput(PulseAudio.getInputSinkFormApplicationIndex("Microsoft Edge"), sliderValues.get(1));
        //PulseAudio.setVolumeInput(PulseAudio.getInputSinkFormApplicationIndex("spotify"), sliderValues.get(2));



    }

    public static ArrayList<Float> parseInput(String line){
        ArrayList<Float> sliderValues = new ArrayList<Float>();

        String[] valuesRaw = line.split("\\|");

        for (String valueRaw: valuesRaw) {
            sliderValues.add(Float.parseFloat(valueRaw));
        }

        return sliderValues;
    }

    public static long map(long x, long in_min, long in_max, long out_min, long out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public static double map(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public static float map(float x, float in_min, float in_max, float out_min, float out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public static int map(int x, int in_min, int in_max, int out_min, int out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
