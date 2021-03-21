/**
 * Configurações de Banco de Dados
 */

 //Configurações Genéricas
var mongoose = require('mongoose');

//desenvolviemnto
//mongoose.connect('mongodb://localhost:27017/db_lojaOnline');

//docker-compose
mongoose.connect('mongodb://mongo:27017/db_lojaOnline');
var Schema = mongoose.Schema;

//Entidade Produto
var productSchema = new Schema({
  sku: String,
  name: String,
  price: String,
  description: String
});

var Product = mongoose.model('Product', productSchema);
module.exports = Product;