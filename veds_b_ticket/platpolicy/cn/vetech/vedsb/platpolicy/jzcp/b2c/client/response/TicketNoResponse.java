package cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class TicketNoResponse {
    private int status;
    private String errorCode;
    private String errorMessage;
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
