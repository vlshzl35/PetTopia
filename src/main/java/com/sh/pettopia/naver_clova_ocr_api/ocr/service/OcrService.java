package com.sh.pettopia.naver_clova_ocr_api.ocr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class OcrService {
    @Value("${naver.clova-ocr.document.url}")
    private String DOCUMENT_OCR_URL;

    @Value("${naver.clova-ocr.document.secretKey}")
    private String DOCUMENT_OCR_SECRET_KEY;

    public String processOcr(String type, byte[] imageData) {
        try {
            URL url = new URL(DOCUMENT_OCR_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");

            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", DOCUMENT_OCR_SECRET_KEY);

            Map<String, Object> map = new HashMap<>();
            map.put("version", "V2");
            map.put("requestId", UUID.randomUUID().toString());
            map.put("timestamp", System.currentTimeMillis());
            map.put("enableTableDetection", true);

            Map<String, Object> imageMap = new HashMap<>();
            imageMap.put("format", "jpg");
            imageMap.put("name", "demo");
            List<Map<String, Object>> imagesList = new ArrayList<>();
            imagesList.add(imageMap);
            map.put("images", imagesList);

            ObjectMapper objectMapper = new ObjectMapper();
            String postParams = objectMapper.writeValueAsString(map);

            con.connect();

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            writeMultiPart(wr, postParams, imageData, boundary);
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            con.disconnect();
            return response.toString();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static void writeMultiPart(OutputStream out, String jsonMessage, byte[] fileData, String boundary) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");

        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (fileData != null && fileData.length > 0) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString.append("Content-Disposition:form-data; name=\"file\"; filename=\"uploaded_image.jpg\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            out.write(fileData);
            out.write("\r\n".getBytes());
            out.flush();

            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
            out.flush();
        }
    }
}

//@Service
//public class OcrService {
//    @Value("${naver.clova-ocr.document.url}")
//    private String DOCUMENT_OCR_URL;
//    @Value("${naver.clova-ocr.document.secretKey}")
//    private String DOCUMENT_OCR_SECRET_KEY;
//
//    public String processOcr(String type, String imageFile) {
//        try {
//            URL url = new URL(DOCUMENT_OCR_URL);
//            // HttpURLConnection 해당 URL에 통신 가능한 Connection 객체
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            // 요청 헤더 설정
//            con.setUseCaches(false); // 캐싱 방지(매번 새롭게 요청해라)
//            con.setDoInput(true); // 입력스트림 허용
//            con.setDoOutput(true); // 출력스트림 허용
//            con.setReadTimeout(30000); // 연결 제한시간 30초(30초가 지나도 연결이 안되면 연결 끊어버림)
//            con.setRequestMethod("POST"); // 전송방식 (GET으로는 멀티파트를 보낼 수 없다)
//
//            // NCP 요청 필수 설정
//            // X-OCR-SECRET : NCP가 정한 시크릿키 헤더
//            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", ""); // UUID는 aa-aa-aa.png식인데 -(하이픈)을 지움
//            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//            con.setRequestProperty("X-OCR-SECRET", DOCUMENT_OCR_SECRET_KEY);
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("version", "V2");
//            map.put("requestId", UUID.randomUUID().toString());
//            map.put("timestamp", System.currentTimeMillis());
//            map.put("enableTableDetection", true);
//
//            // imagesList 배열 안에 imageMap객체를 담는 과정임
//            Map<String, Object> imageMap = new HashMap<>();
//            imageMap.put("format", "jpg");
//            imageMap.put("name", "demo");
//            List<Map<String, Object>> imagesList = new ArrayList<>();
//            imagesList.add(imageMap);
//            map.put("images", imagesList);
//
//            // Java객체 -> json변환
//            // ObjectMapper#writeValueAsString(자바객체):String(json문자열)
//            ObjectMapper objectMapper = new ObjectMapper();
//            String postParams = objectMapper.writeValueAsString(map); // json문자열을 postParams에 담음
//
//            // 통신 시작
//            con.connect();
//            // A.요청
//            // 자바자료형 단위로 출력가능한 스트림
//            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            long start = System.currentTimeMillis();
//            File file = new File(imageFile);
//            // multipart/form-data 요청 메세지 작성
//            writeMultiPart(wr, postParams, file, boundary);
//            wr.close(); // 쓰기 중지
//
//            int responseCode = con.getResponseCode(); // 응답코드 ex) 200, 500 등
//            BufferedReader br;
//            if (responseCode == 200) {
//                // con 입력스트림으로부터 응답데이터(코드) 읽기
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            } else { // 200이 아니면 에러가 발생하므로 에러메시지를 가지고 있는 ErrorStream에서 읽어들임
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//            }
//            String inputLine;
//            // StringBuffer 문자열 이어쓰기 최적화된 객체
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = br.readLine()) != null) {
//                // 읽어낸 br(응답 메시지)을 line단위로 response라는 StringBuffer에 차곡차곡 담는다
//                response.append(inputLine);
//            }
//            br.close();
//            // 통신 종료 (통신 마무리 꼭 해줘야함)
//            con.disconnect();
//            return response.toString();
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//
//    }
//
//    private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
//            IOException {
//        StringBuilder sb = new StringBuilder();
//        sb.append("--").append(boundary).append("\r\n");
//        // json 문자열 적기
//        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
//        sb.append(jsonMessage);
//        sb.append("\r\n");
//
//        // 쓰기(출력)
//        out.write(sb.toString().getBytes("UTF-8"));
//        out.flush();
//
//        if (file != null && file.isFile()) { // file이 존재한다면
//            out.write(("--" + boundary + "\r\n").getBytes("UTF-8")); // json뒤에 경계문구 추가
//            StringBuilder fileString = new StringBuilder(); // 파일명 등을 담음
//            fileString
//                    .append("Content-Disposition:form-data; name=\"file\"; filename="); // header달기
//            fileString.append("\"" + file.getName() + "\"\r\n");
//            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
//            out.write(fileString.toString().getBytes("UTF-8"));
//            out.flush();
//
//            // 파일 이진데이터ㅅ 쓰기
//            try (FileInputStream fis = new FileInputStream(file)) {
//                byte[] buffer = new byte[8192];
//                int count;
//                while ((count = fis.read(buffer)) != -1) {
//                    out.write(buffer, 0, count);
//                }
//                out.write("\r\n".getBytes());
//            }
//            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
//        }
//        out.flush();
//    }
//}
