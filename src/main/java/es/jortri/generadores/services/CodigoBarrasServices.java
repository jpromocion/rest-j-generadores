package es.jortri.generadores.services;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.oned.UPCEWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Referencia https://www.baeldung.com/java-generating-barcodes-qr-codes
 */
@Service
public class CodigoBarrasServices {

	public BufferedImage generateEAN13BarcodeImage(String barcodeText, int width, int height) throws Exception {
	    EAN13Writer barcodeWriter = new EAN13Writer();
	    BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, width, height);

	    return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
	
	public BufferedImage generateCode128BarcodeImage(String barcodeText, int width, int height) throws Exception {
		Code128Writer barcodeWriter = new Code128Writer();
	    BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, width, height);

	    return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}	
	
	public BufferedImage generateUPCABarcodeImage(String barcodeText, int width, int height) throws Exception {
		UPCAWriter barcodeWriter = new UPCAWriter();
	    BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.UPC_A, width, height);

	    return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}	
	
	public BufferedImage generateUPCEBarcodeImage(String barcodeText, int width, int height) throws Exception {
		UPCEWriter barcodeWriter = new UPCEWriter();
	    BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.UPC_E, width, height);

	    return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}		
	
    public BufferedImage generatePDF417BarcodeImage(String barcodeText, int width, int height) throws Exception {
        PDF417Writer barcodeWriter = new PDF417Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.PDF_417, width, height);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

  

	private BufferedImage modifiedQRCode(BitMatrix matrix, String topText, String bottomText) {
		int matrixWidth = matrix.getWidth();
		int matrixHeight = matrix.getHeight();

		BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixHeight);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
		    for (int j = 0; j < matrixHeight; j++) {
		        if (matrix.get(i, j)) {
		            graphics.fillRect(i, j, 1, 1);
		        }
		    }
		}
		
		//texto top
		FontMetrics fontMetrics = graphics.getFontMetrics();
		int topTextWidth = fontMetrics.stringWidth(topText);
		int bottomTextWidth = fontMetrics.stringWidth(bottomText);
		int finalWidth = Math.max(matrixWidth, Math.max(topTextWidth, bottomTextWidth)) + 1;
		int finalHeight = matrixHeight + fontMetrics.getHeight() + fontMetrics.getAscent() + 1;
		
		//texto bottom
		BufferedImage finalImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D finalGraphics = finalImage.createGraphics();
		finalGraphics.setColor(Color.WHITE);
		finalGraphics.fillRect(0, 0, finalWidth, finalHeight);
		finalGraphics.setColor(Color.BLACK);

		finalGraphics.drawImage(image, (finalWidth - matrixWidth) / 2, fontMetrics.getAscent() + 2, null);
		finalGraphics.drawString(topText, (finalWidth - topTextWidth) / 2, fontMetrics.getAscent() + 2);
		finalGraphics.drawString(bottomText, (finalWidth - bottomTextWidth) / 2, finalHeight - fontMetrics.getDescent() - 5);
		
		return finalImage;

	}	
	
	public BufferedImage generateQRCodeImage(String barcodeText, int width, int height, String topText, String bottomText) throws Exception {
	    QRCodeWriter barcodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = 
	      barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, width, height);

		if ((topText != null && !topText.isEmpty()) || (bottomText != null && !bottomText.isEmpty())) {
			return modifiedQRCode(bitMatrix, topText, bottomText);
		} else {
			return MatrixToImageWriter.toBufferedImage(bitMatrix);	
		}
	    
	    
	}
	
	public BufferedImage generateQRCodeImage(String barcodeText, int width, int height) throws Exception {
		return generateQRCodeImage(barcodeText, width, height, null, null);
	}

	
	
	
	
}
