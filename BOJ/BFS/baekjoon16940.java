import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon16940 {
	static int[] ans;
	static boolean[] visited;
	static ArrayList<Integer>[] connectedNode;
	static Queue<Integer> que;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		connectedNode = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			connectedNode[i] = new ArrayList<>();
		}
		ans = new int[N + 1];
		visited = new boolean[N + 1];
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			connectedNode[node1].add(node2);
			connectedNode[node2].add(node1);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			ans[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.print(ans[1] == 1 && isCorrectOrder() ? 1 : 0);
		
	}

	private static boolean isCorrectOrder() {
		que = new LinkedList<Integer>();
		que.offer(1);
		visited[1] = true;
		int idx = 2;
		
		while(!que.isEmpty()) {
			// queue poll �� ���� ���ؼ� visit���� ���� ���ҵ� ���� �湮 üũ�ϰ� ���� ���� ����
			int node = que.poll();
			int size = 0;
			
			for (int i = 0; i < connectedNode[node].size(); i++) {
				int nNode = connectedNode[node].get(i);
				if(!visited[nNode]){
					visited[nNode] = true;
					size++;
				}
			}
			
			// ans �迭���� ���� ������ŭ queue�� �����鼭 visit�ߴ��� Ȯ�� �������� Ż��
			for (int i = idx; i < idx + size; i++) {
				int nNode = ans[i];
				if(visited[nNode]){
					que.offer(nNode);
				}
				else return false;
			}
			
			idx += size;
		}
		
		
		return true;
	}
}
