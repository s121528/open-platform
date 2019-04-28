package cn.hacz.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/3 时间:9:37
 * @JDK 1.8
 * @Description 功能模块：
 */
@SpringBootApplication
@RestController
public class OpenNacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenNacosApplication.class, args);
    }

    /**
     * 功能描述：
     */
    @RequestMapping(value = "/info")
    public String test() {
        return "info";
    }
}
