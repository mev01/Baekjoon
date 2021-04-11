import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon14890 {
	static int N, L, ans = 0;
	static int[] dirx = { -1, 0, 1, 0 }, diry = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean isPoss = true;
		// �� �࿭�� ���鼭 �ٸ� ���̰� �ִ��� Ȯ��
		for (int i = 0; i < N; i++) {
			isPoss = true;
			check = new boolean[N];

			for (int j = 1; j < N; j++) {
				if (map[i][j] != map[i][j - 1]) {
					if(!isSetRamp(i, j - 1, i, j)){
						isPoss = false;
						break;
					}
				}
			}

			if (isPoss)
				ans++;
		}

		for (int j = 0; j < N; j++) {
			isPoss = true;
			check = new boolean[N];
			
			for (int i = 1; i < N; i++) {
				if (map[i][j] != map[i - 1][j]) {
					if(!isSetRamp(i - 1, j, i, j)){
						isPoss = false;
						break;
					}
				}
			}
			
			if (isPoss)
				ans++;
		}
		
		System.out.print(ans);
	}

	private static boolean isSetRamp(int pr, int pc, int r, int c) {
		// ���̰� 1�� �޶����� ��츸
		if (Math.abs(map[pr][pc] - map[r][c]) == 1) {
			// ���̰� ���������� ������ Ȯ���ؼ�
			if (map[pr][pc] < map[r][c]) {
				if (pr != r) {
					return isSameHeight(pr, pc, 0);
				}
				else{
					return isSameHeight(pr, pc, 3);
				}
			}
			// �������� ���� �� Ȯ��
			else {
				if (pc != c) {
					return isSameHeight(r, c, 1);
				}
				else{
					return isSameHeight(r, c, 2);
				}
			}
		}

		return false;
	}

	/**
	 * @param r �����ϴ� ��
	 * @param c �����ϴ� ��
	 * @param dir �����ϴ� ����
	 * @return dir �������� �����ϸ鼭 ��� ĭ�� ���̰� ������
	 */
	private static boolean isSameHeight(int x, int y, int dir) {
		// L�� ���̰� ���̰� ������ Ȯ��
		int r = x;
		int c = y;
		int height = map[r][c];
		
		for (int i = 0; i < L; i++, r += dirx[dir], c += diry[dir]) {
			// ����Ȯ��
			if(r >= 0 && r < N && c >= 0 && c < N){
				if(dir % 2 == 0){
					if(map[r][c] != height || check[r]) return false;
				}
				else{
					if(map[r][c] != height || check[c]) return false;
				}
			}
			else{
				return false;
			}
		}
		
		// ���� ����� üũ
		setRamp(x, y, dir);
		
		return true;
	}

	private static void setRamp(int x, int y, int dir) {
		if(dir % 2 == 0){
			for (int i = 0; i < L; i++, x += dirx[dir]) {
				check[x] = true;
			}
		}
		else{
			for (int i = 0; i < L; i++, y += diry[dir]) {
				check[y] = true;
			}
		}
		
	}
}
