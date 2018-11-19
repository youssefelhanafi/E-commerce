package mps.google.com.ecom;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by famille on 3/6/2018.
 */

public class AsyncInscTask extends AsyncTask<String, Void, Void> {
    Context mContext;
    String urlParameters;
    /*
  String urlString = params[0];
        // URL to call

//        String data = params[1]; //data to post

        OutputStream out = null;
        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            out = new BufferedOutputStream(urlConnection.getOutputStream());

  //          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

//            writer.write();

    ///        writer.flush();

       //     writer.close();

            out.close();

            urlConnection.connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

     */


                /*HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData);
*/


    public AsyncInscTask(Context ctx, String urlParameters) {
        mContext = ctx;
        this.urlParameters = urlParameters;
    }

    @Override
    protected Void doInBackground(String... params) {

        String urlString = params[0];
//newuser
        try {
            // = "firstname=said&lastname=majd";
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            //int postDataLength = postData.length;
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(urlParameters);
            writer.flush();
//
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Log.i("REQINF", line);
            }
            writer.close();
            reader.close();

        } catch (Exception e) {
            Log.i("REQERR", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Toast.makeText(mContext, "POST HTTP DONE!", Toast.LENGTH_LONG).show();
    }
}