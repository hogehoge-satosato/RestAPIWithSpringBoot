package com.freelance.agent.restapi.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** .
 * バリデーション例外を処理するためのグローバル例外ハンドラー。
 * <p>
 * {@code @RestControllerAdvice} により、全てのコントローラーに対して
 * {@link MethodArgumentNotValidException} を捕捉し、整形されたエラーレスポンスを返します。
 * </p>
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    /** ロガー：バリデーションエラーの内容を警告レベルで記録します。 .*/
    private static final Logger LOG
        = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    /**.
     * {@link MethodArgumentNotValidException} を捕捉し、フィールドごとのエラーメッセージを整形して返します。
     *
     * @param ex バリデーション失敗時にスローされる例外
     * @return フィールド名と対応するエラーメッセージを含む {@link ResponseEntity}
     *         ステータスコードは {@code 400 Bad Request}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            final MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        LOG.warn("バリデーションエラー: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }
}

