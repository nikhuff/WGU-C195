package util;

import database.DBAppointment;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.view.JasperViewer;

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
            report.show(false);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    public static void contactSchedule() {

        JasperReportBuilder report = DynamicReports.report();

        Columns.column("Contact", "Contact", DataTypes.stringType());

        report
            .columns(
                Columns.column("Appointment ID", "Appointment_ID", DataTypes.stringType()),
                Columns.column("Title", "Title", DataTypes.stringType()),
                Columns.column("Type", "Type", DataTypes.stringType()),
                Columns.column("Description", "Description", DataTypes.stringType()),
                Columns.column("Start", "Start", DataTypes.stringType()),
                Columns.column("End", "End", DataTypes.stringType()),
                Columns.column("Customer ID", "Customer_ID", DataTypes.stringType()))
            .groupBy(Columns.column("Contact", "Contact_Name", DataTypes.stringType()))
            .title(
                Components.text("Contact Schedule (Times in UTC)")
                    .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
            .pageFooter(Components.pageXofY())
            .setDataSource(DBAppointment.getSchedule());

        try {
            report.show(false);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    public static void customersPerCountry() {

        JasperReportBuilder report = DynamicReports.report();

        report
            .columns(
                Columns.column("Country", "Country", DataTypes.stringType()),
                Columns.column("Number of Customers", "number_customers", DataTypes.stringType()))
            .title(
                Components.text("Number of Customers per Country")
                    .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
            .pageFooter(Components.pageXofY())
            .setDataSource(DBAppointment.customersPerCountry());

        try {
            report.show(false);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }
}
