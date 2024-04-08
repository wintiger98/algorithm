package Codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static int remainTurretCnt;
    static Turret[] turrets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Turret> turretList = new ArrayList<>();
        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] > 0) {
                    Turret turret = new Turret(i, j, arr[i][j]); // 터렛 생성
                    turretList.add(turret);
                    remainTurretCnt++;
                }
            }
        }
        // 터렛 만들기
        turrets = new Turret[turretList.size()];
        for(int i=0; i<turretList.size(); i++) {
            turrets[i] = turretList.get(i);
        }

        for(int i=0; i<K; i++) { // K번 반복
            if(remainTurretCnt <= 1) break; // 부서지지않은 포탑 1개되면 중지
            // 1. 공격자 선정
            Arrays.sort(turrets);
            Turret attacker = turrets[0];
            for(int j=0; j<turrets.length; j++) { // power가 0이 아닌 첫 포탑 찾기
                if(turrets[j].power == 0) continue;
                attacker = turrets[j];
                break;
            }
            attacker.powerUp(); // 파워업
            // 2. 공격
            attacker.setTarget(turrets[turrets.length-1]); // 공격상대 선정
            attacker.attack(); // 공격
            // 3. 포탑 부서짐
            for(int j=0; j<turrets.length; j++) {
                if(turrets[j].isCrushed) continue;
                if(turrets[j].power == 0) turrets[j].isCrushed = true;
            }
            // 4. 포탑 정비

        }
    }

    static class Turret implements Comparable<Turret>{
        int x, y; // 좌표
        int power; // 공격력
        int lastAttackTime; // 마지막 공격 타임
        Turret target; // 타겟
        boolean isCrushed; // 부서졌는지 여부
        public Turret(int x, int y, int power) {
            this.x = x;
            this.y = y;
            this.power = power;
        }
        void powerUp() {
            this.power += N + M;
        }

        void setTarget(Turret target) {
            this.target = target;
        }

        boolean laserAttack() {
            return false;
        }

        void cannonAttack() {

        }

        void attack() {
            if(!laserAttack()) cannonAttack();
        }

        @Override
        public String toString() {
            return "Turret{" +
                "x=" + x +
                ", y=" + y +
                ", power=" + power +
                ", lastAttackTime=" + lastAttackTime +
                '}';
        }

        @Override
        public int compareTo(Turret o) {
            if(this.power == o.power) { // 공격력이 같으면
                if(this.lastAttackTime == o.lastAttackTime) { //그것도 같으면
                    if((this.x + this.y) == (o.x + o.y)) { // 그것도 같으면
                        return o.y - this.y; // 열이 가장 큰 포탑
                    }
                    return (o.x + o.y) - (this.x + this.y); // 행과 열의 합이 가장 큰 포탑
                }
                return o.lastAttackTime - this.lastAttackTime; // 가장 최근에 공격한
            }
            return this.power - o.power; // 공격력이 가장 낮은
        }
    }
}
