package pers.guxiaoyong.ssl.client;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class EchoClient {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException {
        // key store相关信息
        String keyName = "/Users/guxiaoyong/Documents/workspace/mySrvKeystore";
        char[] keyStorePwd = "123456".toCharArray();
        char[] keyPwd = "123456".toCharArray();
        // port 协议类型
        String serverAddress = "192.168.0.104";
        int port = 9999;
        String protocol = "ssl";

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

        SSLSocketFactory sslsocketfactory = sSLContext.getSocketFactory();

        try {
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(serverAddress, port);
            sslsocket.setEnabledCipherSuites(sSLContext.getServerSocketFactory().getSupportedCipherSuites());
            sslsocket.setEnabledProtocols(new String[] {"TLSv1", "TLSv1.1", "TLSv1.2", "SSLv3"});
            System.out.println(sslsocket.getRemoteSocketAddress());
            InputStream inputstream = System.in;
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            OutputStream outputstream = sslsocket.getOutputStream();
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                bufferedwriter.write(string + '\n');
                bufferedwriter.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}