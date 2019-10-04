package com.applicate.service.contentgenerator.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applicate.service.contentgenerator.ContentGenerator;
import com.applicate.utils.VelocityManager;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

@Service
public class PDFGenerator implements ContentGenerator {

	@Autowired
	private VelocityManager velocityManager;

	@Override
	public InputStream generate(JSONObject payLoad) {
		InputStream inputStream = null;
		try {
			String lob = payLoad.getString("lob");
			String templateInfoKey =  payLoad.getString("templateInfoKey");
            JSONObject templateInfo  = velocityManager.getTemplateInfo(lob, templateInfoKey);
            String templateName = templateInfo.getString("templateName").split("\\.")[0];
            String templatePath = templateInfo.getString("templatePath");
			String body = velocityManager.getBody(payLoad.getJSONObject("data"),templateInfoKey,lob);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, os);

			document.open();
			HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

			// Pipelines
			PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
			HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);  
			XMLWorker worker = null;
			// CSS
			if(new File(templatePath+templateName+".css").exists()){
				CSSResolver cssResolver = new StyleAttrCSSResolver();
				CssFile cssFile = XMLWorkerHelper.getCSS( new FileInputStream(templatePath+templateName+".css"));
				cssResolver.addCss(cssFile);
				CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
				worker = new XMLWorker(css, true);
			}else{
				worker = new XMLWorker(html, true);
			}

			// XML Worker
			XMLParser p = new XMLParser(worker);
			p.parse(new StringReader(body));	
			document.close();
			inputStream = new ByteArrayInputStream(os.toByteArray());
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}

}
