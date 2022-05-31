import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


public class NewJSONFieldTest {
    private String expectedResult;
    private Integer id;
    public NewJSONFieldTest(){
        configure("{\"id\":1001}",1001);
        //configure("{\"id\":1234}", 1234);
        //configure("{\"id\":1467}", 1467);
        //configure("{\"id\":1}", 1);
    }
    private void configure(String expectedResult, Integer id){
        this.expectedResult = expectedResult;
        this.id = id;
    }
    @Test
    public void test_jsonField() throws Exception {
        VO vo = new VO();

        vo.setId(this.id);
        vo.setName("abb");

        String text = JSON.toJSONString(vo);
        Assert.assertEquals(this.expectedResult, text);
    }

    public static class VO {
        private int id;

        @JSONField(serialize=false)
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
