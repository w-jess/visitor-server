package com.cf.visitor.api.controller;

import com.cf.support.authertication.UserAuthentication;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whx
 * @date 2022/11/22
 */
@UserAuthentication
@RestController
@RequestMapping("/reserve")
@Api(tags = "小程序预约相关")
public class ReserveController {
}
