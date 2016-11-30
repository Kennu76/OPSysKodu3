import java.util.*;
import java.util.Collections;

/**
 * Created by alk_ on 10/26/16.
 * Funktsioonide klass on avalik aga inimesed kellele see jagatud on teadlikud, et kopeerimine on akadeemiline petturlus
 * Kirjutas Ott Oopkaup
 */
public class Functions  {
    public static int teepikkus(ArrayList in){
        int teepikkus = 0;
        for (int k = 0; k < (in.size()-1); k++){
            teepikkus += Math.abs(Integer.parseInt( in.get(k).toString()) - Integer.parseInt(in.get(k+1).toString()));
        }
    return teepikkus;
    }
    public static int closest(int of, ArrayList<Integer> in) {
        int min = Integer.MAX_VALUE;
        int closest = of;

        for (int v : in) {
            final int diff = Math.abs(v - of);

            if (diff < min) {
                min = diff;
                closest = v;
            }
        }

        return closest;
    }

    public static int[] FCFS(String in){
        ArrayList<Integer> numbrid = new ArrayList<>();
        int teepikkus = 0;

        numbrid.add(10);
        for (int k = 0; k < (in.split(",").length); k++){
            numbrid.add(Integer.parseInt(in.split(",")[k]));
        }
        numbrid.add(0,teepikkus(numbrid));



        return convert(numbrid);
    }

    public static int[] test(String s){

        return null;
    }

    private static int[] convert(ArrayList<Integer> list){
        int[] out = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            out[i] = list.get(i);
        }
        return out;
    }

    public static int[] SSTF(String in){
        ArrayList<Integer> numbrid = new ArrayList<>();
        ArrayList<Integer> uued_numbrid = new ArrayList<>();
        int teepikkus = 0;

        numbrid.add(10);
        for (int k = 0; k < (in.split(",").length); k++){
            numbrid.add(Integer.parseInt(in.split(",")[k]));
        }
        uued_numbrid.add(10);
        while(numbrid.size() != 1){
            int number = numbrid.get(0);
            numbrid.remove(0);
            int closest = closest(number,numbrid);
            numbrid.remove(numbrid.indexOf(closest));
            uued_numbrid.add(closest);
            numbrid.add(0,closest);
        }
        uued_numbrid.add(0,teepikkus(uued_numbrid));



        return convert(uued_numbrid);
        //10,10,12,13,20,3,2,1,44
        //10,1,10,44,2,12,3,13,20
    }

    public static int[] LOOK(String in){
        ArrayList<Integer> numbrid = new ArrayList<>();
        ArrayList<Integer> uued_numbrid = new ArrayList<>();
        ArrayList<Integer> max_numbrid = new ArrayList<>();
        ArrayList<Integer> min_numbrid = new ArrayList<>();



        int teepikkus = 0;

        max_numbrid.add(10);
        for (int k = 0; k < (in.split(",").length); k++){
            numbrid.add(Integer.parseInt(in.split(",")[k]));
        }

        for(int i:numbrid){
            if(i>10){
                max_numbrid.add(i);
            }
            else {
                min_numbrid.add(i);
            }
        }
        for(int i:max_numbrid){

        }
        Collections.sort(max_numbrid);
        Collections.sort(min_numbrid);
        Collections.reverse(min_numbrid);
        uued_numbrid.addAll(max_numbrid);
        uued_numbrid.addAll(min_numbrid);
        uued_numbrid.add(0,teepikkus(uued_numbrid));

    return convert(uued_numbrid);
    }
    
    public static int[] CSCAN(String in){
        ArrayList<Integer> numbrid = new ArrayList<>();
        ArrayList<Integer> uued_numbrid = new ArrayList<>();
        ArrayList<Integer> max_numbrid = new ArrayList<>();
        ArrayList<Integer> min_numbrid = new ArrayList<>();



        int teepikkus = 0;

        max_numbrid.add(50);
        max_numbrid.add(10);
        
        for (int k = 0; k < (in.split(",").length); k++){
            numbrid.add(Integer.parseInt(in.split(",")[k]));
        }

        for(int i:numbrid){
            if(i>10){
                max_numbrid.add(i);
            }
            else {
                min_numbrid.add(i);
            }
        }
        for(int i:max_numbrid){

        }
        Collections.sort(max_numbrid);
        Collections.sort(min_numbrid);        
        uued_numbrid.addAll(max_numbrid);
        uued_numbrid.addAll(min_numbrid);
        uued_numbrid.add(0,teepikkus(uued_numbrid));

    return convert(uued_numbrid);
    }
}
