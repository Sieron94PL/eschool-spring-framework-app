package pl.dmcs.eschool.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;

public class PDFBuilder extends AbstractPdfView {

	@Autowired
	MarkService markService;

	@Autowired
	ClazzService clazzService;

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> map = (Map<String, Object>) model.get("modelMap");

		Student student = (Student) map.get("student");
		Map<Subject, List<Mark>> marks = (Map<Subject, List<Mark>>) map.get("certificate");
		List<Subject> subjects = (List<Subject>) map.get("subjects");

		document.add(new Paragraph("Imie ucznia " + student.getUser().getFirstname()));

		document.add(new Paragraph("Nazwisko ucznia " + student.getUser().getLastname()));

		document.add(new Paragraph("Klasa ucznia " + student.getClazz().getName()));


		document.add(new Paragraph(""));
		
		PdfPTable table = new PdfPTable(3);

		PdfPCell cell = new PdfPCell();

		Font font = FontFactory.getFont(FontFactory.HELVETICA);

		cell.setPhrase(new Phrase("Przedmiot", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Oceny", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Ocena koncowa", font));
		table.addCell(cell);

		for (Subject subject : subjects) {
			String marks_ = "";
			double sum = 0;
			table.addCell(subject.getName());

			for (Mark mark : marks.get(subject)) {
				marks_ += String.valueOf(mark.getMark()) + " ";
				sum += mark.getMark();
			}

			table.addCell(marks_);
			if (marks_ == "") {
				table.addCell("");
			} else {

				double avg = sum / marks.get(subject).size();

				int finalMark = Integer.parseInt(String.valueOf(Math.round(avg)));

				table.addCell(getFinalMark(finalMark));
			}
		}

		document.add(table);

	}

	private String getFinalMark(int choice) {

		String finalMark = "";

		switch (choice) {
		case 2:
			finalMark = "dopuszczaj¹cy";
			break;
		case 3:
			finalMark = "dostateczny";
			break;
		case 4:
			finalMark = "dobry";
			break;
		case 5:
			finalMark = "bardzo dobry";
			break;
		}

		return finalMark;
	}
}
