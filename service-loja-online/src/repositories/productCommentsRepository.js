/**
 * Repositório de de Produtos
 */

'use strict';
var ProductComments = require('../config/dbComments');

module.exports = new class ProductCommentsRepository {

    getAll() {
        return ProductComments.find();
    }

    getById(sku) {
        return ProductComments.find({'sku':sku});
    }

    create(productComment) {
        return ProductComments.create(productComment);
    }

    update(id, productComment) {

        const updatedproductcomment = {
            sku: productComment.sku,
            description: productComment.description
        }

        return ProductComments.findByIdAndUpdate(id, updatedproductcomment, { new: true });
    }

    delete(id) {
        return ProductComments.findByIdAndRemove(id);
    }

}