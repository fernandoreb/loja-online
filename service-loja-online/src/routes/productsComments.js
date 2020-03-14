const express = require('express')
const controllerProductsComments = require('../controller/productsComments')
const router = express.Router()

/**
 * Roteador da API de Produtos
 */
router.get('/productsComments/:id', controllerProductsComments.findById)
router.get('/productsComments', controllerProductsComments.findAll)
router.post('/productsComments', controllerProductsComments.create)
router.put('/productsComments/:id', controllerProductsComments.update)
router.delete('/productsComments/:id', controllerProductsComments.delete)

module.exports = router