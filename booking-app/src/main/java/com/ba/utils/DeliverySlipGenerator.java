package com.ba.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ba.app.vo.DeliveryVo;

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
public class DeliverySlipGenerator {

final static String templateName = "templates/delivery_slip.jrxml";
	
	private static final DeliverySlipGenerator instance = new DeliverySlipGenerator();
	
	public static DeliverySlipGenerator getInstance() {
		return instance;
	}
	
	private JasperPrint getJasperContext(Map<String, Object> reportParams,String templateName) throws Exception {
        JasperReport jasperReport = JasperCompileManager.compileReport(getFile(templateName));
        JRDataSource dataSource = new JREmptyDataSource();
        return JasperFillManager.fillReport(jasperReport, reportParams, dataSource);
    }
	
	public  byte[] getReportDataSource(DeliveryVo deliveryVO) throws Exception {
		Map<String, Object> reportParams = setParams(deliveryVO);
		JasperPrint jasperPrint = getJasperContext(reportParams,templateName);
        byte[] reportArray = JasperExportManager.exportReportToPdf(jasperPrint);
        return reportArray;
    }
	
	public String getMIMEType() {
        return "application/pdf";
    }
	
	protected Map<String, Object> setParams(DeliveryVo deliveryVO) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FROM_LOC", StringUtils.trimToEmpty(deliveryVO.getFromLocation()));
		params.put("TO_LOC", StringUtils.trimToEmpty(deliveryVO.getToLocation()));
		params.put("CONSIGNOR", StringUtils.trimToEmpty(deliveryVO.getFromName()));
		params.put("CONSIGNEE", StringUtils.trimToEmpty(deliveryVO.getToName()));
		
		params.put("LR_NO", StringUtils.trimToEmpty(deliveryVO.getLRNo()));
		params.put("DATE", StringUtils.trimToEmpty(deliveryVO.getDeliveryDate()));
		params.put("TIME", "");
		params.put("NO_OF_ARTICLES", (deliveryVO.getNoOfItems()));
		
		params.put("TO_PAY", deliveryVO.getToPay());
		params.put("UNLOADING_CHARGES", deliveryVO.getUnloadingCharges());
		params.put("DEMURRAGE", deliveryVO.getDemurrage());
		params.put("TOTAL", deliveryVO.getTotal());
		
		params.put("RUPEES_TEXT", NumberToWordsConverter.getInstance().convert(Integer.parseInt(deliveryVO.getTotal())));
		
		
		return params;
	}
	
	private String getFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file.getAbsolutePath();
	  }
}
