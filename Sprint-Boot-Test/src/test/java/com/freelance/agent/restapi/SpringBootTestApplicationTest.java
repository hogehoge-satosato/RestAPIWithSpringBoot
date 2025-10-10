package com.freelance.agent.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/** .
 * {@link SpringBootTestApplication} の起動確認テスト。
 * <p>
 * Spring Boot のアプリケーションコンテキストが正常にロードされるかを検証します。
 * </p>
 */
@SpringBootTest
class SpringBootTestApplicationTest {

    /** .
     * アプリケーションコンテキストが正常に起動することを確認します。
     */
    @Test
    void contextLoads() {
        // コンテキスト起動のみを確認（例外が出なければ成功）
    }
    /** .
     * {@link SpringBootTestApplication#main(String[])} を直接呼び出してカバレッジを補完します。
     */
    @Test
    void mainMethodShouldRun() {
        SpringBootTestApplication.main(new String[] {});
    }

}
