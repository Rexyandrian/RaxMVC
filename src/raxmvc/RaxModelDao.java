/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raxmvc;

/**
 *
 * @author rax
 */
public interface RaxModelDao {
    public RaxModel.result tambah(Object... data);

    public RaxModel.result ubah(Object[][] data, Object[][] kondisi);

    public RaxModel.result hapus(Object[][] kondisi);
}
