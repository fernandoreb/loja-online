/**
 * Configurações de Banco de Dados
 */

 //Configurações Genéricas
var mongoose = require('mongoose');

//desenvolviemnto
//mongoose.connect('mongodb://localhost:27017/db_lojaOnline');

//export MONGO_DB=192.168.0.27:30132
var mongoHost = process.env.MONGO_DB;
//env MONGO_DB
mongoose.connect('mongodb://'+mongoHost+'/db_lojaOnline');

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