package com.freelance.agent.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** .
 * Spring Boot アプリケーションのエントリーポイント。
 * <p>
 * {@link SpringBootApplication} により、コンポーネントスキャン・自動構成・設定クラスの統合が有効化されます。
 * {@code main} メソッドから {@link SpringApplication#run(Class, String...)} を呼び出すことで、
 * アプリケーション全体が起動されます。
 * </p>
 */
@SpringBootApplication(exclude = {
        // 自動で読み込んでしまうので抑止
        org.springframework.boot.autoconfigure.security.servlet.
        UserDetailsServiceAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.
        SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.reactive.
        ReactiveSecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.reactive.
        ReactiveUserDetailsServiceAutoConfiguration.class
    })
public final class SprintBootTestApplication {

    private SprintBootTestApplication() {  }
    /** .
     * アプリケーションの起動メソッド。
     * <p>
     * Spring Boot のランタイムを初期化し、組み込みサーバーを起動します。
     * </p>
     *
     * @param args コマンドライン引数
     */
    public static void main(final String[] args) {
        SpringApplication.run(SprintBootTestApplication.class, args);
    }
}
