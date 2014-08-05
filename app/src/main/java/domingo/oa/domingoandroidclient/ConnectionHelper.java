package domingo.oa.domingoandroidclient;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by ophir-pc on 04/08/2014.
 */
public class ConnectionHelper {

    public void ExecuteHttpRequestAsync(String path, String queryParams, IConnectionCallback callback){
        HttpRequestTask task = new HttpRequestTask(callback);

        task.execute(path + "?" + queryParams);
    }

    public interface IConnectionCallback {
        public void Callback(Exception error, String result);
    }

    private class HttpRequestTask extends AsyncTask<String, Integer, String> {

        IConnectionCallback _callback;
        Exception _error = null;

        public HttpRequestTask(IConnectionCallback callback){
            _callback = callback;
        }

        protected String doInBackground(String... query) {
            // build the request string
            String request = "http://54.191.112.211:1337/" + query[0];

            try {
                // creating http client
                HttpClient httpclient = new DefaultHttpClient();
                httpclient.getParams().setParameter("http.socket.timeout", new Integer(1000));
                HttpResponse response = httpclient.execute(new HttpGet(request));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    String responseString = out.toString();

                    return responseString;
                }
                else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            }
            catch (ClientProtocolException e){
                _error = e;
            }
            catch (IOException e){
                _error = e;
            }

            return null;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(String result) {
            if (_callback != null){
                _callback.Callback(_error, result);
            }
        }
    }
}



