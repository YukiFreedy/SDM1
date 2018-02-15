package sdm.yuki.sdm1.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import sdm.yuki.sdm1.persistance.Quotation;
import sdm.yuki.sdm1.utils.Values;

/**
 * Created by Yuki on 14-Feb-18.
 */

public class MySqliteOH extends SQLiteOpenHelper {

    private static MySqliteOH instance;

    private MySqliteOH(Context context) {
        super(context, Values.QUOTATION_DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE quotation_table (id INTEGER PRIMARY KEY AUTOINCREMENT, quote TEXT NOT NULL, author TEXT, UNIQUE(quote));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Quotation> getAllQuotations() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Values.QUOTATION_TABLE, new String[]{Values.QUOTA_COLUMN, Values.AUTHOR_COLUMN}, null, null, null, null, null, null);
        List<Quotation> quotations = new ArrayList<>();
        while (cursor.moveToNext()) {
            quotations.add(new Quotation(cursor.getString(0), cursor.getString(1)));
        }
        cursor.close();
        db.close();
        return quotations;
    }

    public boolean alreadyInDatabase(Quotation quotation) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Values.QUOTATION_TABLE, null, Values.QUOTA_COLUMN + "=?", new String[]{quotation.getQuoteText()}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            getReadableDatabase().close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public boolean addQuotation(Quotation quotation) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Values.QUOTA_COLUMN, quotation.getQuoteText());
            contentValues.put(Values.AUTHOR_COLUMN, quotation.getQuoteAuthor());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(Values.QUOTATION_TABLE, null, contentValues);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeQuotation(Quotation quotation) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(Values.QUOTATION_TABLE, Values.QUOTA_COLUMN + "=?", new String[]{quotation.getQuoteText()});
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeAllQuotations() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Values.QUOTATION_TABLE, null, null);
        db.close();
    }

    public synchronized static MySqliteOH getInstance(Context context) {
        if (instance == null) {
            instance = new MySqliteOH(context);
        }
        return instance;
    }
}
