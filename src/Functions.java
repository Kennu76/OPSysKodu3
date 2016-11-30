import java.util.*;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 * Created by alk_ on 10/26/16.
 * Funktsioonide klass on avalik aga inimesed kellele see jagatud on teadlikud, et kopeerimine on akadeemiline petturlus
 * Kirjutas Ott Oopkaup
 */
public class Functions {

    public static ArrayList FCFS(String in){
        ArrayList numbrid = new ArrayList();

        for (int k = 0; k < length(in.split(",")); k++){
            numbrid.add(Integer.parseInt(in.split(",")[k]));
        }



        return numbrid;
    }

    public static String[][] test(String s){

        return null;
    }

}