/*package com.ba.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.util.ByteArrayDataSource;

import com.ba.app.entity.Member;
import com.ba.app.entity.Purchase;
import com.ba.app.vo.PurchaseReportVo;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
;

public class ReportGenerator {
	
	
	public JasperPrint getJasperContext(Map<String, Object> reportParams,String templateName) throws Exception {
        JasperReport jasperReport = JasperCompileManager.compileReport(getFile(templateName));
        String detailsParameterName = "DETAILS";
        JRDataSource dataSource;
        if (reportParams.containsKey(detailsParameterName)) {
            @SuppressWarnings("unchecked")
            List<Object> detailObjects = (List<Object>) reportParams.get(detailsParameterName);
            if (detailObjects.size() > 0) {
                dataSource = new JRBeanCollectionDataSource(detailObjects);
            } else {
                dataSource = new JREmptyDataSource();
            }
        } else {
            dataSource = new JREmptyDataSource();
        }
        return JasperFillManager.fillReport(jasperReport, reportParams, dataSource);
    }
	
	public ByteArrayDataSource getReportDataSource(Map<String, Object> reportParams,String templateName) throws Exception {
        JasperPrint jasperPrint = getJasperContext(reportParams,templateName);
        byte[] reportArray = JasperExportManager.exportReportToPdf(jasperPrint);
        ByteArrayDataSource dataSource = new ByteArrayDataSource(reportArray, getMIMEType());
        dataSource.setName("register");
        return dataSource;
    }
	
	public String getMIMEType() {
        return "application/pdf";
    }
	
	public Map<String, Object> getReportData(Member user) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("name", user.getName());
		hm.put("dob", user.getDob());
		hm.put("gender", user.getGender());
		hm.put("email",user.getEmail());
		hm.put("mobile", user.getPhonenumber());
        return hm;
    }
	
	public Map<String, Object> getPurchaseReportData(List<Purchase> purchaseList) {
		Map<String, Object> hm = new HashMap<String, Object>();
		List<PurchaseReportVo> list = new ArrayList<>();
		for(Purchase purchase : purchaseList) {
			if(hm.get("total") != null) {
				hm.put("total", (double)hm.get("total")+ purchase.getAmount()*purchase.getQuantity());
			} else {
				hm.put("total", purchase.getAmount()*purchase.getQuantity());
			}
			hm.put("ordernumber", purchase.getOrderNumber());
			hm.put("purchasedon",purchase.getPurchasedOn());
			hm.put("memberid", purchase.getMemberid());
			PurchaseReportVo vo = new PurchaseReportVo();
			vo.setCategory(purchase.getProduct().getCategory().getDescription()+" ["+purchase.getProduct().getCategory().getCode()+"]");
			vo.setProductDesc(purchase.getProduct().getProdDesc()+" ["+purchase.getProduct().getCode()+"]");
			vo.setQuantity(purchase.getQuantity());
			vo.setPrice(purchase.getAmount());
			list.add(vo);
		}
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
		hm.put("DETAILS", jrBeanCollectionDataSource);
        return hm;
    }
	
	public static void main(String[] args) {

		try {
			System.out.println("Start ....");
			ReportGenerator rp=new ReportGenerator();
			String pdfFileName = "/home/dinesh/workspace/local/jreport/report1.pdf";
			JasperReport jasperReport = JasperCompileManager.compileReport(rp.getFile("templates/report3.jrxml"));
			JRDataSource jrDataSource = new JREmptyDataSource();
			// net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

			// Create arguments
			Map<String, Object> hm = new HashMap<String, Object>();
			hm.put("INVOICE_NUMBER", "1000011");
			hm.put("PAYMENT_TERMS", "PPD");
			hm.put("SHIP_DATE", "20-10-2019");
			hm.put("SHIP_VIA", "UPS");
			hm.put("ORDER_NUMBER", "1000011");
			hm.put("PURCHASE_ORDER", "1000011");
			hm.put("SALES_PERSON", "<html><body><h1>My First Heading <br> Dinesh <br>Test</h1><p>My first paragraph.</p></body></html>");
			hm.put("SHIP_TERMS", "001");
			hm.put("CUSTOMER", "1000011");
			hm.put("ORDER_DATE", "20-10-2019");
			hm.put("SUBTOTAL", "$10.00");
			hm.put("SHIPPING", "$10.00");
			hm.put("TAX", "$10.00");
			hm.put("TOTAL", "$10.00");
			//hm.put("IMAGE",rp.getFile("templates/images/vibeszn_logo.png" ));
			//JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
			//hm.put("DETAILS", jrBeanCollectionDataSource);
			
			// Generate jasper print
			JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperReport, hm, jrDataSource);

			// Export pdf file
			JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);

			byte[] res = JasperExportManager.exportReportToPdf(jprint);

			System.out.println("Done exporting reports to pdf");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file.getAbsolutePath();
	  }
	
	
}*/
