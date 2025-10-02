package com.freelance.agent.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelance.agent.restapi.model.Product;
import com.freelance.agent.restapi.repository.ProductMapper;

/** .
 * {@link ProductService} の実装クラス。
 * <p>
 * 商品情報の取得・登録・更新・削除・存在確認などのビジネスロジックを提供し、
 * {@link ProductMapper} を通じてデータベース操作を行います。
 * コントローラー層から呼び出され、アプリケーションの中心的な処理を担います。
 * </p>
 */
@Service
public class ProductServiceImpl implements ProductService {

    /** 商品情報の永続化を担う MyBatis マッパー。 .*/
    private final ProductMapper productMapper;

    /** .
     * コンストラクタインジェクションにより {@link ProductMapper} を受け取ります。
     *
     * @param mapper 商品情報のデータアクセスを担うマッパー
     */
    public ProductServiceImpl(final ProductMapper mapper) {
        this.productMapper = mapper;
    }

    /** .
     * 全ての商品情報を取得します。
     *
     * @return 商品の一覧 {@link List<Product>}
     */
    @Override
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    /** .
     * 指定された ID に対応する商品情報を取得します。
     *
     * @param id 商品の識別子
     * @return 該当する {@link Product} オブジェクト。存在しない場合は {@code null}
     */
    @Override
    public Product getProduct(final Long id) {
        return productMapper.findById(id);
    }

    /** .
     * 新しい商品情報を登録します。
     *
     * @param product 登録対象の商品情報
     */
    @Override
    public void createProduct(final Product product) {
        productMapper.insert(product);
    }

    /** .
     * 既存の商品情報を更新します。
     *
     * @param product 更新対象の商品情報（ID を含む必要があります）
     */
    @Override
    public void updateProduct(final Product product) {
        productMapper.update(product);
    }

    /** .
     * 指定された ID の商品情報を削除します。
     *
     * @param id 削除対象の商品 ID
     */
    @Override
    public void deleteProduct(final Long id) {
        productMapper.delete(id);
    }

    /** .
     * 指定された ID の商品が存在するかを判定します。
     *
     * @param id 存在確認対象の商品 ID
     * @return 商品が存在する場合は {@code true}、存在しない場合は {@code false}
     */
    @Override
    public boolean exists(final Long id) {
        return productMapper.findById(id) != null;
    }
}
