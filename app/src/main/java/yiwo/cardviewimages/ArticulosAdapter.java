package yiwo.cardviewimages;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.MyViewHolder> {

    private Context mContext;
    private List<Articulos> articulosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_articulo;
        public TextView tv_descripcion;

        public MyViewHolder(View view) {
            super(view);
            iv_articulo =  view.findViewById(R.id.iv_articulo);
            tv_descripcion =  view.findViewById(R.id.tv_descripcion);
        }
    }

    public ArticulosAdapter(Context mContext, List<Articulos> articulosList) {
        this.mContext = mContext;
        this.articulosList = articulosList;
    }


    /**
     * Showing popup menu when tapping on 3 dots
     */
  /*  private void showPopupMenu(View view, Integer position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }*/

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        Integer pos=0;
        public MyMenuItemClickListener(Integer position) {
            pos=position;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.iv_articulo:
                    Articulos articulos= articulosList.get(pos);
                    Log.d("Posicion",pos+"");
                    Log.d("album",articulos.getNombre_Articulo()+"");
                    Toast.makeText(mContext, pos+"  Add to favourite "+articulos.getNombre_Articulo(), Toast.LENGTH_SHORT).show();
                    return true;
//                case R.id.iv_articulo:
//                    Toast.makeText(mContext, "Play next " , Toast.LENGTH_SHORT).show();
//                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return articulosList.size();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.articulos_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Articulos articulos = articulosList.get(position);
        holder.tv_descripcion.setText(articulos.getCodigo_Articulo());
//        holder.tv_descripcion.setText(articulos.getCodigo_Articulo() + " songs");

        // loading articulos cover using Glide library
//        Glide.with(mContext).load(articulos.getCodigo_Articulo()).into(holder.iv_articulo);

        holder.iv_articulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Articulos articulos = articulosList.get(position);
                Log.d("Posicion", position + "");
                Log.d("album", articulos.getNombre_Articulo() + "");
                Toast.makeText(mContext, position + "  Add to favourite " + articulos.getNombre_Articulo(), Toast.LENGTH_SHORT).show();
//                return true;
//                showPopupMenu(holder.overflow, position);
            }
        });


        try {
            ContextWrapper cw = new ContextWrapper(mContext.getApplicationContext());
            File directory = cw.getDir("pedidos", Context.MODE_PRIVATE);
            File f = new File(directory, articulos.getCodigo_Articulo() + ".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            holder.iv_articulo.setImageBitmap(b);
            Log.d("Funciona?",f+"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void loadImageFromStorage(String ImageName)
    {

        try {
            ContextWrapper cw = new ContextWrapper(mContext.getApplicationContext());
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

}
