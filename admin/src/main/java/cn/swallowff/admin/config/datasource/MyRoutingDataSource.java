package cn.swallowff.admin.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author shenyu
 * @create 2019/4/8
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String type = DataSourceContextHolder.getDataSourceType();

        if (type.equals(DataSourceType.WRITE.getType())) {
            return DataSourceType.WRITE.getType();
        } else {
            return DataSourceType.READ.getType();
        }
//        // 读简单负载均衡
//        int number = count.getAndAdd(1);
//        int lookupKey = number % dataSourceNumber;
//        return new Integer(lookupKey);
    }


}
