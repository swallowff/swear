package cn.swallowff.modules.core.config.properties;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

public class DruidProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Integer initialSize = 2;
    private Integer minIdle = 1;
    private Integer maxActive = 20;
    private Integer maxWait = 60000;
    private Integer timeBetweenEvictionRunsMillis = 60000;
    private Integer minEvictableIdleTimeMillis = 300000;
    private String validationQuery = "SELECT 'x'";
    private Boolean testWhileIdle = true;
    private Boolean testOnBorrow = false;
    private Boolean testOnReturn = false;
    private Boolean poolPreparedStatements = true;
    private Integer maxPoolPreparedStatementPerConnectionSize = 20;
    private String filters = "stat";

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMinIdle(this.minIdle);
        dataSource.setMaxActive(this.maxActive);
        dataSource.setMaxWait((long) this.maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis((long) this.timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis((long) this.minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(this.validationQuery);
        dataSource.setTestWhileIdle(this.testWhileIdle);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setTestOnReturn(this.testOnReturn);
        dataSource.setPoolPreparedStatements(this.poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);

        try {
            dataSource.setFilters(this.filters);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public DruidProperties() {
    }

    public String getUrl() {
        return this.url;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getDriverClassName() {
        return this.driverClassName;
    }

    public Integer getInitialSize() {
        return this.initialSize;
    }

    public Integer getMinIdle() {
        return this.minIdle;
    }

    public Integer getMaxActive() {
        return this.maxActive;
    }

    public Integer getMaxWait() {
        return this.maxWait;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return this.timeBetweenEvictionRunsMillis;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return this.minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return this.validationQuery;
    }

    public Boolean getTestWhileIdle() {
        return this.testWhileIdle;
    }

    public Boolean getTestOnBorrow() {
        return this.testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return this.testOnReturn;
    }

    public Boolean getPoolPreparedStatements() {
        return this.poolPreparedStatements;
    }

    public Integer getMaxPoolPreparedStatementPerConnectionSize() {
        return this.maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return this.filters;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setDriverClassName(final String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setInitialSize(final Integer initialSize) {
        this.initialSize = initialSize;
    }

    public void setMinIdle(final Integer minIdle) {
        this.minIdle = minIdle;
    }

    public void setMaxActive(final Integer maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxWait(final Integer maxWait) {
        this.maxWait = maxWait;
    }

    public void setTimeBetweenEvictionRunsMillis(final Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setMinEvictableIdleTimeMillis(final Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public void setValidationQuery(final String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public void setTestWhileIdle(final Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public void setTestOnBorrow(final Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public void setTestOnReturn(final Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public void setPoolPreparedStatements(final Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(final Integer maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public void setFilters(final String filters) {
        this.filters = filters;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DruidProperties)) {
            return false;
        } else {
            DruidProperties other = (DruidProperties) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label215:
                {
                    Object this$url = this.getUrl();
                    Object other$url = other.getUrl();
                    if (this$url == null) {
                        if (other$url == null) {
                            break label215;
                        }
                    } else if (this$url.equals(other$url)) {
                        break label215;
                    }

                    return false;
                }

                Object this$username = this.getUsername();
                Object other$username = other.getUsername();
                if (this$username == null) {
                    if (other$username != null) {
                        return false;
                    }
                } else if (!this$username.equals(other$username)) {
                    return false;
                }

                label201:
                {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            break label201;
                        }
                    } else if (this$password.equals(other$password)) {
                        break label201;
                    }

                    return false;
                }

                Object this$driverClassName = this.getDriverClassName();
                Object other$driverClassName = other.getDriverClassName();
                if (this$driverClassName == null) {
                    if (other$driverClassName != null) {
                        return false;
                    }
                } else if (!this$driverClassName.equals(other$driverClassName)) {
                    return false;
                }

                label187:
                {
                    Object this$initialSize = this.getInitialSize();
                    Object other$initialSize = other.getInitialSize();
                    if (this$initialSize == null) {
                        if (other$initialSize == null) {
                            break label187;
                        }
                    } else if (this$initialSize.equals(other$initialSize)) {
                        break label187;
                    }

                    return false;
                }

                Object this$minIdle = this.getMinIdle();
                Object other$minIdle = other.getMinIdle();
                if (this$minIdle == null) {
                    if (other$minIdle != null) {
                        return false;
                    }
                } else if (!this$minIdle.equals(other$minIdle)) {
                    return false;
                }

                label173:
                {
                    Object this$maxActive = this.getMaxActive();
                    Object other$maxActive = other.getMaxActive();
                    if (this$maxActive == null) {
                        if (other$maxActive == null) {
                            break label173;
                        }
                    } else if (this$maxActive.equals(other$maxActive)) {
                        break label173;
                    }

                    return false;
                }

                label166:
                {
                    Object this$maxWait = this.getMaxWait();
                    Object other$maxWait = other.getMaxWait();
                    if (this$maxWait == null) {
                        if (other$maxWait == null) {
                            break label166;
                        }
                    } else if (this$maxWait.equals(other$maxWait)) {
                        break label166;
                    }

                    return false;
                }

                Object this$timeBetweenEvictionRunsMillis = this.getTimeBetweenEvictionRunsMillis();
                Object other$timeBetweenEvictionRunsMillis = other.getTimeBetweenEvictionRunsMillis();
                if (this$timeBetweenEvictionRunsMillis == null) {
                    if (other$timeBetweenEvictionRunsMillis != null) {
                        return false;
                    }
                } else if (!this$timeBetweenEvictionRunsMillis.equals(other$timeBetweenEvictionRunsMillis)) {
                    return false;
                }

                label152:
                {
                    Object this$minEvictableIdleTimeMillis = this.getMinEvictableIdleTimeMillis();
                    Object other$minEvictableIdleTimeMillis = other.getMinEvictableIdleTimeMillis();
                    if (this$minEvictableIdleTimeMillis == null) {
                        if (other$minEvictableIdleTimeMillis == null) {
                            break label152;
                        }
                    } else if (this$minEvictableIdleTimeMillis.equals(other$minEvictableIdleTimeMillis)) {
                        break label152;
                    }

                    return false;
                }

                label145:
                {
                    Object this$validationQuery = this.getValidationQuery();
                    Object other$validationQuery = other.getValidationQuery();
                    if (this$validationQuery == null) {
                        if (other$validationQuery == null) {
                            break label145;
                        }
                    } else if (this$validationQuery.equals(other$validationQuery)) {
                        break label145;
                    }

                    return false;
                }

                Object this$testWhileIdle = this.getTestWhileIdle();
                Object other$testWhileIdle = other.getTestWhileIdle();
                if (this$testWhileIdle == null) {
                    if (other$testWhileIdle != null) {
                        return false;
                    }
                } else if (!this$testWhileIdle.equals(other$testWhileIdle)) {
                    return false;
                }

                Object this$testOnBorrow = this.getTestOnBorrow();
                Object other$testOnBorrow = other.getTestOnBorrow();
                if (this$testOnBorrow == null) {
                    if (other$testOnBorrow != null) {
                        return false;
                    }
                } else if (!this$testOnBorrow.equals(other$testOnBorrow)) {
                    return false;
                }

                label124:
                {
                    Object this$testOnReturn = this.getTestOnReturn();
                    Object other$testOnReturn = other.getTestOnReturn();
                    if (this$testOnReturn == null) {
                        if (other$testOnReturn == null) {
                            break label124;
                        }
                    } else if (this$testOnReturn.equals(other$testOnReturn)) {
                        break label124;
                    }

                    return false;
                }

                Object this$poolPreparedStatements = this.getPoolPreparedStatements();
                Object other$poolPreparedStatements = other.getPoolPreparedStatements();
                if (this$poolPreparedStatements == null) {
                    if (other$poolPreparedStatements != null) {
                        return false;
                    }
                } else if (!this$poolPreparedStatements.equals(other$poolPreparedStatements)) {
                    return false;
                }

                Object this$maxPoolPreparedStatementPerConnectionSize = this.getMaxPoolPreparedStatementPerConnectionSize();
                Object other$maxPoolPreparedStatementPerConnectionSize = other.getMaxPoolPreparedStatementPerConnectionSize();
                if (this$maxPoolPreparedStatementPerConnectionSize == null) {
                    if (other$maxPoolPreparedStatementPerConnectionSize != null) {
                        return false;
                    }
                } else if (!this$maxPoolPreparedStatementPerConnectionSize.equals(other$maxPoolPreparedStatementPerConnectionSize)) {
                    return false;
                }

                Object this$filters = this.getFilters();
                Object other$filters = other.getFilters();
                if (this$filters == null) {
                    if (other$filters != null) {
                        return false;
                    }
                } else if (!this$filters.equals(other$filters)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DruidProperties;
    }

    public int hashCode() {
        int result = 1;
        Object $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $driverClassName = this.getDriverClassName();
        result = result * 59 + ($driverClassName == null ? 43 : $driverClassName.hashCode());
        Object $initialSize = this.getInitialSize();
        result = result * 59 + ($initialSize == null ? 43 : $initialSize.hashCode());
        Object $minIdle = this.getMinIdle();
        result = result * 59 + ($minIdle == null ? 43 : $minIdle.hashCode());
        Object $maxActive = this.getMaxActive();
        result = result * 59 + ($maxActive == null ? 43 : $maxActive.hashCode());
        Object $maxWait = this.getMaxWait();
        result = result * 59 + ($maxWait == null ? 43 : $maxWait.hashCode());
        Object $timeBetweenEvictionRunsMillis = this.getTimeBetweenEvictionRunsMillis();
        result = result * 59 + ($timeBetweenEvictionRunsMillis == null ? 43 : $timeBetweenEvictionRunsMillis.hashCode());
        Object $minEvictableIdleTimeMillis = this.getMinEvictableIdleTimeMillis();
        result = result * 59 + ($minEvictableIdleTimeMillis == null ? 43 : $minEvictableIdleTimeMillis.hashCode());
        Object $validationQuery = this.getValidationQuery();
        result = result * 59 + ($validationQuery == null ? 43 : $validationQuery.hashCode());
        Object $testWhileIdle = this.getTestWhileIdle();
        result = result * 59 + ($testWhileIdle == null ? 43 : $testWhileIdle.hashCode());
        Object $testOnBorrow = this.getTestOnBorrow();
        result = result * 59 + ($testOnBorrow == null ? 43 : $testOnBorrow.hashCode());
        Object $testOnReturn = this.getTestOnReturn();
        result = result * 59 + ($testOnReturn == null ? 43 : $testOnReturn.hashCode());
        Object $poolPreparedStatements = this.getPoolPreparedStatements();
        result = result * 59 + ($poolPreparedStatements == null ? 43 : $poolPreparedStatements.hashCode());
        Object $maxPoolPreparedStatementPerConnectionSize = this.getMaxPoolPreparedStatementPerConnectionSize();
        result = result * 59 + ($maxPoolPreparedStatementPerConnectionSize == null ? 43 : $maxPoolPreparedStatementPerConnectionSize.hashCode());
        Object $filters = this.getFilters();
        result = result * 59 + ($filters == null ? 43 : $filters.hashCode());
        return result;
    }

    public String toString() {
        return "DruidProperties(url=" + this.getUrl() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", driverClassName=" + this.getDriverClassName() + ", initialSize=" + this.getInitialSize() + ", minIdle=" + this.getMinIdle() + ", maxActive=" + this.getMaxActive() + ", maxWait=" + this.getMaxWait() + ", timeBetweenEvictionRunsMillis=" + this.getTimeBetweenEvictionRunsMillis() + ", minEvictableIdleTimeMillis=" + this.getMinEvictableIdleTimeMillis() + ", validationQuery=" + this.getValidationQuery() + ", testWhileIdle=" + this.getTestWhileIdle() + ", testOnBorrow=" + this.getTestOnBorrow() + ", testOnReturn=" + this.getTestOnReturn() + ", poolPreparedStatements=" + this.getPoolPreparedStatements() + ", maxPoolPreparedStatementPerConnectionSize=" + this.getMaxPoolPreparedStatementPerConnectionSize() + ", filters=" + this.getFilters() + ")";
    }
}
