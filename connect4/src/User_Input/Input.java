package User_Input;

import AI.Learn;

import java.util.Objects;
import java.util.Scanner;

public class Input extends Thread{
    public Learn learn;
    public volatile int command = 0;
    public volatile boolean live = true;
    public Input(Learn learn){
        this.learn = learn;
    }
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);


        while(live){
            String line = in.nextLine();
            //System.out.println(line);
            if(Objects.equals(line, "start")){
                System.out.println("Learning...");
                learn.learn = true;
            }
            if(Objects.equals(line, "stop")){
                System.out.println("Learning stopped!");
                learn.learn = false;
            }
            if(Objects.equals(line, "s")){
                System.out.println("status");
                command = 1;
            }
            if(Objects.equals(line, "game")) {
                System.out.println("game");
                command = 2;
            }
            if(Objects.equals(line, "off")) {
                System.out.println("off");
                live = false;
                learn.live = false;
            }
            //else command = 0;
        }
    }
}
