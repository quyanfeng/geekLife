//package com.geek.life.interceptor;
//
//import com.hsjry.plutus2.common.response.PlutusResponse;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @Description: 幂等性校验
// * @Author: 屈艳锋
// * @Date: 2019/6/10 16:07
// */
//public interface IdempotentTokenService {
//    /**
//     * 申请幂等token
//     * @Author qyf
//     * @Date 2019/6/10 16:51
//     * @param
//     * @return com.hsjry.plutus2.common.response.PlutusResponse
//     **/
//    PlutusResponse createIdempotentToken(String userToken);
//
//    /**
//     * 校验幂等token
//     * @Author qyf
//     * @Date 2019/6/10 16:51
//     * @param request
//     * @return void
//     **/
//    void checkIdempotentToken(HttpServletRequest request);
//}
