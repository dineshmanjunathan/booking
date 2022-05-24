package com.ba.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ba.app.vo.BookingVo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LuggageSlipGenerator {

	final static String templateName = "templates/luggage_slip.jrxml";
	
	private static final LuggageSlipGenerator instance = new LuggageSlipGenerator();
	
	public static LuggageSlipGenerator getInstance() {
		return instance;
	}
	
	private JasperPrint getJasperContext(Map<String, Object> reportParams,String templateName) throws Exception {
        JasperReport jasperReport = JasperCompileManager.compileReport(getFile(templateName));
        JRDataSource dataSource = new JREmptyDataSource();
        return JasperFillManager.fillReport(jasperReport, reportParams, dataSource);
    }
	
	public  byte[] getReportDataSource(BookingVo bookingVo) throws Exception {
		Map<String, Object> reportParams = setParams(bookingVo);
		JasperPrint jasperPrint = getJasperContext(reportParams,templateName);
        byte[] reportArray = JasperExportManager.exportReportToPdf(jasperPrint);
        return reportArray;
    }
	
	public String getMIMEType() {
        return "application/pdf";
    }
	
	protected Map<String, Object> setParams(BookingVo bookingVo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FROM_LOC", StringUtils.trimToEmpty(bookingVo.getFromLocation()));
		params.put("TO_LOC", StringUtils.trimToEmpty(bookingVo.getToLocation()));
		params.put("CONSIGNOR", StringUtils.trimToEmpty(bookingVo.getFromName()));
		params.put("CONSIGNEE", StringUtils.trimToEmpty(bookingVo.getToName()));
		params.put("LR_NO", StringUtils.trimToEmpty(bookingVo.getLrNumber()));
		params.put("DATE", StringUtils.trimToEmpty(bookingVo.getBookedOn()));
		params.put("TIME",DateUtil.localDateTimeToTimeString(bookingVo.getCreateon()));
		params.put("NO_OF_ARTICLES", (bookingVo.getItem_count()));
		params.put("WEIGHT", (bookingVo.getWeight()));
		params.put("VALUE", (bookingVo.getFromValue()));
		params.put("REMARK", StringUtils.trimToEmpty(bookingVo.getRemarks()));
		params.put("FREIGHT", (bookingVo.getFreightvalue()));
		params.put("LOADING_CHARGES", bookingVo.getLoadingcharges());
		params.put("PAYMENT_MODE", StringUtils.trimToEmpty(bookingVo.getPayOption()));
		params.put("CASH_PAID", bookingVo.getTotal());
		params.put("RUPEES_TEXT", NumberToWordsConverter.getInstance().convert(bookingVo.getTotal().intValue()));
		
		params.put("DELIVERY_OFFICE_ADDRESS", StringUtils.trimToEmpty(bookingVo.getBillDesc()));
		
		
		return params;
	}
	
	private String getFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file.getAbsolutePath();
	  }
	
}
