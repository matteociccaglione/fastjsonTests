import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


public class NewOrderedFieldTest {
    private String inputText;
    private Object expectedId;
    public NewOrderedFieldTest(){
        configure("{\"id\":1001}",1001);
        //configure("",1234);
        //configure(null,1234);
        //configure("{\"id\":"ciao"}","ciao");
    }
    private void configure(String inputText, Object expectedId){
        this.inputText=inputText;
        this.expectedId=expectedId;
    }

    @Test
    public void test_ordered_field() throws Exception {
        String text = inputText;
        Model model = JSON.parseObject(text, Model.class, Feature.OrderedField);
        Assert.assertEquals(expectedId, model.getId());
        String text2 = JSON.toJSONString(model);
        Assert.assertEquals(text, text2);
    }

    public static interface Model {
        public int getId();
        public void setId(int value);
    }
}
