package models;

/**
 *
 * @author to2libre
 */
public class ClienteNormaConsumo {

    int idCliente;
    int idAnno;
    double ene;
    double feb;
    double mar;
    double abr;
    double may;
    double jun;
    double jul;
    double ago;
    double sep;
    double oct;
    double nov;
    double dic;

    public ClienteNormaConsumo() {
    }

    public ClienteNormaConsumo(int idCliente, int id_anno, double ene, double feb, double mar, double abr, double may, double jun, double jul, double ago, double sep, double oct, double nov, double dic) {
        this.idCliente = idCliente;
        this.idAnno = id_anno;
        this.ene = ene;
        this.feb = feb;
        this.mar = mar;
        this.abr = abr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.ago = ago;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dic = dic;
    }

    public int getIdAnno() {
        return idAnno;
    }

    public double getAbr() {
        return abr;
    }

    public double getAgo() {
        return ago;
    }

    public double getDic() {
        return dic;
    }

    public double getEne() {
        return ene;
    }

    public double getFeb() {
        return feb;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getJul() {
        return jul;
    }

    public double getJun() {
        return jun;
    }

    public double getMar() {
        return mar;
    }

    public double getMay() {
        return may;
    }

    public double getNov() {
        return nov;
    }

    public double getOct() {
        return oct;
    }

    public double getSep() {
        return sep;
    }

    public void setAbr(double abr) {
        this.abr = abr;
    }

    public void setAgo(double ago) {
        this.ago = ago;
    }

    public void setDic(double dic) {
        this.dic = dic;
    }

    public void setEne(double ene) {
        this.ene = ene;
    }

    public void setFeb(double feb) {
        this.feb = feb;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setJul(double jul) {
        this.jul = jul;
    }

    public void setJun(double jun) {
        this.jun = jun;
    }

    public void setMar(double mar) {
        this.mar = mar;
    }

    public void setMay(double may) {
        this.may = may;
    }

    public void setNov(double nov) {
        this.nov = nov;
    }

    public void setOct(double oct) {
        this.oct = oct;
    }

    public void setSep(double sep) {
        this.sep = sep;
    }

    public void setIdAnno(int idAnno) {
        this.idAnno = idAnno;
    }
}
