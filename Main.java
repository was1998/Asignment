package com.cg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Main {

	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\MOHAKHTA\\OneDrive - Capgemini\\Desktop\\All Files");
		File files[] = f.listFiles();
		int count=0;
		
		System.out.println("-------------------------------");
		System.out.println("    Total number of files:: " + files.length);
		System.out.println("-------------------------------");
		System.out.println("    please enter the keyword   ");
		System.out.println("-------------------------------");
		Scanner sc = new Scanner(System.in);
		int numOfKeys = sc.nextInt();
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < numOfKeys; i++) {
			String strting = sc.nextLine();
			set.add(strting);
		}
		System.out.println("-------------------------------");
		System.out.println("         Showing Results       ");
		System.out.println("-------------------------------");
		for (File file : files) {
			if (file.getName().contains("docx")) {
				FileInputStream input = new FileInputStream(file);
				XWPFDocument document = new XWPFDocument(input);
				XWPFWordExtractor ex = new XWPFWordExtractor(document);
				String str = ex.getText();
                for(String word:set) { 
				if (str.contains(word)) {
					count++;
				}}
                if(count>0) {
                	System.out.println("=>" + file.getName() + " " + file.getAbsolutePath());
                	percentage(count,numOfKeys);
                }

			} else if (file.getName().contains("pdf")) {

				PDDocument document = PDDocument.load(file);
				PDFTextStripper test = new PDFTextStripper();
				String str = test.getText(document);
			    for(String word:set) { 
					if (str.contains(word)) {
						count++;
					}}
			    if(count>0) {
                	System.out.println("=>" + file.getName() + " " + file.getAbsolutePath());
                	percentage(count,numOfKeys);
                }
				document.close();

			}

		}

	}

	private static void percentage(int count,int num) {
		if (count == 0) {
			System.out.println("0%");
			} else {

			System.out.println("matched keywords count is " + count);
			// System.out.println(file.getName());
			double diff = num - count;
			double per = (diff / num) * 100;
			double matched = 100 - per;
			System.out.println(String.format("%.2f", matched));


		
			}
	}

}
