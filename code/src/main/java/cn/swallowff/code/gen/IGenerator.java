package cn.swallowff.code.gen;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public interface IGenerator {

    void doGen();

    void genEntity();

    void genDao();

    void genService();

    void genController();

    void genXmlMapper();

    void genListPage();

    void genAddPage();

    void genEditPage();

    void genListJs();

    void genAddJs();

    void genEditJs();

}
