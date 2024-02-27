package main.week2.BOJ15686;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 치킨배달
 * @author hazel
 */
public class BOJ15686 {
    static int N, M;
    static int[][] map;
    static ArrayList<Point> person; //집의 위치를 저장하는 리스트
    static ArrayList<Point> chicken; //치킨집 위치를 저장하는 리스트
    static int ans;
    static boolean[] open;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();

        //도시의 정보를 map으로 받음.
        // 미리 집과 치킨집에 해당하는 좌표를 ArrayList에 넣기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    person.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Point(i, j)); //도시에 있는 치킨집을 다 배열애 추가
                }
            }
        }

        ans = Integer.MAX_VALUE;
        open = new boolean[chicken.size()];


        DFS(0, 0); //m개의 치킨집을 뽑음.
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int start, int cnt) {
        if (cnt == M) {
            int res = 0;

            for (int i = 0; i < person.size(); i++) {//집
                int temp = Integer.MAX_VALUE;

                // 어떤 집과 치킨집 중 open한 치킨집의 모든 거리를 비교
                // 그 중, 최소 거리를 구한다.
                for (int j = 0; j < chicken.size(); j++) { //치킨집

                    if (open[j]) {
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x)
                                + Math.abs(person.get(i).y - chicken.get(j).y);

                        temp = Math.min(temp, distance);
                        //System.out.println("tmp: " + temp);
                    }
                }
                res += temp;
                //System.out.println("res: " + res);
            }
            ans = Math.min(ans, res);
            return;
        }

        // 백트래킹
        for (int i = start; i < chicken.size(); i++) {
            open[i] = true;
            DFS(i + 1, cnt + 1);//
            open[i] = false;
        }
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
