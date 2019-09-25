/*

[문제]
오늘은 신승원의 생일이다.

박승원은 생일을 맞아 신승원에게 인천국제공항을 선물로 줬다.

공항에는 G개의 게이트가 있으며 각각은 1에서 G까지의 번호를 가지고 있다.

공항에는 P개의 비행기가 순서대로 도착할 예정이며, 당신은 i번째 비행기를 1번부터 gi (1 ≤ gi ≤ G) 번째 게이트중 하나에 영구적으로 도킹하려 한다. 
비행기가 도킹된 게이트에는 다른 비행기가 도착할 수 없다.

이렇게 공항을 운영할 경우 간혹 비행기가 어떤 곳에도 도킹하지 못하는 사고가 발생한다. 
이러한 사고가 일어나면 공항의 평판이 급속히 나빠져, 이후 어떤 비행기도 도착하지 않으려 할 것이다.

신승원은 가장 많은 비행기를 공항에 도킹시켜서 박승원을 행복하게 하고 싶어한다. 승원이는 비행기를 최대 몇 대 도킹시킬 수 있는가?

[입력]
첫 번째 줄에는 게이트의 수 G (1 ≤ G ≤ 105)가 주어진다.

두 번째 줄에는 비행기의 수 P (1 ≤ P ≤ 105)가 주어진다.

이후 P개의 줄에 gi (1 ≤ gi ≤ G) 가 주어진다.

[출력]
승원이가 도킹시킬 수 있는 최대의 비행기 수를 출력한다.

[풀이]
자신이 도킹할 수 있는 가장 큰 수의 게이트부터 도킹한다.
그리고 자신이 어느 게이트에도 도킹할 수 없다면 반복문을 탈출한다.
부모 지정 과정에서 1을 빼고 지정하면 유니온과정은 필요 없다.

*/
import java.io.*;

public class Main {
	
	static int G, P;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}
		int res = 0;
		
		for (int i = 1; i <= P; i++) {
			int tmp = Integer.parseInt(br.readLine());
			int d = find(tmp);
			
			if (d == 0)
				break;
			
			parent[d] = d - 1;
			res++;
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static int find(int x) {
		if (x == parent[x])
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
}