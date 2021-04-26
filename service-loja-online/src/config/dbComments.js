/**
 * Configurações de Banco de Dados
 */

 //Configurações Genéricas
var mongoose = require('mongoose');

//desenvolviemnto
//mongoose.connect('mongodb://localhost:27017/db_lojaOnline');

//export MONGO_DB=192.168.0.27:32182
var mongoHost = process.env.MONGO_DB;
//env MONGO_DB
mongoose.connect('mongodb://'+mongoHost+'/db_lojaOnline');

var Schema = mongoose.Schema;

//Entidade Comentário Produto
var productCommentSchema = new Schema({
  sku: String,
  description: String
});

var ProductComments = mongoose.model('ProductComments', productCommentSchema);
module.exports = ProductComments;