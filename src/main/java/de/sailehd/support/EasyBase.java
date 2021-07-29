package de.sailehd.support;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EasyBase {

    private File easyBase = null;
    private ArrayList<String> lines = new ArrayList<String>();
    private String fileName;
    private String baseName;


    public EasyBase(String name){
        fileName = name + ".esb";
        baseName = name;
        UpdateBaseFile(false);
    }

    public void UpdateBaseFile(boolean debugMessage){
        try {
            File newDataBase = new File(fileName);
            if (newDataBase.createNewFile()) {
                System.out.println("File created: " + newDataBase.getName());
                easyBase = newDataBase;
                initFile(easyBase);
            } else {
                //System.out.println("File already exists.");
                try{
                    ClassLoader classLoader = getClass().getClassLoader();
                    File tempEasyBase = new File(fileName);
                    try{
                        if(tempEasyBase.getName().endsWith(".esb")){
                            lines = readFile(tempEasyBase);
                            try {
                                if(lines.get(0).equals(fileName) && lines.get(1).equals("{") && lines.get(lines.size() - 1).equals("}")){
                                    easyBase = tempEasyBase;
                                    if(debugMessage){
                                        System.out.println(baseName +" Initialized");
                                    }

                                    //System.out.println(lines.toString());
                                }
                            }
                            catch (Exception e){
                                System.out.println("The File is not a vaild Database");
                                e.printStackTrace();
                            }

                        }
                    }
                    catch (Exception e){
                        System.out.println("The File is not a vaild Database");
                        e.printStackTrace();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private ArrayList<String> readFile(File file){
        ArrayList<String> tempLines = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tempLines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            print("An error occurred.");
            e.printStackTrace();
        }

        return tempLines;
    }

    private boolean checkForDuplicate(String name){
        UpdateBaseFile(false);
        for (String line : lines) {
            if(line.contains(name)){
                return true;
            }
        }
        return false;
    }

    private void initFile(File file){
        try {
            PrintStream fW = new PrintStream(file);
            fW.println(file.getName());
            fW.println("{");
            fW.println("}");
            print("File Initialized");
        } catch (IOException e) {
            print("An error occurred.");
            e.printStackTrace();
        }
    }

    private void rewriteFile(){
        try {
            PrintStream fW = new PrintStream(easyBase);
            for (String line : lines) {
                try{
                    fW.println(line);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            //print("File Rewritten");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        UpdateBaseFile(false);
    }

    public void createData(String name, Object data){
        UpdateBaseFile(false);
        try{
            if(checkForDuplicate(name)){
                //print("Data already exists");
                //print(lines.toString());
                //editData("name", data);
                return;
            }

            if(data instanceof String){
                lines.add(2, "String " + name + " = " + (String) data);
                rewriteFile();
            }
            else if(data instanceof Byte){
                lines.add(2, "Byte " + name + " = " + (Byte) data);
                rewriteFile();
            }
            else if(data instanceof Short){
                lines.add(2, "Short " + name + " = " + (Short) data);
                rewriteFile();
            }
            else if(data instanceof Integer){
                lines.add(2, "Integer " + name + " = " + (Integer) data);
                rewriteFile();
            }
            else if(data instanceof Long){
                lines.add(2, "Long " + name + " = " + (Long) data);
                rewriteFile();
            }
            else if(data instanceof Float){
                lines.add(2, "Float " + name + " = " + (Float) data);
                rewriteFile();
            }
            else if(data instanceof Double){
                lines.add(2, "Double " + name + " = " + (Double) data);
                rewriteFile();
            }
            else if(data instanceof Boolean){
                lines.add(2, "Boolean " + name + " = " + (Boolean) data);
                rewriteFile();
            }
            else if(data instanceof ArrayList){
                StringBuilder builder = new StringBuilder();

                if(((ArrayList<?>) data).get(0) instanceof String){
                    if(((ArrayList<String>) data).size() != 0){
                        for (String listdata : (ArrayList<String>) data) {
                            String temp = listdata.replace(' ', '|');
                            builder.append(temp + " ");
                        }
                    }

                    lines.add(2, "ArrayList <String> " + name + " = " + "{ " + builder.toString() + "}");
                    rewriteFile();
                }
                else if(((ArrayList<?>) data).get(0) instanceof Byte){
                    if(((ArrayList<Byte>) data).size() != 0){
                        for (Byte listdata : (ArrayList<Byte>) data) {
                            builder.append(listdata + " ");
                        }
                    }

                    lines.add(2, "ArrayList <Byte> " + name + " = " + "{ " + builder.toString() + "}");
                    rewriteFile();
                }
                else if(((ArrayList<?>) data).get(0) instanceof Short){
                    if(((ArrayList<Short>) data).size() != 0){
                        for (Short listdata : (ArrayList<Short>) data) {
                            builder.append(listdata + " ");
                        }
                    }

                    lines.add(2, "ArrayList <Short> " + name + " = " + "{ " + builder.toString() + "}");
                    rewriteFile();
                }
                else if(((ArrayList<?>) data).get(0) instanceof Integer){
                    if(((ArrayList<Integer>) data).size() != 0){
                        for (Integer listdata : (ArrayList<Integer>) data) {
                            builder.append(listdata + " ");
                        }
                    }

                    lines.add(2, "ArrayList <Integer> " + name + " = " + "{ " + builder.toString() + "}");
                    rewriteFile();
                }
                else if(((ArrayList<?>) data).get(0) instanceof Long){
                    if(((ArrayList<Long>) data).size() != 0){
                        for (Long listdata : (ArrayList<Long>) data) {
                            builder.append(listdata + " ");
                        }
                    }

                    lines.add(2, "ArrayList <Long> " + name + " = " + "{ " + builder.toString() + "}");
                    rewriteFile();
                }
                else if(((ArrayList<?>) data).get(0) instanceof Float){
                    if(((ArrayList<Float>) data).size() != 0){
                        for (Float listdata : (ArrayList<Float>) data) {
                            builder.append(listdata + " ");
                        }
                    }

                    lines.add(2, "ArrayList <Float> " + name + " = " + "{ " + builder.toString() + "}");
                    rewriteFile();
                }
                else if(((ArrayList<?>) data).get(0) instanceof Double){
                    if(((ArrayList<Double>) data).size() != 0){
                        for (Double listdata : (ArrayList<Double>) data) {
                            builder.append(listdata + " ");
                        }
                    }

                    lines.add(2, "ArrayList <Double> " + name + " = " + "{ " + builder.toString() + "}");
                    rewriteFile();
                }
                else if(((ArrayList<?>) data).get(0) instanceof Boolean){
                    if(((ArrayList<Boolean>) data).size() != 0){
                        for (Boolean listdata : (ArrayList<Boolean>) data) {
                            builder.append(listdata + " ");
                        }
                    }

                    lines.add(2, "ArrayList <Boolean> " + name + " = " + "{ " + builder.toString() + "}");
                    rewriteFile();
                }
                else{
                    print("Datatype is not allowd in ArrayList");
                    UpdateBaseFile(false);
                    return;
                }
            }
            else{
                print("Datatype ist not valid");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        UpdateBaseFile(false);
    }

    public Object getData(String name){
        UpdateBaseFile(false);
        for (String line : lines) {
            String[] lineStrings = line.split(" ");
            ArrayList<String> lineArrayList = new ArrayList<String>();
            for(String s : lineStrings){
                if(!line.equals("")){
                    lineArrayList.add(s);
                }
            }
            //print(lines.toString());
            if(lineArrayList.size() > 1){
                if(lineArrayList.get(1).equals(name)){
                    if(lineArrayList.get(0).equals("String")){
                        StringBuilder string = new StringBuilder();
                        for (String word : lineArrayList){
                            if(lineArrayList.size() == 4){
                                return lineArrayList.get(3);
                            }
                            else if(lineArrayList.indexOf(word) >= 3){
                                string.append(word + " ");
                            }
                        }
                        return string.toString();
                    }
                    else if(lineArrayList.get(0).equals("Byte")){
                        return Byte.parseByte(lineArrayList.get(lineArrayList.size() - 1));
                    }
                    else if(lineArrayList.get(0).equals("Short")){
                        return Short.parseShort(lineArrayList.get(lineArrayList.size() - 1));
                    }
                    else if(lineArrayList.get(0).equals("Integer")){
                        return Integer.parseInt(lineArrayList.get(lineArrayList.size() - 1));
                    }
                    else if(lineArrayList.get(0).equals("Long")){
                        return Long.parseLong(lineArrayList.get(lineArrayList.size() - 1));
                    }
                    else if(lineArrayList.get(0).equals("Float")){
                        return Float.parseFloat(lineArrayList.get(lineArrayList.size() - 1));
                    }
                    else if(lineArrayList.get(0).equals("Double")){
                        return Double.parseDouble(lineArrayList.get(lineArrayList.size() - 1));
                    }
                    else if(lineArrayList.get(0).equals("Boolean")){
                        return Boolean.parseBoolean(lineArrayList.get(lineArrayList.size() - 1));
                    }
                    else{
                        print(easyBase.getName() + ": " + "Filetype not Found!");
                        return null;
                    }
                }
                else if(lineArrayList.get(0).startsWith("ArrayList") && lineArrayList.get(2).equals(name)){
                    int startIntex = lineArrayList.indexOf("{");
                    int endIntex = lineArrayList.indexOf("}");
                    ArrayList<Integer> arrayVars = new ArrayList<Integer>();
                    for (String i : lineArrayList) {
                        int currentIndex = lineArrayList.indexOf(i);
                        if(currentIndex < endIntex && currentIndex > startIntex){
                            arrayVars.add(currentIndex);
                        }
                    }
                    ArrayList<Object> output = new ArrayList<Object>();
                    for (Integer i : arrayVars) {
                        if(lineArrayList.get(1).contains("<String>")){
                            String temp = lineArrayList.get(i).replace('|', ' ');
                            output.add(temp);
                        }
                        else if(lineArrayList.get(1).contains("<Byte>")){
                            output.add(Byte.parseByte(lineArrayList.get(i)));
                        }
                        else if(lineArrayList.get(1).contains("<Short>")){
                            output.add(Short.parseShort(lineArrayList.get(i)));
                        }
                        else if(lineArrayList.get(1).contains("<Integer>")){
                            output.add(Integer.parseInt(lineArrayList.get(i)));
                        }
                        else if(lineArrayList.get(1).contains("<Long>")){
                            output.add(Long.parseLong(lineArrayList.get(i)));
                        }
                        else if(lineArrayList.get(1).contains("<Float>")){
                            output.add(Float.parseFloat(lineArrayList.get(i)));
                        }
                        else if(lineArrayList.get(1).contains("<Double>")){
                            output.add(Double.parseDouble(lineArrayList.get(i)));
                        }
                        else if(lineArrayList.get(1).contains("<Boolean>")){
                            output.add(Boolean.parseBoolean(lineArrayList.get(i)));
                        }
                    }



                    return output;
                }

            }

        }
        return null;
    }

    public void editData(String name, Object data){
        for (String line : lines) {
            String[] lineStrings = line.split(" ");
            ArrayList<String> lineArrayList = new ArrayList<String>();
            for(String s : lineStrings){
                lineArrayList.add(s);
            }
            if(lineArrayList.size() > 1){
                if(lineArrayList.get(1).equals(name)){
                    if(lineArrayList.get(0).equals("String")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        createData(name, data);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Byte")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        createData(name, data);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Short")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        createData(name, data);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Integer")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        createData(name, data);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Long")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        createData(name, data);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Float")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        createData(name, data);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Double")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        createData(name, data);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Boolean")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        createData(name, data);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else{
                        print(easyBase.getName() + ": " + "Filetype not Found!");
                    }
                    UpdateBaseFile(false);
                }
                else if(lineArrayList.get(0).startsWith("ArrayList") && lineArrayList.get(2).equals(name)){
                    lines.remove(line);
                    rewriteFile();
                    UpdateBaseFile(false);
                    createData(name, data);
                    rewriteFile();
                    UpdateBaseFile(false);
                    return;

                }
            }
        }
    }

    public void deleteData(String name){
        UpdateBaseFile(false);
        for (String line : lines) {
            String[] lineStrings = line.split(" ");
            ArrayList<String> lineArrayList = new ArrayList<String>();
            for(String s : lineStrings){
                lineArrayList.add(s);
            }
            if(lineArrayList.size() > 1){
                if(lineArrayList.get(1).equals(name)){
                    if(lineArrayList.get(0).equals("String")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Byte")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Short")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Integer")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Long")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Float")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Double")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else if(lineArrayList.get(0).equals("Boolean")){
                        lines.remove(line);
                        rewriteFile();
                        UpdateBaseFile(false);
                        return;
                    }
                    else{
                        print(easyBase.getName() + ": " + "Filetype not Found!");
                    }
                }
                else if(lineArrayList.get(0).startsWith("ArrayList") && lineArrayList.get(2).equals(name)){
                    lines.remove(line);
                    rewriteFile();
                    UpdateBaseFile(false);
                    return;

                }
            }
        }
        UpdateBaseFile(false);
    }

    public void arrayAdd(String name, Object data){
        UpdateBaseFile(false);
        if(data instanceof String){
            ArrayList<String> tempList = new ArrayList<String>();
            tempList = (ArrayList<String>) getData(name);
            tempList.add((String) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Byte){
            ArrayList<Byte> tempList = new ArrayList<Byte>();
            tempList = (ArrayList<Byte>) getData(name);
            tempList.add((Byte) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Short){
            ArrayList<Short> tempList = new ArrayList<Short>();
            tempList = (ArrayList<Short>) getData(name);
            tempList.add((Short) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Integer){
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            tempList = (ArrayList<Integer>) getData(name);
            tempList.add((Integer) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Long){
            ArrayList<Long> tempList = new ArrayList<Long>();
            tempList = (ArrayList<Long>) getData(name);
            tempList.add((Long) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Float){
            ArrayList<Float> tempList = new ArrayList<Float>();
            tempList = (ArrayList<Float>) getData(name);
            tempList.add((Float) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Double){
            ArrayList<Double> tempList = new ArrayList<Double>();
            tempList = (ArrayList<Double>) getData(name);
            tempList.add((Double) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Boolean){
            ArrayList<Boolean> tempList = new ArrayList<Boolean>();
            tempList = (ArrayList<Boolean>) getData(name);
            tempList.add((Boolean) data);
            editData(name, tempList);
            rewriteFile();
        }
        UpdateBaseFile(false);
    }

    public void arrayRemove(String name, Object data){
        UpdateBaseFile(false);
        if(data instanceof String){
            ArrayList<String> tempList = new ArrayList<String>();
            tempList = (ArrayList<String>) getData(name);
            tempList.remove((String) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Byte){
            ArrayList<Byte> tempList = new ArrayList<Byte>();
            tempList = (ArrayList<Byte>) getData(name);
            tempList.remove((Byte) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Short){
            ArrayList<Short> tempList = new ArrayList<Short>();
            tempList = (ArrayList<Short>) getData(name);
            tempList.remove((Short) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Integer){
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            tempList = (ArrayList<Integer>) getData(name);
            tempList.remove((Integer) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Long){
            ArrayList<Long> tempList = new ArrayList<Long>();
            tempList = (ArrayList<Long>) getData(name);
            tempList.remove((Long) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Float){
            ArrayList<Float> tempList = new ArrayList<Float>();
            tempList = (ArrayList<Float>) getData(name);
            tempList.remove((Float) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Double){
            ArrayList<Double> tempList = new ArrayList<Double>();
            tempList = (ArrayList<Double>) getData(name);
            tempList.remove((Double) data);
            editData(name, tempList);
            rewriteFile();
        }
        else if(data instanceof Boolean){
            ArrayList<Boolean> tempList = new ArrayList<Boolean>();
            tempList = (ArrayList<Boolean>) getData(name);
            tempList.remove((Boolean) data);
            editData(name, tempList);
            rewriteFile();
        }
        UpdateBaseFile(false);
    }

    public int arrayIndexOf(String name, Object data){
        UpdateBaseFile(false);
        if(data instanceof String){
            ArrayList<String> tempList = new ArrayList<String>();
            tempList = (ArrayList<String>) getData(name);
            return tempList.indexOf((String) data);
        }
        else if(data instanceof Byte){
            ArrayList<Byte> tempList = new ArrayList<Byte>();
            tempList = (ArrayList<Byte>) getData(name);
            tempList.remove((Byte) data);
            return tempList.indexOf((Byte) data);
        }
        else if(data instanceof Short){
            ArrayList<Short> tempList = new ArrayList<Short>();
            tempList = (ArrayList<Short>) getData(name);
            tempList.remove((Short) data);
            return tempList.indexOf((Short) data);
        }
        else if(data instanceof Integer){
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            tempList = (ArrayList<Integer>) getData(name);
            tempList.remove((Integer) data);
            return tempList.indexOf((Integer) data);
        }
        else if(data instanceof Long){
            ArrayList<Long> tempList = new ArrayList<Long>();
            tempList = (ArrayList<Long>) getData(name);
            tempList.remove((Long) data);
            return tempList.indexOf((Long) data);
        }
        else if(data instanceof Float){
            ArrayList<Float> tempList = new ArrayList<Float>();
            tempList = (ArrayList<Float>) getData(name);
            tempList.remove((Float) data);
            return tempList.indexOf((Float) data);
        }
        else if(data instanceof Double){
            ArrayList<Double> tempList = new ArrayList<Double>();
            tempList = (ArrayList<Double>) getData(name);
            tempList.remove((Double) data);
            return tempList.indexOf((Double) data);
        }
        else if(data instanceof Boolean){
            ArrayList<Boolean> tempList = new ArrayList<Boolean>();
            tempList = (ArrayList<Boolean>) getData(name);
            tempList.remove((Boolean) data);
            return tempList.indexOf((Boolean) data);
        }
        else{
            print("Object was not found in Array");
            return 0;
        }
    }

    public int arraySize(String name){
        UpdateBaseFile(false);
        try{
            ArrayList<Object> tempList = new ArrayList<Object>();
            tempList = (ArrayList<Object>) getData(name);
            return tempList.size();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        print("Index not found in Array");
        return 0;
    }

    public Object arrayGet(String name, int i){
        UpdateBaseFile(false);
        try{
            ArrayList<Object> tempList = new ArrayList<Object>();
            tempList = (ArrayList<Object>) getData(name);
            return tempList.get(i);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        print("Index not found in Array");
        return null;
    }

    public ArrayList<Object> getAll(){
        UpdateBaseFile(false);
        ArrayList<String> names = new ArrayList<String>();
        for (String str: lines) {
            if(!(str.endsWith(".esb")) && !str.equals("{") && !str.equals("}")){
                if(str.startsWith("ArrayList")){
                    String[] strings = str.split(" ");
                    names.add(strings[2]);
                }
                else{
                    String[] strings = str.split(" ");
                    names.add(strings[1]);
                }
            }
        }
        ArrayList<Object> returnList = new ArrayList<Object>();
        for (String str : names) {
            returnList.add(getData(str));
        }

        return returnList;
    }

    private void print(String message){
        Integer tempInt = easyBase.getName().indexOf(".");
        System.out.println(easyBase.getName().substring(0, tempInt) + ": " + message);
    }
}
