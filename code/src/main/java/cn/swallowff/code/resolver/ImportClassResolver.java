package cn.swallowff.code.resolver;

import cn.swallowff.code.entity.ClassField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class ImportClassResolver {

    public ImportClassResolver() {
    }

    public List<String> resolve(List<ClassField> fields){
        List<String> list = new ArrayList<>();
        for (ClassField field : fields){
            list.add("import "+field.getFieldType().getName() + ";");
        }
        return list;
    }
}
