package network;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 获取host与mac地址
 * @author : yangzq80@gmail.com
 * @date: 2019-08-02
 */
@Slf4j
public class HostAndMac {
    public String getServiceKey() {
        byte[] mac = null;
        String hostAddress = null;
        try {

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress() && (networkInterface.getDisplayName().equals("en0") || networkInterface.getDisplayName().equals("eth0"))) {
                        hostAddress = addr.getHostAddress();
                        mac = networkInterface.getHardwareAddress();
                        break;
                    }
                }
                if (mac != null && !StringUtils.isEmpty(hostAddress)) {
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (mac == null || StringUtils.isEmpty(hostAddress)) {
            return null;
        }
        // mac地址拼装成String
        StringBuilder macAddress = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                macAddress.append("-");
            }
            //mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            macAddress.append(s.length() == 1 ? 0 + s : s);
        }
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        return hostAddress + ":" + macAddress.toString().toUpperCase();
    }


    public static void main(String[] args) {
        log.info("{}",new HostAndMac().getServiceKey());
    }
}
