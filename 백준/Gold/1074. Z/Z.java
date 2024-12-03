import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int value;
	static int r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		solution(0, 0, N);

		System.out.println(value);
	}

	private static void solution(int x, int y, int n) {
		if(n == 1) {
			for(int i=x; i<x+2; i++) {
				for(int j=y; j<y+2; j++) {
					if(i == r && j == c) return;
					value++;
				}
			}
		}
		n--;
		int tmpX = x + (int) Math.pow(2, n);
		int tmpY = y + (int) Math.pow(2, n);
		int tmpXX = tmpX + (int) Math.pow(2, n);
		int tmpYY = tmpY + (int) Math.pow(2, n);
	
		int tmpValue = (int) Math.pow(2, n);
		tmpValue *= tmpValue;
		
		if(r >= x && r < tmpX && c >= y && c < tmpY) { // 지
			
		} else if(r >= x && r < tmpX && c >= tmpY && c < tmpYY) { // 그
			value += tmpValue;
			y = tmpY;
		} else if(r >= tmpX && r < tmpXX && c >= y && c < tmpY) { // 재
			value += tmpValue * 2;
			x = tmpX;
		} else { // 그
			value += tmpValue * 3;
			x = tmpX;
			y = tmpY;
		}
		solution(x, y, n);
	}
}