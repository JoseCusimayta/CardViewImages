package yiwo.cardviewimages;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    String DOWNLOAD_URL = "http://192.168.1.111:8080/192.168.1.111/Imagenes/";
    String root = Environment.getExternalStorageDirectory().toString(); //Obetner el directorio padre
    File myDir = new File(root + "/pedidos"); // Crear una carpeta para guardar las imagenes
    Integer imagenescargadas = 0;
    String setTag = "xscpae";
    Integer ImageViewID;






    private RecyclerView recyclerView;
    private ArticulosAdapter adapter;
    private List<Articulos> articulosList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);

        articulosList = new ArrayList<>();
        adapter = new ArticulosAdapter(this, articulosList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
        SendHttpRequestTask task= new SendHttpRequestTask();
        task.execute("");
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Articulos a = new Articulos("xscpae","True Romance","True Romance","True Romance","True Romance","True Romance");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        a = new Articulos("xscpae", "Xscpae","Xscpae","Xscpae","Xscpae","Xscpae");
        articulosList.add(a);

        adapter.notifyDataSetChanged();
    }
    private void loadImageFromStorage(String ImageName)
    {

        try {
            ContextWrapper cw = new ContextWrapper(this.getApplicationContext());
            File directory = cw.getDir("pedidos", Context.MODE_PRIVATE);
            File f=new File(directory, ImageName+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            ImageView img=(ImageView)findViewById(R.id.imgPicker);
//            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    private String saveToInternalStorage(Bitmap bitmapImage, String ImageName){
        // Create imageDir

        ContextWrapper cw = new ContextWrapper(this.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("pedidos", Context.MODE_PRIVATE);
        File mypath=new File(directory,ImageName+".jpg");

        Log.d("Funciona?","Imagen Guardada: "+mypath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.d("Funciona?","Imagen Guardada: "+mypath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();

    }

    private class SendHttpRequestTask extends AsyncTask<String, Void, Bitmap> {
        String imagen_nombre = setTag;
        File imgFile = new File(myDir, imagen_nombre + ".jpg");
        Bitmap bitmap;

        @Override
        protected Bitmap doInBackground(String... params) {
            try {

                URL url = new URL(DOWNLOAD_URL + imagen_nombre + ".jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
                SaveImage(bitmap, imagen_nombre);
                saveToInternalStorage(bitmap,imagen_nombre);
                return bitmap;
                /*
                    if (imgFile.exists()) {
                        bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    }
                 else {
                }*/
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

//            ImageView myImage =  findViewById(ImageViewID);
//            myImage.setImageBitmap(bitmap);

        }
    }


    private void SaveImage(Bitmap finalBitmap, String tagName) {
        myDir.mkdirs();

        File file = new File(myDir, tagName + ".jpg"); //guardar el archivo con el nombre de la imagen en el directorio "myDir"

        if (file.exists())
            file.delete(); //Para reemplazar la imagen

        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Save Image: " + e.getMessage());
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
