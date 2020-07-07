package com.lti.eprms.eprmsservice.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestClient {
	private static Logger LOGGER = LogManager.getLogger(RestClient.class);

	/**
	 * This method will be used to call rest webservices through HTTP GET
	 * request.
	 * 
	 * @param serviceName
	 *            Name of the service to be called
	 * @return Result received from webservice
	 */
	public static String callRESTservice(String serviceURL) {
		try {
			// serviceURL="https://ba-service.mycdsglobal.com/ws/utility/clients/dbuck@cds-global.com?appId=dataprivacy&pwd=ep4dqhd41p&applicationId=249&clientId=2";
			 TrustManager[] trustAllCerts = new TrustManager[] {
				    new X509TrustManager() {
				        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				            return null;
				        }
				        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				            //No need to implement. 
				        }
				        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				            //No need to implement. 
				        }
				    }
				};
				// Install the all-trusting trust manager
				
				    SSLContext sc = SSLContext.getInstance("SSL");
				    sc.init(null, trustAllCerts, new java.security.SecureRandom());
				

			URL url = new URL(serviceURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDefaultSSLSocketFactory(sc.getSocketFactory());
			conn.setRequestMethod("GET");
			conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML.getType());
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			String result = "";
			while ((output = br.readLine()) != null) {
				result += output;
			}
			conn.disconnect();
			return result;
		} catch (Exception e) {
			LOGGER.info("Exception in NetClientGet:- " + e);
		}
		return null;
	}

	public static String callRESTservicePUTMethod(String serviceURL, String xmlInputString) {
		try {
			// serviceURL="https://ba-service.mycdsglobal.com/ws/utility/clients/dbuck@cds-global.com?appId=dataprivacy&pwd=ep4dqhd41p&applicationId=249&clientId=2";
			LOGGER.info("serviceURL is "+serviceURL);
			LOGGER.info("xmlInputString in Rest Client is "+serviceURL);
			
			
			 TrustManager[] trustAllCerts = new TrustManager[] {
					    new X509TrustManager() {
					        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					            return null;
					        }
					        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					            //No need to implement. 
					        }
					        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					            //No need to implement. 
					        }
					    }
					};
					// Install the all-trusting trust manager
					
					    SSLContext sc = SSLContext.getInstance("SSL");
					    sc.init(null, trustAllCerts, new java.security.SecureRandom());
					

			
			
			URL url = new URL(serviceURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setDefaultSSLSocketFactory(sc.getSocketFactory());
			conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);
//			conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

			// conn.setRequestProperty(HttpHeaers.ACCEPT,, "application/json");
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write(xmlInputString);
			osw.flush();
			osw.close();
			// try (OutputStream os = conn.getOutputStream()) {
			// byte[] input = xmlInputString.getBytes("utf-8");
			// os.write(input, 0, input.length);
			// LOGGER.info("Input "+input);
			// }
			LOGGER.info("conn.getResponseCode() is "+conn.getResponseCode());
			if (conn.getResponseCode() != 201) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			String result = "";
			while ((output = br.readLine()) != null) {
				result += output;
			}
			conn.disconnect();
			return result;
		} catch (Exception e) {
			LOGGER.info("Exception in NetClientGet:- " + e);
			LOGGER.info(e);
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//		String xmlInputString = "<customer> <permissions><emailUseForCompanyPerm>N</emailUseForCompanyPerm></permissions></customer>";
		// String jsonInput="{\"id\":\"788\"}";
//		String xmlInputString="<customer><email>test@icepr.com</email><permissions><emailAuthorization>N</emailAuthorization><emailUseForCompanyPerm>N</emailUseForCompanyPerm></permissions></customer>";
//		String xmlInputString="<customer><transactionSource><emplId>1234</emplId></transactionSource><email>test@example.com</email><permissions><emailAuthorization>N</emailAuthorization><emailUseForCompanyPerm>N</emailUseForCompanyPerm></permissions></customer>";
//		RestClient.callRESTservicePUTMethod("http://192.168.43.7:8090/ccpaui/cds-ccpa-request-system/customer/MGH",xmlInputString);
		
//		String url="https://ba-service.mycdsglobal.com/ws/service/customer/WS1/appId=dataprivacy&pwd=ep4dqhd41p";
//		String url="https://service.mycdsglobal.com/ws/service/customer/WS1/appId=dataprivacy&pwd=ep4dqhd41p";
		
//		String url="https://ba-service.mycdsglobal.com/ws/service/customer/WS1/1378753220?appId=dataprivacy&pwd=CCPItest123";
		
		
		String url="https://ba-service.mycdsglobal.com/ws/service/customer/WS1/1378753725?appId=dataprivacy&pwd=CCPItest123";
		String xmlInputString="<customer><transactionSource><emplId>1234</emplId></transactionSource><email>james515@example.com</email><permissions><emailAuthorization>N</emailAuthorization><emailUseForCompanyPerm>N</emailUseForCompanyPerm></permissions></customer>";

		String response=RestClient.callRESTservicePUTMethod(url,xmlInputString);
		LOGGER.info("Response is "+response);
	}
	
}
