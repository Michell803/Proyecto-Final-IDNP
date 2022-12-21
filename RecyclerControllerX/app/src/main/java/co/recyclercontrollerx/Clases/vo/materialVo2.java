package co.recyclercontrollerx.Clases.vo;

public class materialVo2 {

    private String nombreDeMaterial;
    private String tipoDeMaterial;
    private Integer cantidadDeMaterial;

    public materialVo2() {

    }

    public materialVo2(String nombreDeMaterial, String tipoDeMaterial, Integer cantidadDeMaterial) {
        this.nombreDeMaterial = nombreDeMaterial;
        this.tipoDeMaterial = tipoDeMaterial;
        this.cantidadDeMaterial = cantidadDeMaterial;
    }

    public String getNombreDeMaterial() {
        return nombreDeMaterial;
    }

    public void setNombreDeMaterial(String nombreDeMaterial) {
        this.nombreDeMaterial = nombreDeMaterial;
    }

    public String getTipoDeMaterial() {
        return tipoDeMaterial;
    }

    public void setTipoDeMaterial(String tipoDeMaterial) {
        this.tipoDeMaterial = tipoDeMaterial;
    }

    public Integer getCantidadDeMaterial() {
        return cantidadDeMaterial;
    }

    public void setCantidadDeMaterial(Integer cantidadDeMaterial) {
        this.cantidadDeMaterial = cantidadDeMaterial;
    }
}
