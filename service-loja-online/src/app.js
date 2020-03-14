const express = require('express')
const http = require('http')
const status = require('http-status')
const productsRoute = require('./routes/products')
const productsCommentsRoute = require('./routes/productsComments')

const app = express()

app.use(function (req, res, next) {
  
  //habilitando cors
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});


app.use(express.json())

//adicionando a roda dos serviços em /api
app.use('/api', productsRoute)
app.use('/api', productsCommentsRoute)

//rotas não encontradas, responde com 404
app.use((request, response, next) => {
  response.status(status.NOT_FOUND).send()
})

//erros 500 respondem com um json padrão de erro
app.use((error, request, response, next) => {
  response.status(status.INTERNAL_SERVER_ERROR).json({ error })
})

//Porta do Serviço
const port = 3000;

app.set('port', port)
const server = http.createServer(app)

server.listen(port)
console.log('server run on port:' + port);
