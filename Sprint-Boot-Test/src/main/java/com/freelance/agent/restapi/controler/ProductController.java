package com.freelance.agent.restapi.controler;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.agent.restapi.advice.ValidationExceptionHandler;
import com.freelance.agent.restapi.model.Product;
import com.freelance.agent.restapi.service.ProductService;

/** .
 * 商品情報を管理する REST API コントローラー。
 * <p>
 * 商品の取得・登録・更新・削除を提供し、バリデーションや存在チェックに応じたレスポンスを返します。
 * </p>
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    /** バリデーションエラーや存在しないリソースに関するログ出力用ロガー。 .*/
    private static final Logger LOG =
            LoggerFactory.getLogger(ValidationExceptionHandler.class);

    /** 商品情報の操作を担うサービス層。 .*/
    private final ProductService productService;

    /** メッセージの国際化対応を行うメッセージソース。 .*/
    private final MessageSource messageSource;

    /** .
     * コンストラクタインジェクションにより依存するサービスとメッセージソースを受け取ります。
     *
     * @param service 商品操作を担うサービス
     * @param source メッセージ取得用の {@link MessageSource}
     */
    public ProductController(final ProductService service,
            final MessageSource source) {
        this.productService = service;
        this.messageSource = source;
    }

    /** .
     * 全商品の一覧を取得します。
     *
     * @return 商品のリスト
     */
    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    /** .
     * 指定された ID の商品を取得します。
     *
     * @param id 商品の識別子
     * @return 該当する {@link Product} オブジェクト
     */
    @GetMapping("/{id}")
    public Product findOne(@PathVariable final Long id) {
        return productService.getProduct(id);
    }

    /** .
     * 新しい商品を登録します。
     * <p>
     * リクエストボディに含まれる {@link Product} オブジェクトに対してバリデーションを行います。
     * </p>
     *
     * @param product 登録対象の商品情報
     */
    @PostMapping
    public void create(@RequestBody @Valid final Product product) {
        productService.createProduct(product);
    }

    /** .
     * 指定された ID の商品を更新します。
     * <p>
     * 商品が存在しない場合は {@code 404 Not Found} を返し、メッセージはロケールに応じて取得されます。
     * </p>
     *
     * @param id 更新対象の商品 ID
     * @param product 更新内容を含む商品情報
     * @param locale クライアントのロケール情報
     * @return 更新結果に応じた {@link ResponseEntity}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable final Long id,
            @RequestBody @Valid final Product product,
            final Locale locale) {
        if (!productService.exists(id)) {
            String msg =
                    messageSource.getMessage(
                            "product.notfound.update", null, locale);
            LOG.warn("エラー: {}", msg);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("message", msg));
        }
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.noContent().build();
    }

    /** .
     * 指定された ID の商品を削除します。
     * <p>
     * 商品が存在しない場合は {@code 404 Not Found} を返し、メッセージはロケールに応じて取得されます。
     * </p>
     *
     * @param id 削除対象の商品 ID
     * @param locale クライアントのロケール情報
     * @return 削除結果に応じた {@link ResponseEntity}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable final Long id, final Locale locale) {
        if (!productService.exists(id)) {
            String msg =
                    messageSource.getMessage(
                            "product.notfound.delete", null, locale);
            LOG.warn("エラー: {}", msg);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("message", msg));
        }
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
