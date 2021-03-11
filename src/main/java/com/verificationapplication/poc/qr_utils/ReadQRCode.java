package com.verificationapplication.poc.qr_utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ReadQRCode {
	
	public static String readQRCode(String base64image) throws WriterException, IOException, NotFoundException
    {
		// for testing:
		// base64image = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4AQAAAAARHwt/AAABBElEQVR4Xu3UsY3FIAwAUEcUdPkLIGUNuqxEFgj3F7isRMcaSFkg6ShQfEY6LkljOOn/7lPxCiNjGwDvCz5+tROAAi02ANtmi2rWuOdNk40cljjsTrX7kPgfqxlu8bwpv86LW76s6f6TV+ZaD8600gMzsMmpi6qX4ksG2+ZeB+txiSW+YtwjdD4YvbY5mRE3IJfzKsZjFM8Is/ytb4NDh8P3JZ411RfmMRg4+8EaDwgPXDctynm8qV40T3RI6WfFmFvilXV512DKb3g6NbkzX940f4aafZ1X1vQeMCqAcp8mpyn+9btqZcZkoMxfzfn9xHVxl/+CNb2fHlIvz3qwvq2P3+sfBs57KXwyARYAAAAASUVORK5CYII=";
		
		base64image = base64image.substring(base64image.lastIndexOf(",") + 1);
		
        //Not sure where this is going, but it works
		String savedImgFilePath = "//tempfolder// " + (int)(Math.random() * (10000 - 20000 + 1) + 20000) + ".png";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
 
        byte[] decodedBytes = Base64.getDecoder().decode(base64image);
		
        FileUtils.writeByteArrayToFile(new File(savedImgFilePath), decodedBytes);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new File(savedImgFilePath)))));
		Result result = new MultiFormatReader().decode(binaryBitmap);

		return result.getText();
    }


}
