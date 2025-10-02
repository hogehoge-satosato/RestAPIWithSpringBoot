package com.freelance.agent.restapi.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

/** .
 * 商品情報を表すドメインモデル。
 * <p>
 * 商品の識別子、名称、価格、在庫数、説明などを保持し、
 * バリデーション制約により不正なデータ入力を防止します。
 * </p>
 */
@Data
@NoArgsConstructor
public class Product {

    /** 商品の一意な識別子。DBの主キーなどに使用されます。. */
    private Long id;

    /** .
     * 商品名。
     * <p>
     * 空文字や未入力を許容せず、{@code NotBlank} によってバリデーションされます。
     * メッセージは {@code ValidationMessages.properties} で定義されたキーにより取得されます。
     * </p>
     */
    @NotBlank(message = "{NotBlank.productRequest.name}")
    private String name;

    /** .
     * 商品の価格。
     * <p>
     * {@code 1.0} 以上である必要があり、{@code DecimalMin} によって制約されます。
     * 金額は {@link BigDecimal} で保持され、精度の高い計算が可能です。
     * </p>
     */
    @DecimalMin(value = "1.0", message = "{Min.productRequest.price}")
    private BigDecimal price;

    /** .
     * 商品の在庫数。
     * <p>
     * {@code 0} 以上である必要があり、{@code Min} によって制約されます。
     * 負の値は許容されません。
     * </p>
     */
    @Min(value = 0, message = "{Min.productRequest.stock}")
    private int stock;

    /** .
     * 商品の説明文。
     * <p>
     * 任意項目であり、空でも構いません。
     * 商品の特徴や補足情報などを記述するために使用されます。
     * </p>
     */
    private String description;
}
