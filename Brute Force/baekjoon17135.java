import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17135 {
	static int N, M, D, ans = 0;
	static int[] enemyX = new int[3], enemyY = new int[3];
	static int[][] map, visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		setArcher(0, 0);
		System.out.println(ans);
	}

	private static void setArcher(int numArcher, int preidx) {
		if(numArcher == 3){
			//�ü��� 3�� ��ġ ��
			ans = Math.max(attackEnemy(), ans);
			return;
		}
		for (int i = preidx; i < M; i++) {
			map[N][i] = 1;
			setArcher(numArcher+1, i+1);
			map[N][i] = 0;
		}
	}

	private static int attackEnemy() {
		int numEn = 0;
		visit = new int[N+1][M];
		for (int i = N; i >= 1; i--) {
			// N��° �ٿ� �ִ� �ü��� �� �پ� ���鼭 ���
			// ���Ͱ� �� �پ� �������� �Ͱ� ����
			findEnemy(i);
			numEn = attack(numEn);
		}
		
		return numEn;
	}

	private static int attack(int numEn) {
		for (int i = 0; i < 3; i++) {
			//�ü��� ���� �������� ��, �ش� ���� �ٸ� �ü��� �̹� ������ ��찡 �ƴ� �� ����
			if(enemyX[i] != -1 && visit[enemyX[i]][enemyY[i]] != 1){
				visit[enemyX[i]][enemyY[i]] = 1;
				numEn++;
			}
		}
		return numEn;
	}

	private static void findEnemy(int row) {
		int num = 0;
		//�ü� �� �� ���
		for (int i = 0; i < M; i++) {
			if(map[N][i] == 1){
				//�Ÿ��� 1���� D������ �κ��� Ž��
				//�Ÿ��� ���� ���������� ���ʺ��� Ž��
				loop:
				for (int dis = 1; dis <= D; dis++) {
					int mx = 1, my = dis*(-1)+1;
					for (; mx <= dis; mx++, my++) {
						if(row - mx >= 0 && i + my >= 0 && i + my < M){
							if(map[row - mx][i + my] == 1 && visit[row - mx][i + my] != 1){
								enemyX[num] = row - mx;
								enemyY[num++] = i + my;
								break loop;
							}
						}
					}
					mx -= 2;
					for (; mx >= 1 && dis != 1; mx--, my++) {
						if(row - mx >= 0 && i + my >= 0 && i + my < M){
							if(map[row - mx][i + my] == 1 && visit[row - mx][i + my] != 1){
								enemyX[num] = row - mx;
								enemyY[num++] = i + my;
								break loop;
							}
						}
					}
					
					if(dis == D){
						enemyX[num] = -1;
						enemyY[num++] = -1;
					}
				}
			}
		}
	}
}
