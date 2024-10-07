package es.jortri.generadores.controller;

import java.awt.image.BufferedImage;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.services.CodigoBarrasServices;

/**
 * Controlador de generación de codigos de barra
 * https://www.baeldung.com/java-generating-barcodes-qr-codes
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/barcodes")
public class BarcodesController {

	@Autowired
	private CodigoBarrasServices codigoBarrasServices;
	
    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }	
	
	/**
	 * Generar un codigo barras tipo 128
	 * 
	 * @param barcode Texto asociado al codigo. Se recibe en el cuerpo de la petcion
	 * @return
	 * @throws Exception
	 */
    @PostMapping(value = "/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> code128(@RequestBody String barcode, 
    		@RequestParam Optional<Integer> width, @RequestParam Optional<Integer> height) throws Exception {
        return okResponse(codigoBarrasServices.generateCode128BarcodeImage(barcode, width.orElse(300), height.orElse(150)) );
    }	
	
	/**
	 * Generar un codigo de barras EAN13
	 * @param barcode Texto asociado al codigo. Se recibe en la URL de seguido tras /ean13/12345
	 * @return
	 * @throws Exception
	 */
    @GetMapping(value = "/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> ean13(@PathVariable("barcode") String barcode,
    		@RequestParam Optional<Integer> width, @RequestParam Optional<Integer> height) throws Exception {
        return okResponse(codigoBarrasServices.generateEAN13BarcodeImage(barcode, width.orElse(300), height.orElse(150)));
    }
		
    /**
     * Generar un codigo de barras UPC-A
     * @param barcode Texto asociado al codigo. Se recibe en la URL de seguido tras /upca/12345
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> upca(@PathVariable("barcode") String barcode,
    		@RequestParam Optional<Integer> width, @RequestParam Optional<Integer> height) throws Exception {
        return okResponse(codigoBarrasServices.generateUPCABarcodeImage(barcode, width.orElse(300), height.orElse(150)));
    }
    
    /**
     * Generar un codigo de barras UPC-E
     * @param barcode Texto asociado al codigo. Se recibe en la URL de seguido tras /upce/12345
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/upce/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> upce(@PathVariable("barcode") String barcode,
    		@RequestParam Optional<Integer> width, @RequestParam Optional<Integer> height) throws Exception {
        return okResponse(codigoBarrasServices.generateUPCEBarcodeImage(barcode, width.orElse(300), height.orElse(150)));
    }    

    /**
     * Generar un codigo PDF417 
     * @param barcode Texto. Se recibe en el cuerpo de la peticion
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> pdf417(@RequestBody String barcode, 
    		@RequestParam Optional<Integer> width, @RequestParam Optional<Integer> height) throws Exception {
        return okResponse(codigoBarrasServices.generatePDF417BarcodeImage(barcode, width.orElse(700), height.orElse(700)));
    }

    /**
     * Generar un codigo QR
     * @param barcode Texto. Se recibe en el cuerpo de la peticion
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode, 
    		@RequestParam Optional<Integer> width, @RequestParam Optional<Integer> height,
    		@RequestParam Optional<String> toptext, @RequestParam Optional<String> bottomtext) throws Exception {
        return okResponse(codigoBarrasServices.generateQRCodeImage(barcode, width.orElse(200), height.orElse(200), toptext.orElse(""), bottomtext.orElse("")));
    }
    
    
	
	
}
