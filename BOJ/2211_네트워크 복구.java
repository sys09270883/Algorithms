/*
https://www.acmicpc.net/problem/2211
[����]
N(1 �� N �� 1,000)���� ��ǻ�ͷ� ������ ��Ʈ��ũ�� �ִ�. �̵� �� �� ���� ��ǻ�͵��� ���� ��Ʈ��ũ ������ �Ǿ� �־� ���� �ٸ� �� ��ǻ�� �� ����� �����ϵ��� �Ǿ� �ִ�. 
����� �� ������ ���� ���� ����Ǿ� �ִ� ȸ���� �̿��� ���� ������, ȸ���� �ٸ� ��ǻ�͸� ���ļ� ����� �� ���� �ִ�.

�� ��ǻ�͵�� ȸ���� �� ������ ���̰� �� �� �ִ�. ���� ������ ���� ����Ǿ� �ִ� ȸ���� �̿��ؼ� ����� �ϴµ� �ɸ��� �ð��� ���� �ٸ� �� �ִ�. 
������� ���� ����Ǿ� �ִ� ȸ���� ������ �� ������, �ٸ� ��ǻ�͸� ���ؼ� ����� �ϴ� ���� �� ������ ���� �ִ�. 
���� ����Ǿ� �ִ� ȸ���� ����� ��쿡�� �� ȸ���� �̿��ؼ� ����� �ϴ� �� ��� �ð���ŭ�� ��� �ȴ�. 
���� ���� ȸ���� ��ġ�� ��쿡�� �� ȸ���� �̿��ؼ� ����� �ϴ� �� ��� �ð��� �ո�ŭ�� �ð��� �ɸ��� �ȴ�.

��� ��, ��Ŀ�� ��Ʈ��ũ�� ħ���Ͽ���. ��Ʈ��ũ�� �����ڴ� �켱 ��� ȸ���� ��ǻ�͸� ������ ��, ��Ŀ�� ������ ���� �� �־���. 
�����ڴ� ��ǻ�Ϳ� ���� �ý����� ��ġ�Ϸ� �Ͽ��µ�, ���� ������ ���� �ý����� �� ���� ������ǻ�Ϳ��� ��ġ�� �� �־���.
 �� ��ǻ�Ͱ� ������ �ް� �Ǹ�, ��Ʈ��ũ�� ���� ������ǻ�Ϳ� �� ����� ������ �ǰ�, �׷��� ������ǻ�Ϳ����� ��Ʈ��ũ�� �̿��ؼ� ���� ��Ŷ�� �����ϴ� ����� ����ϱ�� �Ͽ���. 
 �غ� ��ģ ��, �����ڴ� �ٽ� ��Ʈ��ũ�� �����ϱ�� �Ͽ���. �̶�, ������ ���ǵ��� �����Ǿ�� �Ѵ�.

��Ŀ�� �ٽ� ������ �� ����� �ֱ� ������, �ּ� ������ ȸ������ �����ؾ� �Ѵ�. 
����, �׷��ٸ� �ƹ� ȸ���� �������� ������ �ǰ�����, �̷� ��� ��Ʈ��ũ�� ��뿡 ������ ����� �ȴ�. 
���� ��Ʈ��ũ�� ������ �Ŀ� ���� �ٸ� �� ��ǻ�� ���� ����� �����ϵ��� �����ؾ� �Ѵ�.
��Ʈ��ũ�� �����ؼ� ����� �����ϵ��� ����� �͵� �߿�������, ��Ŀ���� ������ �޾��� �� ���� ��Ŷ�� �����ϴ� �� �ɸ��� �ð��� �߿��� ������ �ȴ�. 
���� ������ǻ�Ͱ� �ٸ� ��ǻ�͵�� ����ϴµ� �ɸ��� �ּ� �ð���, ������ ��Ʈ��ũ���� ����ϴµ� �ɸ��� �ּ� �ð����� Ŀ������ �� �ȴ�.
������ ��Ʈ��ũ�� ���� ������ �־����� ��, ���� ������ �����ϸ鼭 ��Ʈ��ũ�� �����ϴ� ����� �˾Ƴ��� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �� ���� N, M�� �־�����. ���� M���� �ٿ��� ȸ���� ������ ��Ÿ���� �� ���� A, B, C�� �־�����. 
�̴� A�� ��ǻ�Ϳ� B�� ��ǻ�Ͱ� ��� �ð��� C (1 �� C �� 10)�� ȸ������ ����Ǿ� �ִٴ� �ǹ��̴�. 
��ǻ�͵��� ��ȣ�� 1���� N������ �����̸�, 1�� ��ǻ�ʹ� ���� �ý����� ��ġ�� ������ǻ���̴�. 
��� ����� �����ֹ��� ������� �̷������ ������, �� ȸ������ ����� �� ��ǻ�ʹ� ��� �������ε� ����� �� �ִ�.

[���]
ù° �ٿ� ������ ȸ���� ���� K�� ����Ѵ�. ���� K���� �ٿ��� ������ ȸ���� ��Ÿ���� �� ���� A, B�� ����Ѵ�. 
�̴� A�� ��ǻ�Ϳ� B�� ��ǻ�͸� �����ϴ� ȸ���� �����Ѵٴ� �ǹ��̴�. ����� ������ ������� �ϸ�, ���� ���� �� �����ϴ� ��쿡�� �ƹ� ���̳� �ϳ��� ����ϸ� �ȴ�.

[Ǯ��]
�׻� MST�� �� �˾Ҵµ� 2�� ���ǿ� ���ؼ� ���ͽ�Ʈ��� �����ߴ�.
���ͽ�Ʈ�� �����ϸ鼭 �� �ܰ踦 �����Ѵ�. (trace)
������ ȸ���� ������ �׻� N - 1�̰�, trace�� ��ȸ�ϸ鼭 �� �ܰ�� �� �ܰ踦 ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Node>> adj;
    static int[] costs;
    static int[] trace;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<ArrayList<Node>>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<Node>());
        }
        costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        trace = new int[N + 1];
        Arrays.fill(trace, -1);
        int A, B, C;
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            adj.get(A).add(new Node(B, C));
            adj.get(B).add(new Node(A, C));
        }

        dijkstra();

        res.append(N - 1).append('\n');

        for (int i = 1; i < N + 1; i++) {
            if (trace[i] != -1) {
                res.append(trace[i]).append(' ').append(i).append('\n');
            }
        }

        bw.write(res.toString().trim());
        bw.close();
        br.close();
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(1, 0));
        costs[1] = 0;

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int cur = tmp.n;
            int c = tmp.c;

            if (c > costs[cur])
                continue;

            for (Node tmp2 : adj.get(cur)) {
                int next = tmp2.n;
                int nc = tmp2.c;
                if (costs[next] > costs[cur] + nc) {
                    costs[next] = costs[cur] + nc;
                    pq.add(new Node(next, costs[next]));
                    trace[next] = cur;
                }
            }
        }
    }

}

class Node implements Comparable<Node>{
    int n, c;

    public Node(int n, int c) {
        super();
        this.n = n;
        this.c = c;
    }

    @Override
    public int compareTo(Node o) {
        // TODO Auto-generated method stub
        return this.c - o.c;
    }

}