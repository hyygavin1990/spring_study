package cn.datawin;

import io.xjar.XKit;
import io.xjar.boot.XBoot;
import io.xjar.jar.XJarAntEntryFilter;
import io.xjar.key.XKey;

public class XJarTest {

    public static void main(String[] args) throws Exception {
        String password = "5tgb^YHN&UJM8ik,";
        XKey xKey = XKit.key(password);

        String managerSource = "D:\\workspace\\ai\\cloud_manager\\target\\ROOT.jar";
        String managerTarget = "C:\\Users\\hyy83\\Desktop\\manager.jar";
        XBoot.encrypt(managerSource, managerTarget, xKey, new XJarAntEntryFilter("cn/datawin/**"));

//        String ctiSource = "D:/java/IdeaProjects/cloud_cti_sale/target/ROOT.jar";
//        String ctiTarget = "C:/Users/zhouyi/Desktop/cti.jar";
//        XBoot.encrypt(ctiSource, ctiTarget, xKey, new XJarAntEntryFilter("cn/datawin/**"));

//        String taskSource = "D:/java/IdeaProjects/cloud_task_sale/target/ROOT.jar";
//        String taskTarget = "C:/Users/zhouyi/Desktop/task.jar";
//        XBoot.encrypt(taskSource, taskTarget, xKey, new XJarAntEntryFilter("cn/datawin/**"));

//        String staffSource = "D:/java/IdeaProjects/cloud_staff_sale/target/ROOT.jar";
//        String staffTarget = "C:/Users/zhouyi/Desktop/staff.jar";
//        XBoot.encrypt(staffSource, staffTarget, xKey, new XJarAntEntryFilter("cn/datawin/**"));
    }

}
