package main.week2.BOJ5430;

import java.io.*;
import java.util.*;

/**
 * AC
 * @author hazel
 */
public class BOJ5430 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 케이스 개수 T 입력
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            // 함수 배열 cmd 입력
            String p = br.readLine();

            // 배열에 들어있는 수 n 입력
            int n = Integer.parseInt(br.readLine());

            // 배열을 입력 받을 Deque 타입 선언
            Deque<Integer> dq = new ArrayDeque<>();

            // StringTokenizer로 문자열 분리 "[", "]", "," 3개로 분리
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");

            // 정수형태로 Deque에 입력
            for (int i = 0; i < n; i++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

            // 뒤집기 상태를 관리할 flag
            boolean flag = true;
            // 에러 상태를 관리할 error
            boolean error = false;

            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == 'R') {
                    flag = !flag;
                } else if (p.charAt(i) == 'D') {
                    if (!dq.isEmpty()) {
                        if (flag) {
                            dq.pollFirst(); //맨 앞의 요소를 하나 뽑아서 제거
                        } else {
                            dq.pollLast();//맨 뒤의 요소를 하나 뽑아서 제거
                        }
                    } else { //비어있으면 에러 체크 후 break
                        error = true;
                        break;
                    }
                }
            }

            // 출력 구현
            // dq에 상태에 따라 뒤집어야하므로, dq를 List로 변환
            ArrayList<Integer> arr = new ArrayList<>(dq);

            if (!error) {
                if (flag) {
                    bw.write(arr.toString().replace(" ", ""));
                } else {
                    Collections.reverse(arr);
                    bw.write(arr.toString().replace(" ", ""));
                }
            } else {
                bw.write("error");
            }

            bw.write("\n");
            bw.flush();
        }
    }
}

