package main.week3.BOJ11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 연결요소의 개수
 * DFS
 * @author hazel
 */
public class BOJ11724 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); //양방향 인접리스트
    static int M, N, U, V;
    static int cnt;
    static boolean[] visited; //방문처리할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mn = br.readLine().split(" ");
        N = Integer.parseInt(mn[0]); //정점
        M = Integer.parseInt(mn[1]); //간선

        //초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            String[] uv = br.readLine().split(" ");
            U = Integer.parseInt(uv[0]);
            V = Integer.parseInt(uv[1]);
            graph.get(U).add(V);
            graph.get(V).add(U); //양방향

        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
                cnt++; //더 이상 연결된 정점이 없으면 빠져 나오므로 1 증가
            }
        }
        System.out.println(cnt);
    }

    public static void dfs(int n) {
        visited[n] = true;
        for (int i = 0; i < graph.get(n).size(); i++) {
            int next = graph.get(n).get(i); //인덱스 주의하기!!
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
