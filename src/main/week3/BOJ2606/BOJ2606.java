package main.week3.BOJ2606;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 바이러스
 * DFS로 풀이
 * @author hazel
 */
public class BOJ2606 {
    static int cn; //컴퓨터의 수
    static int n; //연결되어있는 쌍의 수
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();//양방향 인접 리스트
    static boolean[] visited;
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        cn = scanner.nextInt();
        n = scanner.nextInt();

        // 그래프 초기화
        for (int i = 0; i <= cn; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[cn + 1];


        //값 추가
        for (int i = 0; i < n; i++) {
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();
            graph.get(n1).add(n2);
            graph.get(n2).add(n1); //양방향이니까 반대로 추가
        }

        //System.out.println(graph);

        //1부터 시작
        dfs(1);
        System.out.println(cnt - 1);

    }

    public static void dfs(int x) {
        //현재 방문한 노드 처리
        visited[x] = true;//방문처리
        cnt++;

        for (int i = 0; i < graph.get(x).size(); i++) {
            int y = graph.get(x).get(i);
            if (!visited[y]) { //방문처리 안 했을때
                visited[y] = true; //방문처리
                dfs(y);
            }
        }
    }
}
