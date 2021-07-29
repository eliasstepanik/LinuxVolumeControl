package de.sailehd.support;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.time.StopWatch;

public class ProcessBar {

    private int length;
    private int steps;
    private int localIndex = 0;
    private boolean stopper = false;
    StopWatch watch = new StopWatch();

    public ProcessBar(int length){
        watch.start();
        this.length = length;
        if(length < 100){
            return;
        }
        this.steps = length / 100;
    }


    public void update(Integer index, String extraInfo){
        if(index == length){
            watch.stop();
        }
        build(index, extraInfo);
    }


    private void build(Integer index, String extraInfo){
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= 100 ; i++) {
            result.append(" ");
        }

        if(index % steps == 0){
            localIndex++;
        }
        for (int i = 0; i < index; i++) {
            if(i % steps == 0 && i != 0){
                result.replace(localIndex - 1,localIndex, "■");
                for (int j = 0; j < localIndex - 1; j++) {
                    result.replace(j, j + 1, "■");
                }
            }
        }

        result.insert(0, "0%");
        result.append("100%");


        if((watch.getTime() / 1000) < 60){
            result.append("\nPercent-Done:" + localIndex + "%" + " | Time:" + watch.getTime() / 1000 + "sec");
            if(watch.getTime() > 1000 && localIndex != 0){
                Integer timeRemaining = Math.toIntExact(((watch.getTime() / 1000) / localIndex) * 100);

                if(timeRemaining < 60){
                    result.append(" | Time-Remaining:" + timeRemaining + "sec");
                }
                else if(timeRemaining > 60 && timeRemaining < 3600){
                    result.append(" | Time-Remaining:" + timeRemaining / 60 + "min" + timeRemaining % 60 + "sec");
                }
                else{
                    result.append(" | Time-Remaining:" + (timeRemaining /60) / 60 + "h " + (timeRemaining /60) % 60 + "min " + (timeRemaining % 60) % 60 + "sec");
                }

            }
        }
        else if((watch.getTime() / 1000) > 60 && (watch.getTime() / 1000) < 3600){
            result.append("\nPercent-Done:" + localIndex + "%" + " | Time:" + (watch.getTime() / 1000) / 60 + "min " + (watch.getTime() / 1000) % 60 + "sec");
            if(watch.getTime() > 1000 && localIndex != 0){
                Integer timeRemaining = Math.toIntExact(((watch.getTime() / 1000) / localIndex) * 100);

                if(timeRemaining < 60){
                    result.append(" | Time-Remaining:" + timeRemaining + "sec");
                }
                else if(timeRemaining > 60 && timeRemaining < 3600){
                    result.append(" | Time-Remaining:" + timeRemaining / 60 + "min" + timeRemaining % 60 + "sec");
                }
                else{
                    result.append(" | Time-Remaining:" + (timeRemaining /60) / 60 + "h " + (timeRemaining /60) % 60 + "min " + (timeRemaining % 60) % 60 + "sec");
                }

            }
        }
        else{
            int time = Math.toIntExact(watch.getTime() / 1000);

            result.append("\nPercent-Done:" + localIndex + "%" + " | Time:" + (time /60) / 60 + "h " + (time /60) % 60 + "min " + (time % 60) % 60 + "sec");

            if(watch.getTime() > 1000 && localIndex != 0){
                Integer timeRemaining = Math.toIntExact(((watch.getTime() / 1000) / localIndex) * 100);

                if(timeRemaining < 60){
                    result.append(" | Time-Remaining" + timeRemaining + "sec");
                }
                else if(timeRemaining > 60 && timeRemaining < 3600){
                    result.append(" | Time-Remaining" + timeRemaining / 60 + "min" + timeRemaining % 60 + "sec");
                }
                else{
                    result.append(" | Time-Remaining" + (timeRemaining /60) / 60 + "h " + (timeRemaining /60) % 60 + "min " + (timeRemaining % 60) % 60 + "sec");
                }

            }

        }
        result.append(" | Index:" + index);
        if(extraInfo != null){
            result.append(" | " + extraInfo);
        }
        Debug.clear();
        Debug.log(result.toString());

    }

}
