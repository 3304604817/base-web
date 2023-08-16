package com.base.basic.domain.vo.v0;

public class ScriptBodyVO {
    /**
     * 地址
     */
    private String address;
    /**
     * 端口
     */
    private int port;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 要执行的脚本信息
     */
    private ScriptParamVO script;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ScriptParamVO getScript() {
        return script;
    }

    public void setScript(ScriptParamVO script) {
        this.script = script;
    }
}
