package salonistan.ghazanfar.com.urltobase64;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {
    String base64ofIMage;
    TextView todisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ImageTask().execute();


    }


    private class ImageTask extends AsyncTask<Void, Integer, Void> {
        Bitmap bitmap;

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            java.net.URL img_value = null;
            Log.d("taking", "2");
            try {
                img_value = new java.net.URL("http://salonistan.com/public/assets/img/category/category_8JVHlfWkhZ.png");


                //img_value = new URL("http://graph.facebook.com/"+ userProfileID +"/picture?type=square");
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeStream(img_value.openConnection().getInputStream(), null, options);
                Log.d("taking", "3" + img_value);
                Log.d("taking", "3" + bitmap);
                Log.d("taking", String.valueOf(bitmap));

                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                byte[] ba = bao.toByteArray();
                base64ofIMage = Base64.encodeToString(ba, Base64.DEFAULT);

                System.out.println("Encoded image is : ===== " + base64ofIMage);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            ShowInPopUp(base64ofIMage);

            if (bitmap != null) {
//                user_pic.setImageBitmap(bitmap);
//                get_string_image = encodeTobase64(bitmap);
//                ByteArrayOutputStream bao = new ByteArrayOutputStream();

            }
        }
    }

    private void ShowInPopUp(final String base64ofIMage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result.....");
        builder.setMessage(base64ofIMage);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();

    }
}
