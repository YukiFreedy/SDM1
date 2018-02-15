package sdm.yuki.sdm1.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import sdm.yuki.sdm1.persistance.Quotation;
import sdm.yuki.sdm1.utils.Values;

/**
 * Created by Yuki on 15-Feb-18.
 */

@Database(entities = {Quotation.class}, version = 1)
public abstract class QuotationDatabase extends RoomDatabase{

    private static QuotationDatabase quotationDatabase;

    public synchronized static QuotationDatabase getInstance(Context context){
        if(quotationDatabase == null){
            quotationDatabase = Room.databaseBuilder(context, QuotationDatabase.class, Values.QUOTATION_DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return quotationDatabase;
    }

    public abstract QuotationDao quotationDao();
}
