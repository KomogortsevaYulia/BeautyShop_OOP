package main.data.tableModel;

import main.userInterface.table.*;

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
