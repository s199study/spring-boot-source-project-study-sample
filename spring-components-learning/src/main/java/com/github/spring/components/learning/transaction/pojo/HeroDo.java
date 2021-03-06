package com.github.spring.components.learning.transaction.pojo;

import com.github.spring.components.learning.mybatis.annoation.Table;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hero")
public class HeroDo {
    private Integer id;

    private String name;

    private String type;

    private Integer money;

    private String createTime;
}