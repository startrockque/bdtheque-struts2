package admin;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Fabien on 07/07/2015.
 */
public class EmpruntsActions extends ActionSupport {

    private int pageNumber;
    private int pageNumberM;
    private int pageNumberE;

    public String getAllEmprunts() throws Exception {
        return "success";
    }

    public String toAddEmprunts() throws Exception {
        return "success";
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumberM() {
        return pageNumberM;
    }

    public void setPageNumberM(int pageNumberM) {
        this.pageNumberM = pageNumberM;
    }

    public int getPageNumberE() {
        return pageNumberE;
    }

    public void setPageNumberE(int pageNumberE) {
        this.pageNumberE = pageNumberE;
    }
}