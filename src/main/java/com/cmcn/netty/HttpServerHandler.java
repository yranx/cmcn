package com.cmcn.netty;

import com.cmcn.constant.AppConst;
import com.cmcn.util.Responder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.InvalidParameterException;

/**
 * 自定义处理器
 * @author ranx
 * @create 2018-11-08 20:42
 **/
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>{
    private static Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        logger.info("请求URL:{} IP地址：{}", new Object[]{request.getUri(), getRemoteIpAddr(ctx)});
        //只接受post请求
        if (request.getMethod() != HttpMethod.POST) {
            logger.info("非POST方法不支持");
            new Responder().sendWithStatus(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED, "方法不可用");
            ctx.close();
            return;
        }

        JSONObject param = null;
        try {
            String paramStr = request.content().toString(Charset.forName("UTF-8"));
            logger.info("请求URL: {}请求参数：{}", new Object[]{request.getUri(), paramStr});
            if (StringUtils.isNotBlank(paramStr)) {
                param = JSONObject.fromObject(paramStr);
            } else {
                param = new JSONObject();
            }
        } catch (Exception e) {
            logger.error("[p] json解析出错{}", e);
            throw new InvalidParameterException();
        }

        //请求映射
//        ctx.writeAndFlush(response)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof  InvalidParameterException) {
            new Responder().sendWithStatus(ctx, HttpResponseStatus.NOT_ACCEPTABLE, "数据解析出错");
        } else {
            new Responder().sendWithStatus(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR, "内部错误");
        }
        logger.error("[p] InvalidParamException:{}", cause);
        ctx.close();
    }

    private String getRemoteIpAddr(ChannelHandlerContext ctx) {
        String ip = AppConst.DEFAULT_IP_ADDRESS;
        try {
            String ipAndPort = ctx.channel().remoteAddress().toString();
            ipAndPort = ipAndPort.substring(1);
            ipAndPort = ipAndPort.split("\\:")[0];
            ip = ipAndPort;
        } catch (Exception e) {
            ip = AppConst.DEFAULT_IP_ADDRESS;
        }
        return ip;
    }
}
