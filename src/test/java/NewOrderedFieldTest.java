import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NewOrderedFieldTest {
    private String inputText;
    private long expectedId;
    public NewOrderedFieldTest(String inputText, long expectedId){
        this.inputText=inputText;
        this.expectedId=expectedId;
    }
    @Parameterized.Parameters
    public static Collection configure(){
        return Arrays.asList(new Object[][]{
                {"{\"id\":1001}",1001},
                {"{\"id\":1234}",1234},
                {"{\"id\":1467}",1467},
                {"{\"id\":1}",1}
        });
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
