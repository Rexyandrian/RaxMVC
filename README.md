# RaxMVC

RaxMVC merupakan java framework yang di desain yang mudah dipahami dan diperlajari. 
## Fitur :
0. Dilengkapi dengan versi 8 pada mysql.
1. Memudahkan untuk mengelola basis data (DDL&DML).
2. Terdapat syntax yang mudah dipahami.
3. Dilengkapi Email Sender / pengiriman email. 

Dalam struktur berkas MVC maka anda hanya perlu membuat konfigurasi pada main class dalama project.

Dalam kelas utama anda dapat menambahkan Parent/Kelas induk dengan 'RaxConfig', lalu di akhiri dengan menerapkan konfigurasi.

#### contoh konfigurasi basis data.

import raxmvc.RaxConfig;
import raxmvc.RaxDatabase;

public class MyClass extends RaxConfig{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setDBserver("localhost");
        setDBusername("root");
        setDBport(3306);
        setDBpassword("");
        setDatabase("db_kunci_logistics");
        setAutoConnectDB(true);
        setTimeZone(timeZoneGMT.GMT_PLUS_7);       
        RaxDataBase.getInstance(); // guna menerapkan konfigurasi yang telah dibuat.
    }
    
}
