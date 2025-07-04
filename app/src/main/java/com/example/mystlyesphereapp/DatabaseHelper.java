package com.example.mystlyesphereapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "stylesphere.db";
    private static final int DATABASE_VERSION = 2;

    // --- Products Table ---
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CATEGORY = "category";

    // --- Users Table ---
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";

    // --- Create Table SQL ---
    private static final String CREATE_PRODUCTS_TABLE =
            "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_PRICE + " REAL," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_CATEGORY + " TEXT" + ")";

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + "(" +
                    COLUMN_USER_EMAIL + " TEXT PRIMARY KEY," +
                    COLUMN_USER_PASSWORD + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // --- Create Tables ---
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        insertSampleData(db);
        insertAdminUser(db); // ✅ Insert admin credentials
    }

    // --- Upgrade DB ---
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // --- Insert Sample Products ---
    private void insertSampleData(SQLiteDatabase db) {
        addProduct(new Product("Floral Dress", 29.99, "Elegant floral dress for all occasions.", "Women"), db);
        addProduct(new Product("Cotton Top", 19.99, "Lightweight cotton top for daily wear.", "Women"), db);
        addProduct(new Product("Jeans", 39.99, "Slim fit jeans for women.", "Women"), db);
    }

    // --- Insert Admin User ---
    private void insertAdminUser(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, "admin@stylesphere.com");
        values.put(COLUMN_USER_PASSWORD, "admin123");
        db.insert(TABLE_USERS, null, values);
    }

    // --- Add Product (used internally) ---
    public void addProduct(Product product, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_CATEGORY, product.getCategory());
        db.insert(TABLE_PRODUCTS, null, values);
    }

    // --- Get Products by Category ---
    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS,
                new String[]{COLUMN_NAME, COLUMN_PRICE, COLUMN_DESCRIPTION, COLUMN_CATEGORY},
                COLUMN_CATEGORY + "=?",
                new String[]{category}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                double price = cursor.getDouble(1);
                String description = cursor.getString(2);
                String prodCategory = cursor.getString(3);

                productList.add(new Product(name, price, description, prodCategory));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productList;
    }

    // --- Register New User ---
    public boolean registerUser(String email, String password) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_USER_EMAIL},
                COLUMN_USER_EMAIL + "=?",
                new String[]{email},
                null, null, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            return false; // Already exists
        }

        cursor.close();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    // --- User Login Check ---
    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                null,
                COLUMN_USER_EMAIL + "=? AND " + COLUMN_USER_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null);

        boolean success = cursor.moveToFirst();
        cursor.close();
        return success;
    }

    // --- Add Product (no db passed) ---
    public void addProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_CATEGORY, product.getCategory());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

}
