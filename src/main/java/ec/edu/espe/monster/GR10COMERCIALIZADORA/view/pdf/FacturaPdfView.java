package ec.edu.espe.monster.GR10COMERCIALIZADORA.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Factura;

@Component("/store/detalle-factura")
public class FacturaPdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Factura factura = (Factura) model.get("factura");
		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Datos del Cliente");
		tabla.addCell(factura.getCustomer().getNames_customer()+" "+factura.getCustomer().getLastnames_customer());
		tabla.addCell(factura.getCustomer().getEmail_customer());
		
		PdfPTable tabla2 = new PdfPTable(1);
		tabla2.addCell("Datos de la Factura");
		tabla2.addCell("Código de la Factura: "+ factura.getId_factura());
		tabla2.addCell("Descripción: "+ factura.getDescripcion_factura());
		tabla2.addCell("Fecha de emisión: "+ factura.getEmision_factura());
		
		document.add(tabla);
		document.add(tabla2);
	}

}
