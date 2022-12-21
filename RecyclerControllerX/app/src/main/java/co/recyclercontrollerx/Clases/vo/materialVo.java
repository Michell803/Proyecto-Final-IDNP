package co.recyclercontrollerx.Clases.vo;

import java.io.Serializable;

public class materialVo  implements Serializable
{
    private Integer idMaterial;
    private String tipoMaterial;
    private String nombreMaterial;
    private Integer cantidadMaterial;
    public materialVo(Integer idUsuario, Integer idMaterial, String tipoMaterial,Integer cantidadMaterial, String nombreMaterial) {
        this.idMaterial = idMaterial;
        this.nombreMaterial = nombreMaterial;
        this.tipoMaterial = tipoMaterial;
        this.cantidadMaterial=cantidadMaterial;
    }

    public materialVo() {

    }


    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMascota(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }


    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
  }
    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void getCantidadMaterial(Integer cantidadMaterial)
    {
        this.cantidadMaterial=cantidadMaterial;
    }
    public void setCantidadMaterial(Integer cantidadMaterial)
    {
        this.cantidadMaterial = cantidadMaterial;
    }


    public Integer getCantidadMaterial() {
        return cantidadMaterial;
    }

}
