package com.freelance.agent.restapi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.freelance.agent.restapi.model.Product;

/** .
 * 商品情報に対するデータベース操作を定義する MyBatis マッパーインターフェース。
 * <p>
 * 商品の取得・登録・更新・削除に対応する SQL がマッピングされ、
 * {@code ProductMapper.xml} またはアノテーションベースで実装されます。
 * </p>
 */
@Mapper
public interface ProductMapper {

    /** .
     * 全ての商品情報を取得します。
     *
     * @return 商品の一覧 {@link List<Product>}
     */
    List<Product> findAll();

    /** .
     * 指定された ID に対応する商品情報を取得します。
     *
     * @param id 商品の識別子
     * @return 該当する {@link Product} オブジェクト。存在しない場合は {@code null}
     */
    Product findById(Long id);

    /** .
     * 新しい商品情報をデータベースに登録します。
     *
     * @param product 登録対象の商品情報
     */
    void insert(Product product);

    /** .
     * 既存の商品情報を更新します。
     *
     * @param product 更新対象の商品情報（ID を含む必要があります）
     */
    void update(Product product);

    /** .
     * 指定された ID の商品情報を削除します。
     *
     * @param id 削除対象の商品 ID
     */
    void delete(Long id);
}

