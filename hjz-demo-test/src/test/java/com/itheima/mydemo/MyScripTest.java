package com.itheima.mydemo;

import cn.hutool.script.ScriptUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * @Description js模板引擎计算公式测试
 * @Author huojz
 * @project huojz
 * @create 2024 01 29 15:42
 */
@SpringBootTest
public class MyScripTest {
    @Test
    public void testJsScript() {
        Bindings map = new SimpleBindings();
        map.put("钢管指标f1",100);
        map.put("木方指标f2",150);
        map.put("模板指标f3",300);
        String s1 = "钢管指标f1*木方指标f2+模板指标f3";
        CompiledScript compile1 = ScriptUtil.compile("print('Script test!');");
        CompiledScript compile2 = ScriptUtil.compile(s1);
        try {
            Object eval = compile2.eval(map);
            System.out.println("eval = " + eval);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello JVAV!");
    }
}
