package com.example.recommend.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Configuration
public class MahoutConfig {
    private MysqlDataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("127.0.0.1");
        dataSource.setUser("root");
        dataSource.setPassword("zhangning");
        dataSource.setDatabaseName("recommend");
        return dataSource;
    }

    //一个是基于MySQL数据库的DataMode，
    @Bean(autowire = Autowire.BY_NAME, value = "mysqlDataModel")
    public DataModel getMySQLDataModel() {
        DataModel dataModel = new MySQLJDBCDataModel(getDataSource(),"rating","user_id","spot_id","rating","rate_time");
        return dataModel;

    }


    //一个是基于文件的DataMode
    @Bean(autowire = Autowire.BY_NAME,value = "fileDataModel")
    public DataModel getDataModel() throws IOException {
        URL url = MahoutConfig.class.getClassLoader().getResource("mahout/ratings.data");
        DataModel dataModel = new FileDataModel(new File(url.getFile()));
        return dataModel;
    }

}
