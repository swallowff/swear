package cn.swallowff.code;

/**
 * @author shenyu
 * @create 2019/7/10
 */
@Deprecated
public enum  FileType {
    ENTITY(0,"实体类","",".java","entity","entity.btl"),
    DAO(1,"持久层","Dao",".java","dao","dao.btl"),
    SERVICE(2,"service层","Service",".java","service","service.btl"),
    CONTROLLER(3,"控制层","Controller",".java","controller","controller.btl"),
    MAPPER(4,"Mybatis映射文件","Mapper",".xml","","mapper.btl"),
    LIST_HTML(5,"列表页面","-list",".html","","list-html.btl"),
    ADD_HTML(6,"添加页面","-add",".html","","add-html.btl"),
    EDIT_HTML(6,"修改页面","-edit",".html","","edit-html.btl"),
    LIST_JS(7,"列表页JS","-list",".js","","list-js.btl"),
    ADD_JS(8,"添加页JS","-add",".js","","add-js.btl"),
    EDIT_JS(9,"修改页JS","-edit",".js","","edit-js.btl"),

    ;
    private Integer code;
    private String name;
    private String nameSuffix;
    private String suffix;
    private String packageName;
    private String templateName;

    FileType(Integer code, String name, String nameSuffix, String suffix, String packageName,String templateName) {
        this.code = code;
        this.name = name;
        this.nameSuffix = nameSuffix;
        this.suffix = suffix;
        this.packageName = packageName;
        this.templateName = templateName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
