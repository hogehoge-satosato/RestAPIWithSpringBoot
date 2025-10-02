package com.freelance.agent.restapi.service;

import java.util.List;

import com.freelance.agent.restapi.model.Product;

/** .
 * 商品情報に関するビジネスロジックを定義するサービスインターフェース。
 * <p>
 * 商品の取得・登録・更新・削除・存在確認など、アプリケーションの主要な操作を抽象化します。
 * 実装は {@code ProductServiceImpl} などで提供され、コントローラー層から呼び出されます。
 * </p>
 */
public interface ProductService {

    /** .
     * 全ての商品情報を取得します。
     *
     * @return 商品の一覧 {@link List<Product>}
     */
    List<Product> getAllProducts();

    /** .
     * 指定された ID に対応する商品情報を取得します。
     *
     * @param id 商品の識別子
     * @return 該当する {@link Product} オブジェクト。存在しない場合は {@code null}
     */
    Product getProduct(Long id);

    /** .
     * 新しい商品情報を登録します。
     *
     * @param product 登録対象の商品情報
     */
    void createProduct(Product product);

    /** .
     * 既存の商品情報を更新します。
     *
     * @param product 更新対象の商品情報（ID を含む必要があります）
     */
    void updateProduct(Product product);

    /** .
     * 指定された ID の商品情報を削除します。
     *
     * @param id 削除対象の商品 ID
     */
    void deleteProduct(Long id);

    /** .
     * 指定された ID の商品が存在するかを判定します。
     *
     * @param id 存在確認対象の商品 ID
     * @return 商品が存在する場合は {@code true}、存在しない場合は {@code false}
     */
    boolean exists(Long id);
}
