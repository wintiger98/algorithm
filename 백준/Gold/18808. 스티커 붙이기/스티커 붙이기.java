import java.util.*;
import java.io.*;

public class Main {
    static int N, M; // 노트북 크기
    static int[][] notebook; // 노트북
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // 스티커 개수
        int answer = 0; // 붙인 스티커 칸 개수

        notebook = new int[N][M]; // 노트북 초기화

        // 스티커 입력
        for(int k=0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 스티커 만들기
            int[][] sticker = new int[R][C];
            // 스티커 칸 개수
            int stickerCnt = 0;

            for(int i=0; i<R; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<C; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    sticker[i][j] = num;
                    // 스티커 칸 개수 올리기
                    if(num == 1) stickerCnt++;
                }
            }
            // 만약 넣을 수 있다면, 스티커 칸 개수 늘리기
            if(canSticker(sticker)) answer += stickerCnt;
            // print(notebook);
        }

        System.out.println(answer);
    }

    static void print(int[][] arr) {
        System.out.println();
        for(int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }

    static boolean canSticker(int[][] sticker) {
        // 확인
        for(int i=0; i<4; i++) {
            // 네 번 확인하는데 처음은 안 움직이니까!
            if(i > 0) sticker = rotate90(sticker);
            // 넣을 수 있다면 넣고 true 반환
            if(isValid(sticker)) return true;
        }
        return false;
    }

    // 노트북에 넣을 수 있는지 여부
    static boolean isValid(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        // 해당 경우마다 sticker를 notebook에 넣어보기
        // 만약 2 이상의 값이 생기는 곳이 있다면 다음 경우로 넘어가기
        // 그런곳이 없다면 바로 true 반환 및 notebook 갈아끼우기?
        for(int baseRow=0; baseRow<N-r+1; baseRow++) {
            for(int baseCol=0; baseCol<M-c+1; baseCol++) {
                if(isValidCase(baseRow, baseCol, sticker)) {
                    putSticker(baseRow, baseCol, sticker);
                    return true;
                }
            }
        }
        return false;
    }

    static void putSticker(int baseRow, int baseCol, int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                notebook[baseRow + i][baseCol + j] += sticker[i][j];
            }
        }
    }

    static boolean isValidCase(int baseRow, int baseCol, int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(notebook[baseRow + i][baseCol + j] + sticker[i][j] > 1) return false;
            }
        }
        return true;
    }

    // 시계방향 90도 회전
    static int[][] rotate90(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        int[][] newSticker = new int[c][r];

        for(int i=0; i<c; i++) {
            for(int j=0; j<r; j++) {
                newSticker[i][j] = sticker[r-j-1][i];
            }
        }

        // System.out.println("기존");
        // print(sticker);
        // System.out.println("이후");
        // print(newSticker);
        return newSticker;
    }
}