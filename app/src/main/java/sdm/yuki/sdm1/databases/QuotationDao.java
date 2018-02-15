package sdm.yuki.sdm1.databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import sdm.yuki.sdm1.persistance.Quotation;
import sdm.yuki.sdm1.utils.Values;

/**
 * Created by Yuki on 15-Feb-18.
 */

@Dao
public interface QuotationDao {

    @Insert
    void addQuotation(Quotation quotation);

    @Delete
    void removeQuotation(Quotation quotation);

    @Query("SELECT * FROM " + Values.QUOTATION_TABLE)
    List<Quotation> getAllQuotations();

    @Query("SELECT * FROM " + Values.QUOTATION_TABLE + " WHERE " + Values.QUOTA_COLUMN + " = :quote")
    Quotation getQuotation(String quote);

    @Query("DELETE FROM " + Values.QUOTATION_TABLE)
    void removeAllQuotations();
}
