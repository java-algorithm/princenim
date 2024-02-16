package main.week1.BOJ1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 보석 도둑
 * @author hazel
 */
public class BOJ1202 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); //한줄

        int n = Integer.parseInt(st.nextToken()); //보석 수
        int k = Integer.parseInt(st.nextToken()); // 보석 가격
        long answer = 0; //가방 하나의 최대무게가 100,000,000 이므로 int가 아니라 long.

        Jewelry[] jewelries = new Jewelry[n]; //보석를 담을 배열
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewelry(m, v);
        }

        Comparator<Jewelry> jewelryComparator = new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                if (o1.mass == o2.mass) { //무게가 같으면
                    return o2.price - o1.price; //가격 오름차순
                }
                return o1.mass - o2.mass; //무게 내림차순
            }
        };

        //무게를 오름차순 정렬하되 무게가 같을 때는 가격 내림차순
        Arrays.sort(jewelries, jewelryComparator);
        //System.out.println(Arrays.toString(jewelries));

        Integer[] bag = new Integer[k];
        for (int i = 0; i < k; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        // 가방 오름차순 정렬
        Arrays.sort(bag);

        // 우선순위 큐- 높은 숫자부터
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());//내림차순
        int idx = 0;
        for (int i = 0; i < bag.length; i++) { //가장 기준으로 탐색
            // 현재 가방의 무게보다 작거나 같은 보석을 모두 우선순위 큐에 넣음.
            while (idx < n) {
                if (jewelries[idx].mass <= bag[i]) {
                    q.add(jewelries[idx].price);
                    idx++;
                } else {
                    //이미 오른차순으로 정렬했기 때문에 이후로는 검사 x
                    break;
                }
            }

            // 우선순위 큐에 있는 요소를 하나 빼서 가방에 넣음.
            // 이 때, 우선순위 큐는 내림차순 정렬이 되어있으므로 가장 큰 값이 나옴
            if (!q.isEmpty()) {
                answer += q.poll();
            }
        }
        System.out.println(answer);

    }

}

class Jewelry {
    int mass; //무게
    int price;//가격

    Jewelry(int mass, int price) { //생성자
        this.mass = mass;
        this.price = price;
    }

    public String toString() {
        return "mass :" + mass + ", " + "price :" + price;
    }


}