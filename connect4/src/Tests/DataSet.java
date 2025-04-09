package Tests;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class DataSet {

    public int dataset_length;
    public int input_neuron_number;
    public int output_neuron_number;

    public double[][] data;
    public double[][] results;

    public int batch_size;
    public double[][] batch_data;
    public double[][] batch_results;

    private static final Random rand = new Random();

    public DataSet(int dataset_length, int input_neurons_number, int output_neurons_number, int batch_size){
        this.dataset_length = dataset_length;
        data = new double[dataset_length][input_neurons_number];
        results = new double[dataset_length][output_neurons_number];
        this.batch_size = batch_size;

        createDataSet();
    }

    public void createDataSet(){
        double min = -10.0;
        double max = 10.0;

        for (int i = 0; i < dataset_length; i++) {
            double x = getRanDouble(min, max);
            double y = getRanDouble(min, max);

            data[i] = new double[]{x, y};
            //System.out.println(x+" "+y);
            results[i] = fx(x, y);
        }
    }

    public void setRandomBatch(){
        if (batch_size==dataset_length){
            batch_data = data;
            batch_results = results;
            return;
        }

        batch_data = new double[batch_size][input_neuron_number];
        batch_results = new double[batch_size][output_neuron_number];

        LinkedList<Integer> already_used_r = new LinkedList<Integer>();

        for (int i = 0; i < batch_size; i++) {
            int r;
            while(already_used_r.contains(r = getRanInt(0, dataset_length)));

            batch_data[i] = data[r].clone();
            batch_results[i] = results[r].clone();

            already_used_r.add(r);
        }

    }

    public double[][] getRandomData(){
        int rand_d = getRanInt(0, dataset_length);

        return new double[][]{data[rand_d], results[rand_d]};
    }



    public static double[] fx(double x, double y){
        //boolean f = (2*x-5)<=y && y<=(2*x+5);
        boolean f = y>=5;

        return f?new double[]{1, 0}:new double[]{0, 1};
    }

    public static int getRanInt(int min, int max){
        return min + (int)(Math.random() * ((max - min)));
    }

    public static double getRanDouble(double min, double max){
        double num = min + ((max - min) * rand.nextDouble());
        return num;
    }
}
