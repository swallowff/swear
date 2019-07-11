package cn.swallowff.code.resolver;

import cn.swallowff.code.entity.ClassField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class ImportClassResolver {

    public ImportClassResolver() {
    }

    public List<String> resolve(List<ClassField> fields){
        List<String> list = new ArrayList<>();
        Set<Class> importedClass = new HashSet<>();
        for (ClassField field : fields){
            if (!importedClass.contains(field.getFieldType())){
                list.add("import "+field.getFieldType().getName() + ";");
                importedClass.add(field.getFieldType());
            }
        }
        return list;
    }
}
