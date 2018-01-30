package com.cdkj.coin.wallet.dto.res;

public class XN805951Res {
    // 上传凭证
    private String uploadToken;

    public XN805951Res() {
    }

    public XN805951Res(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }
}
