package response;

import java.util.List;

public class RespCustomerList {

    private List<RespCustomer> respCustomerList;

    private RespStatus status;

    public List<RespCustomer> getRespCustomerList() {
        return respCustomerList;
    }

    public void setRespCustomerList(List<RespCustomer> respCustomerList) {
        this.respCustomerList = respCustomerList;
    }

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RespCustomerList{" +
                "respCustomerList=" + respCustomerList +
                ", status=" + status +
                '}';
    }
}
