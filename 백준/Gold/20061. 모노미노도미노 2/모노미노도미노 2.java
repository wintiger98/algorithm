import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;   // 보드판
    static boolean[][] tileBoard; // 타일 여부
    static int score;       // 점수

    public static void main(String[] args) throws IOException {
        // 보드판 만들기
        makeBoard();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // t=1, 1x1 (x,y)
            // t=2, 1x2 (x,y), (x, y+1)
            // t=3, 2x1 (x,y), (x+1, y)
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            List<Position> blocks = new ArrayList<>();
            blocks.add(new Position(x, y));
            if(t == 2) {
                blocks.add(new Position(x, y+1));
            } else if(t == 3) {
                blocks.add(new Position(x+1, y));
            }
            monomono(blocks, true);
            monomono(blocks, false);
            checkRow();
            checkColumn();
            checkLightColor(true);
            checkLightColor(false);
        }

//        printTileBoard("최종");
        System.out.println(score);
        System.out.println(getTileCount());
    }

    private static void checkLightColor(boolean isBlue) {
        if(isBlue) {
            // 하늘색 확인
            for (int j = 4; j <= 5; j++) {
                for (int i = 0; i <= 3; i++) {
                    if (tileBoard[i][j]) {
                        // 4번열(하늘색 0번열)에 있다면, 다른 거 확인안하고 그냥 두칸옮기기
                        if (j == 4) {
                            for (int col = 9; col >= 4; col--) {
                                for(int row = 0; row <= 3; row++) {
                                    tileBoard[row][col]=tileBoard[row][col-2];
                                }
                            }
                        } else { // 5번열에 있다면, 해당 열만 이동
                            for (int col = 9; col >= 5; col--) {
                                for(int row = 0; row <= 3; row++) {
                                    tileBoard[row][col]=tileBoard[row][col-1];
                                }
                            }
                        }
                        return;
                    }
                }
            }
        } else {
            // 연두색 확인
            for (int i = 4; i <= 5; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (tileBoard[i][j]) {
                        // 4번열(하늘색 0번열)에 있다면, 다른 거 확인안하고 그냥 두칸옮기기
                        if (i == 4) {
                            for (int row = 9; row >= 4; row--) {
                                for(int col = 0; col <= 3; col++) {
                                    tileBoard[row][col]=tileBoard[row-2][col];
                                }
                            }
                        } else { // 5번열에 있다면, 해당 열만 이동
                            for (int row = 9; row >= 5; row--) {
                                for(int col = 0; col <= 3; col++) {
                                    tileBoard[row][col]=tileBoard[row-1][col];
                                }
                            }
                        }
                        return;
                    }
                }
            }
        }
    }

    private static void checkRow() {
        for(int i=9; i>=6; i--) {
            int tileCnt = 0;
            for(int j=0; j<4; j++) {
                // 타일 개수 세기
                if(tileBoard[i][j]) tileCnt++;
            }

            // 만약 꽉채웠으면
            if(tileCnt == 4) {
                score++; // 점수 올리기
                // 해당 행 삭제
                for(int k=0; k<4; k++) {
                    tileBoard[i][k] = false;
                }
                // 위에꺼 밑으로 한칸씩(6부터로 하자)
                for(int j=i-1; j>=3; j--) {
                    for(int k=0; k<4; k++) {
                        tileBoard[j+1][k] = tileBoard[j][k];
                    }
                }
                i=10;
            }
        }
    }

    private static void checkColumn() {
        for(int i=9; i>=6; i--) {
            int tileCnt = 0;
            for(int j=0; j<4; j++) {
                // 타일 개수 세기
                if(tileBoard[j][i]) tileCnt++;
            }

            // 만약 꽉채웠으면
            if(tileCnt == 4) {
                score++; // 점수 올리기
                // 해당 열 삭제
                for(int k=0; k<4; k++) {
                    tileBoard[k][i] = false;
                }
                // 왼쪽꺼 오른쪽으로 한칸씩(어차피 연한색은 비어있는게 보장되니까 5부터로 하자)
                for(int j=i-1; j>=3; j--) {
                    for(int k=0; k<4; k++) {
                        tileBoard[k][j+1] = tileBoard[k][j];
                    }
                }
                i=10;
            }
        }
    }

    private static void monomono(List<Position> blocks, boolean isBlue) {
        // 방향 배열
        int[] dir = new int[2];
        if(isBlue) {
            // 오른쪽 이동
            dir[1] = 1;
        } else {
            // 왼쪽 이동
            dir[0] = 1;
        }
        // 클론 때리기
        Position[] blockArray = new Position[blocks.size()];
        for(int i=0; i<blocks.size(); i++) {
            blockArray[i] = blocks.get(i).makeClone();
        }
        boolean isContinue = true;

        while(true) {
            for(Position block: blockArray) {
                block.x += dir[0];
                block.y += dir[1];
                // 만약 영역을 벗어나거나 이미 타일 있는 곳으로 가면 뒷걸음질 치고 끝
                if((block.x >= 10 || block.y >= 10) || tileBoard[block.x][block.y]) {
                    isContinue = false;
                }
            }
            if(!isContinue) {
                for (Position block : blockArray) {
                    block.x -= dir[0];
                    block.y -= dir[1];

                    // 타일에 놓기
                    tileBoard[block.x][block.y] = true;
                }
                break;
            }
        }
    }

    private static int getTileCount() {
        int cnt = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(tileBoard[i][j]) cnt++;
            }
        }
        return cnt;
    }

    static void makeBoard() {
        tileBoard = new boolean[10][10];

    }

    static void printTileBoard(String str) {
        System.out.println(str);
        for(boolean[] b : tileBoard) {
            System.out.println(Arrays.toString(b));
        }
        System.out.println();
    }

    static class Position {
        int x, y;

        @Override
        public String toString() {
            return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }

        public Position(
            int x,
            int y
        ) {
            this.x = x;
            this.y = y;
        }

        public Position makeClone() {
            return new Position(this.x, this.y);
        }
    }
}