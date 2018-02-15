package sdm.yuki.sdm1.persistance;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import sdm.yuki.sdm1.utils.Values;

/**
 * Created by Yuki on 07-Feb-18.
 */

@Entity(tableName = Values.QUOTATION_TABLE, indices = {@Index(value = Values.QUOTA_COLUMN, unique = true), @Index(Values.AUTHOR_COLUMN)})
public class Quotation {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = Values.QUOTA_COLUMN)
    private String quoteText;
    @ColumnInfo(name = Values.AUTHOR_COLUMN)
    private String quoteAuthor;

    public Quotation(String quoteText, String quoteAuthor) {
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }
}
