package com.unla.grupo4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.grupo4.services.QRCodeService;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {
	@Autowired
	@Qualifier("qrCodeService")
	QRCodeService qrCodeService;
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";
	
	@GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
	public void download(
			@PathVariable("codeText") String codeText,
			@PathVariable("width") Integer width,
			@PathVariable("height") Integer height)
		    throws Exception {
		        qrCodeService.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
		    }

	@GetMapping(value = "/genrateQRCode/{codeText}/{width}/{height}")
	public ResponseEntity<byte[]> generateQRCode(
			@PathVariable("codeText") String codeText,
			@PathVariable("width") Integer width,
			@PathVariable("height") Integer height)
		    throws Exception {
		        return ResponseEntity.status(HttpStatus.OK).body(qrCodeService.getQRCodeImage(codeText, width, height));
		    }
}
