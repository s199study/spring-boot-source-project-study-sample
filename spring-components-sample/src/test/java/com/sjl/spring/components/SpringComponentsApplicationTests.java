package com.sjl.spring.components;

import com.alibaba.fastjson.JSONObject;
import com.sjl.spring.components.transaction.dao.JdGoodsMapper;
import com.sjl.spring.components.transaction.pojo.Hero;
import com.sjl.spring.components.transaction.pojo.Team;
import com.sjl.spring.components.transaction.service.CommonOperateService;
import com.sjl.spring.components.transaction.service.JdGoodsService;
import com.sjl.spring.components.transaction.pojo.JdGoods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
class SpringComponentsApplicationTests {


    @Autowired
    private JdGoodsService jdGoodsService;
    @Resource
    private JdGoodsMapper jdGoodsMapper;
    @Autowired
    private CommonOperateService<Team, Hero> commonOperateService;

    @Test
    void contextLoads() {
        JdGoods jdGoods = JdGoods.builder().operate("insert").rate("12").shopName("森马").thumbnail("http://www.baidu.com").title("测试").build();
        int insert = jdGoodsService.insert(jdGoods);
        System.out.println("====>>" + insert);
    }

    @Test
    void queryTest() {
        JdGoods jdGoods = jdGoodsMapper.selectByPrimaryKey(2);
        System.out.println("===>" + JSONObject.toJSONString(jdGoods));

    }

    @Test
    void commonServiceTest() {
        commonOperateService.operation(getTeam(), getHero());
    }

    private Hero getHero() {
        return Hero.builder().createTime(LocalDateTime.now())
                .money(1000).name("安琪拉").type("法师").build();

    }

    private Team getTeam() {
        return Team.builder().createTime(LocalDateTime.now())
                .teamName("狼人杀").updateTime(LocalDateTime.now())
                .build();
    }


}
