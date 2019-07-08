package JDK.JDK;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by order
 * 2019/4/1 20:57
 */
public class test {

    public static void main(String[] args) {
        Map<String, String> formatSizeLangs = new HashMap<>(0);
        formatSizeLangs.forEach((k, v) ->
                System.out.println(k + v));

        List<String> li = Lists.newArrayList();
        li.add("12");
        li.add("123");
        System.out.println(StringUtils.join(li,"|"));
     }
}
