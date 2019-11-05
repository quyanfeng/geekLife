//package com.geek.life.interceptor;
//
//import com.hsjry.plutus.wallet.common.constant.WalletConstant;
//import com.hsjry.plutus.wallet.common.enums.PlutusExceptionCodeEnum;
//import com.hsjry.plutus.wallet.common.exception.PlutusWalletException;
//import com.hsjry.plutus.wallet.common.util.RandomUtil;
//import com.hsjry.plutus2.common.response.PlutusResponse;
//import com.hsjry.plutus2.utils.redis.RedisUtil;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.text.StrBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @Description:
// * @Author: 屈艳锋
// * @Date: 2019/6/10 16:07
// */
//@Service
//public class IdempotentTokenServiceImpl implements IdempotentTokenService {
//
//    private static final String TOKEN_NAME = "idempotentToken";
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    /**
//     * 申请幂等token
//     * @Author qyf
//     * @Date 2019/6/10 16:51
//     * @param
//     * @return com.hsjry.plutus2.common.response.PlutusResponse
//     **/
//    @Override
//    public PlutusResponse createIdempotentToken(String userToken) {
//        String token = RandomUtil.nextString(20);
//        StrBuilder key=new StrBuilder();
//        key.append(WalletConstant.Redis.IDEMPOTENT_TOKEN_PREFIX).append(userToken);
//        redisUtil.set(key.toString(), token, WalletConstant.Redis.EXPIRE_TIME_MINUTE);
//        return new PlutusResponse(token);
//    }
//
//
//    /**
//     * 校验幂等token
//     * @Author qyf
//     * @Date 2019/6/10 16:51
//     * @param request
//     * @return void
//     **/
//    @Override
//    public void checkIdempotentToken(HttpServletRequest request) {
//
//        String token = request.getHeader(TOKEN_NAME);
//        if (StringUtils.isBlank(token)) {// header中不存在token
//            token = request.getParameter(TOKEN_NAME);
//            if (StringUtils.isBlank(token)) {// parameter中也不存在token
//                throw new PlutusWalletException(PlutusExceptionCodeEnum.ILLEGAL_ARGUMENT);
//            }
//        }
//
//        if (!redisUtil.haskey(token)) {
//            throw new PlutusWalletException(PlutusExceptionCodeEnum.REPETITIVE_OPERATION);
//        }
//
//        redisUtil.delete(token);
//
//        //TODO 需要判断删除是否成功，否则依然存在并发问题
////        Long del = redisUtil.delete(token);
////        if (del <= 0) {
////            throw new PlutusWalletException(PlutusExceptionCodeEnum.REPETITIVE_OPERATION);
////        }
//    }
//
//}
