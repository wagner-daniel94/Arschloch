package com.example.arschloch;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Statistic";

    // Table name: Note.
    private static final String TABLE_STATISTICS = "Statistic";

    private static final String COLUMN_STATISTIC_ID ="EntityId";
    private static final String COLUMN_STATISTIC_NAME ="StatisticName";
    private static final String COLUMN_STATISTIC_NUMBER = "StatisticNumber";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");

        String script = "CREATE TABLE " + TABLE_STATISTICS + "("
                + COLUMN_STATISTIC_ID + " INTEGER PRIMARY KEY," + COLUMN_STATISTIC_NAME + " TEXT,"
                + COLUMN_STATISTIC_NUMBER + " TEXT" + ")";

        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);

        onCreate(db);
    }


    // If Statistics table has no data
    // default, Insert 2 records.
    public void createDefaultStatisticsIfNeed()  {
        int count = this.getStatisticsCount();
        if(count ==0 ) {
            Statistic statistics  = new Statistic(1,"0","Anzahl Spiele");
            Statistic statistics1 = new Statistic(2,"0","Anzahl gewonnene Spiele");
            Statistic statistics2 = new Statistic(3,"0","Anzahl der Züge");
            Statistic statistics3 = new Statistic(4,"0","Anzahl verlorener Spiele");
            this.addStatistic(statistics);
            this.addStatistic(statistics1);
            this.addStatistic(statistics2);
            this.addStatistic(statistics3);
        }
    }


    public void addStatistic(Statistic statistic) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STATISTIC_NAME, statistic.getStatisticName());
        values.put(COLUMN_STATISTIC_NUMBER, statistic.getStatisticNumber());

        // Inserting Row
        db.insert(TABLE_STATISTICS, null, values);

        // Closing database connection
        db.close();
    }


    public Statistic getStatistic(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Statistic statistic = new Statistic();
        Cursor cursor = db.query(TABLE_STATISTICS, new String[] {COLUMN_STATISTIC_ID,
                        COLUMN_STATISTIC_NUMBER, COLUMN_STATISTIC_NAME}, COLUMN_STATISTIC_ID + "=?",
                new String[] { String.valueOf(id) }, COLUMN_STATISTIC_ID, COLUMN_STATISTIC_ID, null, null);
        if(cursor !=null )
            cursor.moveToFirst();


             statistic = new Statistic(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));

             return statistic;


    }


    public List<Statistic> getAllStatistics() {
        List<Statistic> statistics = new ArrayList<Statistic>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STATISTICS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Statistic statistic = new Statistic();
                statistic.setStatisticId(Integer.parseInt(cursor.getString(0)));
                statistic.setStatisticName(cursor.getString(1));
                statistic.setStatisticNumber(cursor.getString(2));

                statistics.add(statistic);
            } while (cursor.moveToNext());
        }

        // return note list
        return statistics;
    }

    public int getStatisticsCount() {

        String countQuery = "SELECT  * FROM " + TABLE_STATISTICS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateStatistics (Statistic statistic) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STATISTIC_NAME, statistic.getStatisticName());
        values.put(COLUMN_STATISTIC_NUMBER, statistic.getStatisticNumber());

        // updating row
        return db.update(TABLE_STATISTICS, values, COLUMN_STATISTIC_ID + " = ?",
                new String[]{String.valueOf(statistic.getStatisticId())});
    }

    public void deleteStatistics (Statistic statistic) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATISTICS, COLUMN_STATISTIC_ID + " = ?",
                new String[] { String.valueOf(statistic.getStatisticId()) });
        db.close();
    }

    public void deleteDatabase(String DATABASE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = " DELETE FROM " + DATABASE_NAME;
        db.execSQL(clearDBQuery);
    }




}