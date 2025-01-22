package com.goodJuJu.dao;

import com.goodJuJu.bean.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.util.Logger;
import org.sqlite.util.LoggerFactory;

public class ProductDao {

    private JdbcTemplate temp;
    /// setter injection


    public void setTemp(JdbcTemplate temp) {
        this.temp = temp;
    }


    public void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "SKU TEXT NOT NULL UNIQUE, " +
                "price_per_unit REAL NOT NULL, " +
                "active BOOLEAN NOT NULL CHECK (active IN (0, 1))" +
                ");";

        temp.execute(createTableQuery);

    }

    public int insert(Product product) {
        String sql = "INSERT INTO Products (name, SKU, price_per_unit, active) VALUES (?, ?, ?, ?)";
        return temp.update(sql, product.getName(), product.getSKU(), product.getPrice_per_unit(), product.isActive() ? 1 : 0);
    }
}
