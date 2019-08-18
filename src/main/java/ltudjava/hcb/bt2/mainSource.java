package ltudjava.hcb.bt2;

import ltudjava.hcb.bt2.bus.*;
import ltudjava.hcb.bt2.dao.*;
import ltudjava.hcb.bt2.dto.*;

public class mainSource {
    public static void main(String[] args) {
        HelperBUS.openFileToGetContent(null);
        
        System.out.println("end!!!");
        HibernateUtil.getSessionFactory().close();
    }
}
