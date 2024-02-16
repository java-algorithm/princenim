package main.week1.BOJ1543;

import java.util.Scanner;

/**
 * 문서 검색
 * @author hazel
 */
public class BOJ1543 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine(); //next를 사용하면 공백단위로 잘리져서 예제 2 입력이 불가능함.
        String word = scanner.nextLine();

        int cnt = 0;
        String text = str.replace(word, "!");
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '!') {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

}
