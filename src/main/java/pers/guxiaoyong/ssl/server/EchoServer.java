package pers.guxiaoyong.ssl.server;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class EchoServer {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, CertificateException, KeyManagementException {
        // key store相关信息
        String keyName = "/Users/guxiaoyong/Documents/workspace/mySrvKeystore";
        char[] keyStorePwd = "123456".toCharArray();
        char[] keyPwd = "123456".toCharArray();
        // port 协议类型
        int port = 9999;
        String protocol = "TLSv1.2";
        // 装载当前目录下的key store. 可用jdk中的keytool工具生成keystore
        KeyStore ks = KeyStore.getInstance("JKS");
        InputStream ksIs = new FileInputStream(keyName);
        try {
            ks.load(ksIs, keyStorePwd);
        } finally {
            if (ksIs != null) {
                ksIs.close();
            }
        }
        // 初始化key manager factory
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, keyPwd);

        // 初始化ssl context
        SSLContext sSLContext = SSLContext.getInstance(protocol);
        sSLContext.init(kmf.getKeyManagers(), new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new SecureRandom());

        try {
            SSLServerSocketFactory sslserversocketfactory = sSLContext.getServerSocketFactory();
            
            SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(port);
            sslserversocket.setEnabledCipherSuites(sSLContext.getServerSocketFactory().getSupportedCipherSuites());
            sslserversocket.setEnabledProtocols(new String[] {"TLSv1", "TLSv1.1", "TLSv1.2", "SSLv3"});

            Socket client = sslserversocket.accept();
            System.out.println(client.getRemoteSocketAddress());
            //获取流
            InputStream inputstream = client.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                System.out.println(string);
                System.out.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}