package com.base.common.util.http;

import okhttp3.*;

import java.io.IOException;
import java.util.Base64;

public class SoapUtil {

    public static void send(String soapUrl, String Username, String Password) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/xml");
            RequestBody body = RequestBody.create(mediaType, "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:sap-com:document:sap:rfc:functions\">\r\n    <soap:Header/>\r\n    <soap:Body>\r\n        <urn:Z_TM_CREATE_PO>\r\n            <IV_RECEIVE_IN>[{\"Header\":{\"Statement_ID\":\"ST2304260001\",\"GUID\":\"100292762259161089\",\"STATUS\":\"I\",\"Created_Date\":\"20230426\",\"Create_Time\":\"165431\",\"BSART\":\"Z013\",\"LIFNR\":\"15000048\",\"TDLINEH\":\"运输费\",\"INCO1\":\"DDP\",\"INCO2_L\":\"DDP\",\"EKGRP\":\"214\",\"BUKRS\":\"1000\",\"EKORG\":\"1000\",\"ERNAM\":\"50000472\",\"Item\":[{\"EBELP\":\"10\",\"KNTTP\":\"K\",\"PS_POSID\":\"\",\"MENGE\":\"1\",\"MEINS\":\"EA\",\"PREIS\":7004.85,\"MWSKZ\":\"J3\",\"WAERS\":\"CNY\",\"WERKS\":\"1000\",\"AFNAM\":\"56432\",\"TXZ01\":\"20230418鄂尔多斯市-上海市4.2普厢（恒温）运费\",\"AUFNR\":\"\",\"LFDAT\":\"20230418\",\"MATKL\":\"9910\",\"KOSTL\":\"1000G007\"}]}}]</IV_RECEIVE_IN>\r\n        </urn:Z_TM_CREATE_PO>\r\n    </soap:Body>\r\n</soap:Envelope>");
            String authorization =  Base64.getEncoder().encodeToString(
                    new StringBuilder(Username).append(":").append(Password).toString().getBytes()
            );
            Request request = new Request.Builder()
                    .url(soapUrl)
                    .method("POST", body)
                    .addHeader("Content-Type", "text/xml")
                    .addHeader("Authorization", "Basic " + authorization)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        /**
         * SOAP 1.1 = " http://schemas.xmlsoap.org/soap/envelope/ "
         *
         * SOAP 1.2 =“ http://www.w3.org/2003/05/soap-envelope ”
         */
    }
}
