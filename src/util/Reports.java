package util;

import database.DBAppointment;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class Reports {

    public static void totalAppointmentsByTypeAndMonth() {

        JasperReportBuilder report = DynamicReports.report();

        report
            .columns(
                Columns.column("Type/Month", "type", DataTypes.stringType()),
                Columns.column("Total", "total", DataTypes.stringType()))
            .title(
                Components.text("Total Appointments By Type and Month")
                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
                .pageFooter(Components.pageXofY())
                .setDataSource(DBAppointment.getAppointmentsByTypeAndMonth());

        try {
            report.show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }
}
