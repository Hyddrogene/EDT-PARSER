package com.leria.parser.Api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class API {
  private static String urlUA = "https://api.univ-angers.fr/api/edtLeria/";
  private static String urlAPI = "http://localhost:8080/edt-api/";

  private API() {
  }

  public static HttpResponse<String> requestMaquette(String codeEtape, String year) {
    return sendRequestUA(DataType.MAQUETTE, year + "/" + codeEtape);
  }

  public static HttpResponse<String> requestEtapes(String year) {
    return sendRequestUA(DataType.ETAPES, year);
  }

  public static HttpResponse<String> requestSalles(String year) {
    return sendRequestUA(DataType.SALLES, year);
  }

  public static HttpResponse<String> requestEnseignants(String year) {
    return sendRequestUA(DataType.ENSEIGNANTS, "");
  }

  public static HttpResponse<String> requestEtapeCalendars(String year) {
    return sendRequestAPI(DataType.ETAPE_CALENDAR, "");
  }

  public static HttpResponse<String> requestServiceEnseignant(String year, String courseId, String type) {
    return sendRequestAPI(DataType.SERVICE_ENSEIGNANT, courseId + "/" + type);
  }

  public static HttpResponse<String> requestStudentCount(String year, String codeEtape) {
    return sendRequestAPI(DataType.STUDENT_COUNT, codeEtape);
  }

  private static HttpResponse<String> sendRequestUA(DataType type, String args) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(URI.create(urlUA + type.toString() + "/" + args))
        .header("X-Token", generateTokenAPIUA(UAKey.getApiUAKey()))
        .build();
    try {
      return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      Thread.currentThread().interrupt();
      return null;
    }
  }

  private static HttpResponse<String> sendRequestAPI(DataType type, String args) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(URI.create(urlAPI + type.toString() + "/" + args))
        .build();
    try {
      return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      Thread.currentThread().interrupt();
      return null;
    }
  }

  private static String encoding(String chaine) {
    String encoding = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      byte[] bytes = md.digest(chaine.getBytes());
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      encoding = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    return encoding;
  }

  private static String generateTokenAPIUA(String key) {
    long timestamp = System.currentTimeMillis() / 1000;
    return "key=" + encoding(key + "-" + timestamp) + ",timestamp=" + timestamp;
  }
}
