
/**
 * Write a description of class BerkasPendaftaran here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BerkasPendaftaran
{
    private String formulirPendaftaranWisuda;
    private String formulirBiodataMahasiswa;
    private String formulirPermohonanBebasPustaka;
    private String formulirBebasLaboratorium;
    private String formulirSuratIkutSertaWisuda;
    private String formulirDokumenWisuda;
    private String formulirPenyerahanKaryaIlmiah;
    
   public BerkasPendaftaran(
   String formulirPendaftaranWisuda,
   String formulirBiodataMahasiswa,
   String formulirPermohonanBebasPustaka,
   String formulirBebasLaboratorium,
   String formulirSuratIkutSertaWisuda,
   String formulirDokumenWisuda,
   String formulirPenyerahanKaryaIlmiah
   ){
    this.formulirPendaftaranWisuda = formulirPendaftaranWisuda;
    this.formulirBiodataMahasiswa = formulirBiodataMahasiswa;
    this.formulirPermohonanBebasPustaka = formulirPermohonanBebasPustaka;
    this.formulirBebasLaboratorium = formulirBebasLaboratorium;
    this.formulirSuratIkutSertaWisuda = formulirSuratIkutSertaWisuda;
    this.formulirDokumenWisuda = formulirDokumenWisuda;
    this.formulirPenyerahanKaryaIlmiah = formulirPenyerahanKaryaIlmiah;
    
   }
   
   public void displayBerkasPendaftaran(String namaMahasiswa){

    System.out.println("Berkas Pendaftaran Wisuda " + namaMahasiswa);
    System.out.println("Formulir Pendaftaran Wisuda : " + formulirPendaftaranWisuda);
    System.out.println("Formulir Biodata Mahasiswa : " + formulirBiodataMahasiswa);
    System.out.println("Formulir Permohonan Bebas Pustaka : " + formulirPermohonanBebasPustaka);
    System.out.println("Formulir Bebas Laboratorium : " + formulirBebasLaboratorium);
    System.out.println("Formulir Surat Ikut Serta Wisuda : " + formulirSuratIkutSertaWisuda);
    System.out.println("Formulir Dokumen Wisuda : " + formulirDokumenWisuda);
    System.out.println("Formulir Penyerahan Karya Ilmiah : " + formulirPenyerahanKaryaIlmiah);
   
   }
   
   
}
