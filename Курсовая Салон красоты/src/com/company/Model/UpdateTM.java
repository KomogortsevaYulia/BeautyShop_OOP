package com.company.Model;

import com.company.View.*;

public class UpdateTM {
    public static void updateTM(){
        FrameRecord.TMRecord.update();
        FrameRecordDay.TMRecordDay.update();
        FrameClients.TMClients.update();
        FrameEmployee.TMEmployee.update();
        FramePerformedWork.TMWork.update();
        FrameServices.TMServices.update();
    }
}
