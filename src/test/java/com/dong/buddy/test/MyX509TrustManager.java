package com.dong.buddy.test;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * [简要描述]:<br/>
 * [详细描述]:信任管理器
 *
 * @author yqzhang
 * @version 1.0, 2018年2月6日
 * @since 项目名称
 * @table
 */
public class MyX509TrustManager implements X509TrustManager
{

    @Override
    public void checkClientTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkServerTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public X509Certificate[] getAcceptedIssuers()
    {
        // TODO Auto-generated method stub
        return null;
    }

}