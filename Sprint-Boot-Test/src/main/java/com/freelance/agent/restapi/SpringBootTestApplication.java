package com.freelance.agent.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/** .
 * Spring Boot アプリケーションのエントリーポイント。
 * <p>
 * {@link SpringBootApplication} により、コンポーネントスキャン・自動構成・設定クラスの統合が有効化されます。
 * {@code main} メソッドから {@link SpringApplication#run(Class, String...)} を呼び出すことで、
 * アプリケーション全体が起動されます。
 * </p>
 */
@SpringBootApplication
public final class SpringBootTestApplication extends SpringBootServletInitializer {

    private SpringBootTestApplication() {  }
    /** .
     * アプリケーションの起動メソッド。
     * <p>
     * Spring Boot のランタイムを初期化し、組み込みサーバーを起動します。
     * </p>
     *
     * @param args コマンドライン引数
     */
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootTestApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	return builder.sources(SpringBootTestApplication.class);
    }
}
