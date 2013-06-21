// Generated by wsdl compiler for android/java
// DO NOT CHANGE!
package net.webservicex.client;


import com.leansoft.nano.ws.SOAPServiceCallback;
import com.leansoft.nano.ws.NanoSOAPClient;
import net.webservicex.GenerateBarCodeResponse;
import net.webservicex.GenerateBarCode;


/**
 This class is the SOAP client to the BarCodeSoap Web Service.
*/ 
public class BarCodeSoap_SOAPClient extends NanoSOAPClient {


    /**
     WebserviceX.NET barcode library that provides the means to create barcodes for printing and display in any internet enabled applications. This web service supports Code 128, Industrial 2 of 5, Interleaved 2 of 5, Code 2 5 Matrix, Code 39, Code 39 Extended, Code 93, Code 93 Extended, Codabar, EAN13, EAN8, MSI, Postnet, Supp2, Supp5, UPC A, UPC E0 and UPC E1 barcode formats. This Barcodes returns byte image. It supports following image format JPEG, GIF, PNG, BMP, EMF, EXIF, ICON, MEMORY BMP, TIFF and WMF.
    */
    public void generateBarCode(GenerateBarCode requestObject, SOAPServiceCallback<GenerateBarCodeResponse> serviceCallback) {
       
        super.getAsyncHttpClient().addHeader("SOAPAction", "http://www.webservicex.net/GenerateBarCode"); 
        
        super.invoke(requestObject, serviceCallback, GenerateBarCodeResponse.class);
    }


}