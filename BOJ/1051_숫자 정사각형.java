import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int row, col;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row][col];
		String str;
		for (int i = 0; i < row; i++) {
			str = br.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bw.write(String.valueOf(func()));
		bw.close();
		br.close();
	}

	private static int func() {
		int d = Math.min(row, col);

		while(d-- > 0) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(i + d >= row || j + d >= col)
						continue;

					if(isEqual(map[i][j], map[i + d][j], map[i][j + d], map[i + d][j + d]))
						return (int)Math.pow((d + 1), 2);
				}
			}
		}

		return -1;
	}

	private static boolean isEqual(int a, int b, int c, int d) {
		if(a == b && b == c && c == d && d == a)
			return true;
		return false;
	}
}
