package com.kilingzhang.dianvideo.Bean;

/**
 * Created by Slight on 2017/5/1 0001.
 */

public class RealVideoUrlApiBean {

    /**
     * msg : 200
     * ext : mp4
     * url : http://k.youku.com/player/getFlvPath/sid/0493598282208120d3d83/st/mp4/fileid/030020010058EEFB2723DC2EFD12734223422F-9703-B29D-76B3-A18DE9C5FD54?k=6d71665780a73034282c509a&hd=1&myp=0&ts=10881&ypremium=1&ymovie=1&ctype=12&ev=1&token=0544&oip=1919206819&ep=ciadGUyEXswI5yHfgD8bZ3jhIH8MXP4J9h%2BHgdJjALshQJ3K70zTwJ%2BzOoxAY49tcyABE%2B%2FzqdPj%0AaEJiYflEqWsQrUyhTfrn%2BILg5dxSzOR1b2ozBLnSsFSeRjT1&ccode=0501&duration=10881&expire=18000&psid=7c49174563097cdf1c2cd4645c8d8b2b&ups_client_netip=114.100.197.163&ups_ts=1493598282&ups_userid=1127101661&utid=71BNEHaFRnYCATzXz4YyU0S%2B&vid=XMjY1OTQyMTI3Mg%3D%3D&vkey=A847eea8ad631fa0ba28748964fd927d6
     */

    private String msg;
    private String ext;
    private String url;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
