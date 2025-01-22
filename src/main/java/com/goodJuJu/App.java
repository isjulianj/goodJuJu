package com.goodJuJu;

import com.goodJuJu.bean.Product;
import com.goodJuJu.dao.ProductDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext apc = new ClassPathXmlApplicationContext("config.xml");
        Scanner sc = new Scanner(System.in);

        Product product = apc.getBean(Product.class);
        System.out.println("Add a product name:");
        product.setName(sc.nextLine().trim());
        System.out.println("Add a product SKU:");
        product.setSKU(sc.nextLine().trim());
        System.out.println("Add a product price per unit:");
        product.setPrice_per_unit(sc.nextFloat());
        System.out.println("Is product live");
        product.setActive(sc.nextBoolean());

        ProductDao dao = apc.getBean(ProductDao.class);

        dao.createTable();

        if(dao.insert(product) > 0) {
            System.out.println("The product has been added");
        }

    }
}
