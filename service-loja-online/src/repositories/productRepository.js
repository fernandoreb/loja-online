/**
 * Repositório de de Produtos
 */

'use strict';
var Product = require('../config/db');

module.exports = new class ProductRepository {

    getAll() {
        return Product.find();
    }

    getById(id) {
        return Product.findById(id);
    }

    create(product) {
        return Product.create(product);
    }

    update(id, product) {

        const updatedproduct = {
            sku: product.sku,
            name: product.name,
            price: product.price,
            description: product.description,
        }

        return Product.findByIdAndUpdate(id, updatedproduct, { new: true });
    }

    delete(id) {
        return Product.findByIdAndRemove(id);
    }

}