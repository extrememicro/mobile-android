package exo.exoplatform.document;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import exo.exoplatform.controller.AppController;

//	File info
public class ExoFile {

  public String  urlStr;     // File URL

  public String  fileName;   // File name

  public String  contentType; // File content type

  public boolean isFolder;   // is folder

  // Default constructors
  public ExoFile() {

  }

  // Construtor
  public ExoFile(String urlString, String file_Name) {
    HttpURLConnection con = null;
    try {
      String strUserName = AppController.sharedPreference.getString(AppController.EXO_PRF_USERNAME,
                                                                    "exo_prf_username");
      String strPassword = AppController.sharedPreference.getString(AppController.EXO_PRF_PASSWORD,
                                                                    "exo_prf_password");

      urlStr = urlString.replace(" ", "%20");
      fileName = file_Name;

      URL url = new URL(urlStr);
      con = (HttpURLConnection) url.openConnection();

      con.setRequestMethod("GET");
      // String authorizationStr =
      con.setRequestProperty("Authorization",
                             AppController._eXoConnection.authorizationHeader(strUserName,
                                                                              strPassword));

      contentType = con.getContentType();

      if (contentType.indexOf("text/html") >= 0) {
        if (con.getContentEncoding() == null) {
          isFolder = true;
        } else {
          isFolder = false;
        }

      } else {
        isFolder = false;
      }

    } catch (ClientProtocolException e) {
      e.getMessage();
    } catch (IOException e) {
      // String str = e.toString();
      // String msg = e.getMessage();
      // Log.v(str, msg);
    }

    con.disconnect();

  }
}
