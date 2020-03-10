const express = require('express')
const controllerProducts = require('../controller/products')
const router = express.Router()

/**
 * Roteador da API de Produtos
 */
router.get('/products/:id', controllerProducts.findById)
router.get('/products', controllerProducts.findAll)
router.post('/products', controllerProducts.create)
router.put('/products/:id', controllerProducts.update)
router.delete('/products/:id', controllerProducts.delete)

module.exports = router