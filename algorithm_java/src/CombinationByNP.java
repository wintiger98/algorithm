import java.util.Scanner;

public class CombinationByNP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int R = sc.nextInt();
        int[] input = new int[N]; // 입력 수 배열
        int[] selected = new int[N]; // 선택된 거

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        // step0 : 맨 뒤 R개 1로 초기화
        for (int i = N - 1; i > N - 1 - R; i--)
            selected[i] = 1;

        do {
            for (int i = 0; i < N; i++) {
                if (selected[i] == 1) {
                    System.out.print(input[i] + " ");
                }
            }
            System.out.println();

        } while (np(selected));
        sc.close();
    }

    public static boolean np(int[] p) { // p: 현 순열
        final int N = p.length;
        // step1 : 교환위치 찾기(꼭대기를 찾으면, 꼭대기 이전이 교환 위치)
        int i = N - 1;
        while (p[i - 1] >= p[i])
            --i; // 뒤부터, 오르막 확인하면서 이동

        if (i == 0) // 내림차순 정렬인 상태 (= 마지막 순열) -> np 없음(다음 순열 없음)
            return false;

        // step2 : 교환위치(i-1)에 넣을 값 뒤쪽부터 찾기(큰 값 중 최소값)
        int j = N - 1;
        while (p[i - 1] >= p[j])
            --j;

        // step3 : 교환위치(i-1) 값과 찾은 위치(j) 값 교환
        swap(p, i - 1, j);

        // step4 : 꼭대기(i)위치부터 맨뒤까지 오름차순 정렬
        int k = N - 1;
        while (i < k)
            swap(p, i++, k--); // 바꾸면서, i는 오른쪽으로, k는 왼쪽으로

        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
