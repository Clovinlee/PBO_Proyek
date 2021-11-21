/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo_proyek;

import java.util.Date;

/**
 *
 * @author chris
 */
public class User {
    private String id;
    private String kode;
    private String username;
    private String password;
    private String nama;
    private String gender;
    private Date tanggal_lahir;
    private String alamat;
    private String status;
    private String fk_jabatan;
    private String nama_jabatan;

    public User(String id, String kode, String username, String password, String nama, String gender, Date tanggal_lahir, String alamat, String status, String fk_jabatan) {
        this.id = id;
        this.kode = kode;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.gender = gender;
        this.tanggal_lahir = tanggal_lahir;
        this.alamat = alamat;
        this.status = status;
        this.fk_jabatan = fk_jabatan;
        this.nama_jabatan = DB.query("SELECT nama_jabatan from jabatan where id = "+fk_jabatan).get(0)[0];
    }

    public String getNama_jabatan() {
        return nama_jabatan;
    }

    public void setNama_jabatan(String nama_jabatan) {
        this.nama_jabatan = nama_jabatan;
    }

    public String getId() {
        return id;
    }

    public String getKode() {
        return kode;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getGender() {
        return gender;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getStatus() {
        return status;
    }

    public String getFk_jabatan() {
        return fk_jabatan;
    }
    
}
