import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon13398 {
	static int ans;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][2];	// [0]�� �ѹ��� ���� �������� ���� ��� [1]�� �ѹ� ���� ������ ���
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = arr[0];
		ans = arr[0];
		
		for (int i = 1; i < N; i++) {
			// dp[i - 1][0]�� 0���� ũ�� �ʴٸ� ���� �����ϴ°� �� ũ��
			dp[i][0] = dp[i - 1][0] > 0 ? dp[i - 1][0] + arr[i] : arr[i];
			// �� ���� ���� �������� ���� ��� + arr[i]�� ������ ���� ���
			// �� �� ���� ������ ��� + arr[i]�� ���ϴ� ���
			// �� ��츦 ���Ͽ� ū ���� �ִ´�.
			dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
			
			ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
		}
		
		System.out.print(ans);
	}
}
