package hello.core;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor // 생성자 자동생성
@AllArgsConstructor
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {

    }
}
