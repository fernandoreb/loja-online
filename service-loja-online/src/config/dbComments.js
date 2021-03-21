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

//Entidade Comentário Produto
var productCommentSchema = new Schema({
  sku: String,
  description: String
});

var ProductComments = mongoose.model('ProductComments', productCommentSchema);
module.exports = ProductComments;