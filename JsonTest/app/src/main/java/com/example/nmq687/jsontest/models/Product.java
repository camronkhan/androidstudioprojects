package com.example.nmq687.jsontest.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    private String id;
    private String name;
    private String description;
    private String image;
    private String company;
    private String companyWebsite;
    private String source;

    public Product(JSONObject object) {
        try {
            this.id = object.getString("id");
            this.name = object.getString("product");
            this.description = object.getString("description");
            this.image = object.getString("image");
            this.company = object.getString("company");
            this.companyWebsite = object.getString("company_website");
            this.source = object.getString("source");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    public Product(String product, String description, String image, String company, String companyWebsite, String source) {
        this.product = product;
        this.description = description;
        this.image = image;
        this.company = company;
        this.companyWebsite = companyWebsite;
        this.source = source;
    }
    */

    public String getId() {
        if (this.id != null) {
            return this.id;
        } else {
            return "";
        }
    }

    public String getName() {
        if (this.name != null) {
            return this.name;
        } else {
            return "";
        }
    }

    public String getDescription() {
        if (this.description != null) {
            return this.description;
        } else return "";
    }

    public String getImage() {
        if (this.image != null) {
            return this.image;
        } else {
            return "";
        }
    }

    public String getCompany() {
        if (this.company != null) {
            return this.company;
        } else {
            return "";
        }
    }

    public String getCompanyWebsite() {
        if (this.companyWebsite != null) {
            return this.companyWebsite;
        } else {
            return "";
        }
    }

    public String getSource() {
        if (this.companyWebsite != null) {
            return this.source;
        } else {
            return "";
        }
    }

    /*
    public void setProduct(String product) {
        this.product = product;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public void setSource(String source) {
        this.source = source;
    }
    */
}