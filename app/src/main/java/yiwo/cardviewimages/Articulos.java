package yiwo.cardviewimages;

public class Articulos {
    private String Codigo_Articulo;
    private String Nombre_Articulo;
    private String Cantidad_Articulo;
    private String TipoUnidad_Articulo;
    private String Precio_Articulo;
    private String Moneda_Articulo;

    public Articulos() {
    }

    public Articulos(String codigo_Articulo, String nombre_Articulo, String cantidad_Articulo, String tipoUnidad_Articulo, String precio_Articulo, String moneda_Articulo) {
        Codigo_Articulo = codigo_Articulo;
        Nombre_Articulo = nombre_Articulo;
        Cantidad_Articulo = cantidad_Articulo;
        TipoUnidad_Articulo = tipoUnidad_Articulo;
        Precio_Articulo = precio_Articulo;
        Moneda_Articulo = moneda_Articulo;
    }

    public String getCodigo_Articulo() {
        return Codigo_Articulo;
    }

    public String getNombre_Articulo() {
        return Nombre_Articulo;
    }

    public String getCantidad_Articulo() {
        return Cantidad_Articulo;
    }

    public String getTipoUnidad_Articulo() {
        return TipoUnidad_Articulo;
    }

    public String getPrecio_Articulo() {
        return Precio_Articulo;
    }

    public String getMoneda_Articulo() {
        return Moneda_Articulo;
    }
}
