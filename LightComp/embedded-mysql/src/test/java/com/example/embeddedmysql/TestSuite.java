package com.example.embeddedmysql;

import static com.wix.mysql.distribution.Version.v5_7_19;

import java.time.ZoneId;
import java.util.TimeZone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DatabaseTest.class
})
public class TestSuite {
    private static EmbeddedMysql embeddedMysql;

    @BeforeClass
    public static void _setupBeforeClass() {
        MysqldConfig config = MysqldConfig.aMysqldConfig(v5_7_19)
                .withPort(3307)
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")))
                .withUser("test", "test")
                .build();

        SchemaConfig schemaConfig = SchemaConfig.aSchemaConfig("test_database")
                .build();

        embeddedMysql = EmbeddedMysql.anEmbeddedMysql(config)
                // use the download config option to set a
                // custom location for the cached mysql executable
                //                .withDownloadConfig(DownloadConfig.aDownloadConfig()
                //                        .withCacheDir("./.embedmysql")
                //                        .build())
                .addSchema(schemaConfig)
                .start();
    }

    @AfterClass
    public static void _tearDownAfterClass() {
        if (null != embeddedMysql) {
            embeddedMysql.stop();
        }
    }
}