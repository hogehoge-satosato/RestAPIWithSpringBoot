package com.freelance.agent.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* クライアント用コントローラー.
 * <p>
 *     Reactクライアント用のルーティングコントローラー
 *     ソースはstaticフォルダ内
 * </p>
 */
@Controller
public class ForwardController {
    /** .
     * 転送用メソッド
     * パスにapiを含まない場合は、すべてindex.htmlへ転送
     *
     * @return 転送先
     */
    @GetMapping("/**/{path:^(?!api)[^.]*}")
    public String forward() {
        return "forward:/index.html";
    }

}
