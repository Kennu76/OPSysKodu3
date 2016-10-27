import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alk_ on 10/26/16.
 */
public class Functions {


	private static List<String> chars() {
		List<String> chars = new ArrayList();
		chars.addAll(Arrays.asList(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
		return chars;
	}

	private static String[][] genEmpty(){
		String[][] out = new String[10][51];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 51; j++) {
				out[i][j] = "-";
			}
		}

		return out;
	}

	private static int[][] parse(String in){
		int[][] out = new int[10][2];
		String[] processes = in.split("[;]");

		for (int i = 0; i < processes.length; i++) {
			out[i][0] = Integer.parseInt(processes[i].split("[,]")[0]);
			out[i][1] = Integer.parseInt(processes[i].split("[,]")[1]);
		}

		return out;
	}

	public static String[][] firstFit(String in){
		String[][] out = genEmpty();
		int[][] processes = parse(in);
		List<String> chars = chars();
		
		String[] mem = new String[51];

		for (int i = 0; i < 51; i++) {
			mem[i] = "-";
		}

		int[] addr = new int[10];

		//P6mst, iga tsykkel ta peab kontrollim
		//kus on vanad ja hoidma neid m'lus kui vaja
		//ja esimene koht kuhu panna uus

		//Lisab esimene malu rea, see pole automaatne


		out[0] = mem;


		for (int i = 0; i < processes.length; i++) {
			mem[0] = Integer.toString(processes[i][0]) + "," + Integer.toString(processes[i][1]) ;
			System.out.println(Arrays.asList(mem).toString());
			if(i == 0){
				for (int j = 1; j <= processes[0][1]; j++) {
					mem[j] = chars.get(0);
					addr[0] = 1;
				}
				processes[0][1] = processes[0][1]-1;
				continue;
			}
			
			for (int j = 0; j <= i ; j++) {
				if(processes[j][1] == 0){
					continue;
				}else{
					for (int k = 0; k < processes[j][1]; k++) {
						mem[1+k+addr[j]] = chars.get(j);
					}
					processes[j][1] = processes[j][1]-1;
				}
			}
			out[i] = Arrays.copyOf(mem,mem.length);


		}



		return out;
	}
    public static String[][] firstFitNew(String in){
        String[][] out = genEmpty();
        ArrayList jarjend = new ArrayList();
        String[] numbrid = in.split(";");
        int i = 1;

        String[] mem = new String[51];

        for (int j = 0; j < 51; j++) {
            mem[j] = "-";
        }
		mem[0] = "-";
		out[0] = Arrays.copyOf(mem,mem.length);
        for(String item : numbrid ){
            mem = new String[51];
            for (int j = 0; j < 51; j++) {
                mem[j] = "-";
            }
            mem[0] = item;
            out[i] = Arrays.copyOf(mem,mem.length);
			ArrayList asi = new ArrayList();
            asi.add(Integer.parseInt(item.split(",")[0]));
            asi.add(Integer.parseInt(item.split(",")[1]));
            jarjend.add(asi);
            i++;
        }
        System.out.println(jarjend.toString());

        return out;
    }

}