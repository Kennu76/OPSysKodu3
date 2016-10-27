import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

	//should work, still TODO is to make the error output
	public static String[][] firstFit(String in){
		String[][] out = genEmpty();
		int[][] processes = parse(in);
		List<String> chars = chars();
		String[] mem = new String[51];
		int[] addr = new int[10];

		for (int i = 0; i < processes.length; i++) {
			//clear memory
			for (int d = 0; d < 51; d++) {
				mem[d] = "-";
			}
			mem[0] = Integer.toString(processes[i][0]) + "," + Integer.toString(processes[i][1]);
			for (int j = 0; j <= i; j++) {
				//Delete old processes and write the ones still running
				if(j<i){
					if(processes[j][1] >= 1){
						for (int k = 0; k < processes[j][0]; k++) {
							mem[addr[j]+1+k] = chars.get(j);
						}
					}
				}else{ //Find first free memory where it fits, if it is a new process
					for (int k = 0; k < 50; k++) {
						boolean flag = true;

						for (int l = 0; l < processes[i][0]; l++) {
							if(! mem[k+l+1].equals("-")){
								flag = false;
								break;
							}
						}
						if(flag){
							addr[j] = k;
							break ;
						}
					}
					for (int k = 0; k < processes[i][0]; k++) {
						try {
							mem[addr[j] + k + 1] = chars.get(j);
						}catch (Exception e){
							out[i] = null;
							return out;
						}
					}
				}
				processes[j][1]--;
			}
			out[i] = Arrays.copyOf(mem,mem.length);
		}
		return out;
	}

	//should work
	public static String[][] worstFit(String in){
		String[][] out = genEmpty();
		int[][] processes = parse(in);
		List<String> chars = chars();
		String[] mem = new String[51];
		int[] addr = new int[10];

		for (int i = 0; i < processes.length; i++) {
			//clear memory
			for (int d = 0; d < 51; d++) {
				mem[d] = "-";
			}
			mem[0] = Integer.toString(processes[i][0]) + "," + Integer.toString(processes[i][1]);
			for (int j = 0; j <= i; j++) {
				//Delete old processes and write the ones still running
				if(j<i){
					if(processes[j][1] >= 1){
						for (int k = 0; k < processes[j][0]; k++) {
							mem[addr[j]+1+k] = chars.get(j);
						}
					}
				}else{ //Find largest free memory where it fits, if it is a new process
					//Key is the size, value will be index
					HashMap<Integer,Integer> map = new HashMap<>();
					loop:
					for (int k = 0; k < 50; k++) {
						if(mem[k].equals("-")){
							for (int l = 0; l < 50-k; l++) {
								if(!mem[k+l].equals("-")){
									map.put(l,k);
									k+= l;
									continue loop;
								}
							}
							map.put(50-k,k-1);
							break;
						}

					}

					int size = 0;

					for (Integer integer : map.keySet()) {
						System.out.println(integer);
						System.out.println(map.get(integer));
						System.out.println();
						if(integer > size){
							size = integer;
							addr[j] = map.get(integer);
						}
					}

					//write into output
					for (int k = 0; k < processes[i][0]; k++) {
						try {
							mem[addr[j] + k + 1] = chars.get(j);
						}catch (Exception e){
							out[i] = null;
							return out;
						}
					}
				}
				processes[j][1]--;
			}
			out[i] = Arrays.copyOf(mem,mem.length);
		}
		return out;
	}
}
