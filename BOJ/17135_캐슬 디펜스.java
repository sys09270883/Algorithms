/*
https://www.acmicpc.net/problem/17135
[문제]
캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다. 게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다. 
격자판은 1×1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대 하나이다. 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.

성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다. 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다. 
각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다. 
궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 
같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다. 궁수의 공격이 끝나면, 적이 이동한다. 
적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다. 모든 적이 격자판에서 제외되면 게임이 끝난다. 

게임 설명에서 보다시피 궁수를 배치한 이후의 게임 진행은 정해져있다. 따라서, 이 게임은 궁수의 위치가 중요하다. 
격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.

격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.

[입력]
첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다. 둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸이다.

[출력]
첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.

[제한]
3 ≤ N, M ≤ 15
1 ≤ D ≤ 10

[풀이]
이 문제에서 필요한 것은 적의 위치만 알면 된다. 따라서 2차원 배열 대신에 적들의 좌표 값을 리스트에 넣어준다.
2. 궁수를 배치한다.
  -> 궁수가 3명 뿐이여서 3중 for문으로 했는데 백트래킹으로 위치를 정해줄 수도 있다.
3. 궁수와 모든 적의 거리를 측정한다.
  3-1. 수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격해야 한다.
  3-2. 그리고 적을 중복으로 공격할 수 있기 때문에 이 부분을 처리해주어야 한다.
  3-3. Point 클래스에 적이 살아있는지 여부를 체크하는 isAlive 변수를 만들고, 공격을 당하면 isAlive를 false로 바꾼다.
4. 궁수의 공격이 끝나면 적이 한 칸 전진한다.
5. 3~4 과정을 N번 반복한다.
6. 2~5 과정을 ɴC₃번 반복하면서 제거할 수 있는 최대 수를 갱신한다.

 + 구현이 생각보다 까다로웠던 문제였다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int N, M, D, killCnt;
	static List<Point> enemyList;
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		M = io.nextInt();
		D = io.nextInt();
		enemyList = new ArrayList<Point>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (io.nextInt() == 1)
					enemyList.add(new Point(i, j));
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					List<Point> archerList = getArchers(i, j, k);
					List<Point> copyEnemyList = getCopyEnemies();
					int cnt = 0;
					
					for (int l = 0; l < N; l++) {
						cnt = attack(archerList, copyEnemyList, cnt);
						moveEnemies(copyEnemyList);
					}
				}
			}
		}
		
		io.write(killCnt);
	}
	
	private static void moveEnemies(List<Point> copyEnemyList) {
		Iterator<Point> it = copyEnemyList.listIterator();
		
		while (it.hasNext()) {
			Point p = it.next();
			
			if (++p.x >= N)
				it.remove();
		}
	}
	
	private static int attack(List<Point> archerList, List<Point> copyEnemyList, int cnt) {
		for (Point archer : archerList) {
			Point target = null;
			int dist = -1;
			
			for (Point enemy : copyEnemyList) {
				if (enemy.x >= N)
					continue;
				
				int curDist = distance(enemy, archer);
				
				if (curDist <= D) {
					if (target == null) {
						target = enemy;
						dist = curDist;
					}
					
					else {
						if (dist > curDist) {
							target = enemy;
							dist = curDist;
						}
						
						else if (dist == curDist) {
							if (target.y > enemy.y)
								target = enemy;
						}
					}
				}
				
			}
			
			if (target != null)
				target.isAlive = false;
		}
		
		Iterator<Point> it = copyEnemyList.listIterator();
		
		while (it.hasNext()) {
			Point p = it.next();
			if (!p.isAlive) {
				cnt++;
				it.remove();
			}
		}
		
		killCnt = Math.max(killCnt, cnt);
		
		return cnt;
	}
	
	private static int distance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
	private static List<Point> getArchers(int... archers) {
		List<Point> archerList = new ArrayList<Point>();
		
		for (int i = 0; i < 3; i++) {
			archerList.add(new Point(N, archers[i]));
		}
		
		return archerList;
	}
	
	private static List<Point> getCopyEnemies() {
		List<Point> copy = new ArrayList<Point>();
		
		for (Point enemy : enemyList) {
			copy.add(new Point(enemy));
		}
		
		return copy;
	}
	
}

class Point {
	int x, y;
	boolean isAlive;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.isAlive = true;
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
		this.isAlive = true;
	}
	
}

class FastIO {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	FastIO() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}

	String next() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return st.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	long nextLong() {
		return Long.parseLong(next());
	}

	double nextDouble() {
		return Double.parseDouble(next());
	}

	String nextLine() {
		String str = null;
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	void write(double d) throws IOException {
		bw.write(String.valueOf(d));
		close();
	}

	void write(int i) throws IOException {
		bw.write(String.valueOf(i));
		close();
	}

	void write(long l) throws IOException {
		bw.write(String.valueOf(l));
		close();
	}

	void write(StringBuilder sb) throws IOException {
		bw.write(sb.toString().trim());
		close();
	}

	void write(String str) throws IOException {
		bw.write(str.trim());
		close();
	}

	void close() throws IOException {
		bw.close();
		br.close();
	}
}
