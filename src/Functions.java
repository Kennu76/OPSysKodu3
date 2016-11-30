import java.lang.reflect.Array;
import java.util.*;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 * Created by alk_ on 10/26/16.
 * Funktsioonide klass on avalik aga inimesed kellele see jagatud on teadlikud, et kopeerimine on akadeemiline petturlus
 * Kirjutas Ott Oopkaup
 */
public class Functions {
    public static int teepikkus(ArrayList in){
        int teepikkus = 0;
        for (int k = 0; k < (in.size()-1); k++){

        }
    }

    public static String[] FCFS(String in){
        ArrayList numbrid = new ArrayList();
        int teepikkus = 0;

        numbrid.add(10);
        for (int k = 0; k < (in.split(",").length); k++){
            numbrid.add(Integer.parseInt(in.split(",")[k]));
        }



        return (String[])numbrid.toArray();
    }

    public static String[][] test(String s){

        return null;
    }

}